package com.dkass.applicationone;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dkass.applicationone.databinding.ActivityMainBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationClient;
    private ActivityMainBinding binding;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        LocationStuff.init(this);

        binding.buttonOk.setOnClickListener((View v) -> {
            //
            update();
        });

    }

    void getDescriptionFromInternets(String name, TextView textView) {

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://192.168.0.143:8080/uaua";
        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (null != response) {
                            try {
                                textView.setText(response.getString("query"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                                textView.setText("excpetion");
                            }
                        } else {
                            textView.setText("null");
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.toString());
            }
        });
        queue.add(request);
        queue.start();
    }

    @SuppressLint("MissingPermission")
    void update() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        final Location[] locationArr = new Location[1];

        Task<Location> foo = fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(Location location2) {
                        if (location2 != null) {
                            locationArr[0] = location2;
                            binding.textViewCurrent1.setText("Latitude = " + Double.toString(location2.getLatitude()));
                            binding.textViewCurrent2.setText("Longitude = " + Double.toString(location2.getLongitude()));

                            Location currentLocation = location2;
                            List<LocationStuff.MyLocation> locations = LocationStuff.getAllLocations(MainActivity.this);

                            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                            String url = "http://192.168.0.143:8080/uaua";
                            JsonObjectRequest request = new JsonObjectRequest(url, null,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            if (null != response) {
                                                try {
                                                    JSONArray arr = response.getJSONArray("arr");
                                                    
                                                    for (int i = 0; i < arr.length(); i++) {
                                                        String[] parts = arr.getString(i).split(",");

                                                        LocationStuff.MyLocation m = new LocationStuff.MyLocation();
                                                        m.latitude = Double.parseDouble(parts[0]);
                                                        m.longitude = Double.parseDouble(parts[1]);
                                                        m.name = "from the server: " + parts[2];
                                                        m.description = parts[3];
                                                        locations.add(m);
                                                    }


                                                    locations.sort(
                                                            (LocationStuff.MyLocation a, LocationStuff.MyLocation b) -> {
                                                                float[] results1 = new float[3];
                                                                float[] results2 = new float[3];

                                                                Location.distanceBetween(a.latitude, a.longitude, currentLocation.getLatitude(), currentLocation.getLongitude(), results1);
                                                                Location.distanceBetween(b.latitude, b.longitude, currentLocation.getLatitude(), currentLocation.getLongitude(), results2);

                                                                return Double.compare(
                                                                        results1[0],
                                                                        results2[0]
                                                                );
                                                            });

                                                    LocationStuff.MyLocation closest = locations.get(0);
                                                    float[] closestDistanceArr = new float[3];
                                                    Location.distanceBetween(closest.latitude, closest.longitude, currentLocation.getLatitude(), currentLocation.getLongitude(), closestDistanceArr);
                                                    float closestDistance = closestDistanceArr[0];

                                                    if (closestDistance < Double.parseDouble(binding.editNumberDistance.getText().toString())) {
                                                        binding.textLocationName.setText(closest.name);
                                                        if (closest.description.isEmpty()) {
//                                    binding.textLocationDescription.setText(getDescriptionFromInternets(closest.name));
                                                            getDescriptionFromInternets(closest.name, binding.textLocationDescription);
                                                        } else {
                                                            binding.textLocationDescription.setText(closest.description);
                                                        }
                                                    } else {
                                                        binding.textLocationName.setText("nothing too close :(");
                                                        binding.textLocationDescription.setText("closest was this far: " + closestDistance);
                                                    }

                                                    // ===================================================================================== list
                                                    {
                                                        List<String> values = new ArrayList<>();

                                                        for (LocationStuff.MyLocation myLocation : locations) {
                                                            float[] c = new float[3];
                                                            Location.distanceBetween(myLocation.latitude, myLocation.longitude,
                                                                    currentLocation.getLatitude(), currentLocation.getLongitude(),
                                                                    c);

                                                            if (c[0] < Double.parseDouble(binding.editNumberDistance.getText().toString()))
                                                                values.add(myLocation.name + " (" + myLocation.description + ")");

                                                        }

                                                        String[] valuesArr = new String[values.size()];
                                                        values.toArray(valuesArr);

                                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                                                                android.R.layout.simple_list_item_1, android.R.id.text1, valuesArr);
                                                        binding.list.setAdapter(adapter);
                                                    }
                                                    // ===================================================================================

                                                } catch (JSONException e) {

                                                }
                                            } else {
                                            }

                                        }
                                    }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            });
                            queue.add(request);
                            queue.start();
                        }
//                        binding.textViewCurrent2.setText("suck wtf");
                    }
                });

//        Location currentLocation = locationArr[0];
//        if (currentLocation != null) {
//            double currentLat = currentLocation.getLatitude();
//            double currentLon = currentLocation.getLongitude();
//            List<LocationStuff.MyLocation> locations = LocationStuff.getAllLocations();
//
//            locations.sort(
//                    (LocationStuff.MyLocation a, LocationStuff.MyLocation b) -> {
//                        float[] results1 = new float[3];
//                        float[] results2 = new float[3];
//
//                        Location.distanceBetween(b.latitude, b.longitude, currentLocation.getLatitude(), currentLocation.getLongitude(), results1);
//                        Location.distanceBetween(b.latitude, b.longitude, currentLocation.getLatitude(), currentLocation.getLongitude(), results2);
//
//                        return Double.compare(
//                                results1[0],
//                                results2[1]
//                        );
//                    });
//
//            LocationStuff.MyLocation closest = locations.get(0);
////            float[] closestDistanceArr = new float[3];
////            Location.distanceBetween(closest.latitude, closest.longitude, currentLat, currentLon, closestDistanceArr);
////            float closestDistance = closestDistanceArr[0];
//
////            if (closestDistance < Integer.parseInt(binding.editNumberDistance.getText().toString())) {
//            binding.textLocationName.setText(closest.name);
//            binding.textLocationDescription.setText(closest.description);
////            } else {
////                binding.textLocationName.setText("nothing too close :(");
////                binding.textLocationDescription.setText("");
////            }
//        }
    }
}