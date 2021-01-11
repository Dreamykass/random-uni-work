package com.dkass.weatherapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dkass.weatherapp.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonOK.setOnClickListener((View v) -> {
            //
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + binding.editTextCity.getText() + ",poland&appid=8ba160638ee3551db0852598229b0fcd";
            JsonObjectRequest request = new JsonObjectRequest(url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if (null != response) {
                                try {
                                    JSONArray array = response.getJSONArray("list");
                                    JSONObject obj;

                                    if (!binding.checkBoxNextDay.isChecked()) {
                                        obj = array.getJSONObject(0);
                                    } else {
                                        obj = array.getJSONObject(7);
                                    }

                                    JSONObject main = obj.getJSONObject("main");
                                    int temp = (int) Double.parseDouble(main.getString("temp"));
                                    binding.textViewTemperature.setText("" + (temp - 273) + "C");
                                    binding.textViewHumidity.setText("" + main.getString("humidity") + "%");

                                    JSONArray weather = obj.getJSONArray("weather");
                                    String weatherString = weather.getJSONObject(0).getString("description");
                                    binding.textViewWeather.setText(weatherString);

                                    if (weatherString.equals("clear sky")) {
                                        binding.textViewIcon.setText("\uD83C\uDF1E");
                                    } else if (weatherString.equals("overcast clouds")) {
                                        binding.textViewIcon.setText("⛅");
                                    } else {
                                        binding.textViewIcon.setText("⛅");
                                    }


                                } catch (JSONException e) {
                                    binding.textViewWeather.setText("json exception");
                                }
                            } else {
                                binding.textViewWeather.setText("response is null");
                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    binding.textViewWeather.setText("response error: " + error.toString());
                }
            });
            queue.add(request);
            queue.start();

        });
    }
}