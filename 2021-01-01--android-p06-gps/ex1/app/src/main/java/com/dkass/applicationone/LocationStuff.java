package com.dkass.applicationone;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class LocationStuff {
    static public SQLiteDatabase db;


    static void init(Context context) {
        String path = context.getFilesDir().getPath();

        db = SQLiteDatabase.openOrCreateDatabase(path + "/my_db.db", null, null);

        db.execSQL("DROP TABLE IF EXISTS TutorialsPoint;");
        db.execSQL("CREATE TABLE IF NOT EXISTS TutorialsPoint(Longitude REAL, Latitude REAL, Name TEXT, Description TEXT);");

//        db.execSQL("INSERT INTO TutorialsPoint VALUES(50.322679032726874, 17.396315920696303, 'blisko','blisko i fajnie');");
        db.execSQL("INSERT INTO TutorialsPoint VALUES(50.322679032726874, 17.396315920696303, 'blisko i bez opisu','');");
        db.execSQL("INSERT INTO TutorialsPoint VALUES(50.34054038967489, 17.410306321781682, 'srednio blisko','okok');");
        db.execSQL("INSERT INTO TutorialsPoint VALUES(50.37334176278174, 17.433738099882177, 'dalej troche','aaaaaaaa');");
        db.execSQL("INSERT INTO TutorialsPoint VALUES(50.413629518442775, 17.26089348783791, 'daleko','hghhhhhhhhh');");
        db.execSQL("INSERT INTO TutorialsPoint VALUES(50.4758515256263, 17.331704837743985, 'nysa bardzo daleko1','nysanysa1');");
        db.execSQL("INSERT INTO TutorialsPoint VALUES(50.4758515256263, 17.331704837743985, 'nysa bardzo daleko2','nysanysa2');");
        db.execSQL("INSERT INTO TutorialsPoint VALUES(50.4758515256263, 17.331704837743985, 'nysa bardzo daleko3','nysanysa3');");
        db.execSQL("INSERT INTO TutorialsPoint VALUES(50.4758515256263, 17.331704837743985, 'nysa bardzo daleko4','nysanysa4');");

    }

    static List<MyLocation> getLocationsFromServer(Context context) {
        final List<MyLocation> list = new ArrayList<>();
        final AtomicInteger q = new AtomicInteger(0);
        final CountDownLatch latch = new CountDownLatch(1);

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://192.168.0.143:8080/uaua";

        /*
        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        q.incrementAndGet();
//                        MyLocation uu = new MyLocation();
//                        uu.latitude = 50.319139334568966;
//                        uu.longitude = 17.3773578438666;
//                        uu.name = "got a response?";
//                        list.add(uu);

                        if (null != response) {
                            try {
                                JSONArray arr = response.getJSONArray("arr");

//                                MyLocation n = new MyLocation();
//                                n.latitude = 50.319139334568966;
//                                n.longitude = 17.3773578438666;
//                                n.name = "huh: " + arr.length();
//                                list.add(n);
                                q.incrementAndGet();

                                for (int i = 0; i < arr.length(); i++) {
//                                    MyLocation m = new MyLocation();
//                                    m.latitude = 50.319139334568966;
//                                    m.longitude = 17.3773578438666;
//                                    m.name = "huh: " + arr.getString(i);
//                                    list.add(m);
                                    q.incrementAndGet();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
//                                MyLocation m = new MyLocation();
//                                m.latitude = 50.319139334568966;
//                                m.longitude = 17.3773578438666;
//                                m.name = "exception: " + e.getMessage();
//                                list.add(m);
                                q.set(-444);
                            }
                        } else {
//                            MyLocation m = new MyLocation();
//                            m.latitude = 50.319139334568966;
//                            m.longitude = 17.3773578438666;
//                            m.name = "null == response";
//                            list.add(m);
                            q.set(-22);
                        }

                        latch.countDown();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                MyLocation m = new MyLocation();
//                m.latitude = 50.319139334568966;
//                m.longitude = 17.3773578438666;
//                m.name = "error response";
//                list.add(m);

                q.set(-10);
                latch.countDown();
            }
        });
        queue.add(request);
        queue.start();

        try {
            latch.await(1, TimeUnit.SECONDS);
//            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        queue.stop();*/

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(url, null, future, future);
        queue.add(request);
        queue.start();

        try {
            JSONObject response = future.get(); // this will block
        } catch (InterruptedException e) {
            // exception handling
        } catch (ExecutionException e) {
            // exception handling
        }

        MyLocation m = new MyLocation();
        m.latitude = 50.319139334568966;
        m.longitude = 17.3773578438666;
        m.name = "test " + q.get();
        list.add(m);

        MyLocation m2 = new MyLocation();
        m2.latitude = 50.319139334568966;
        m2.longitude = 17.3773578438666;
        m2.name = "test_";
        list.add(m2);

        return list;
    }

    static List<MyLocation> getAllLocations(Context context) {
        List<MyLocation> list = new ArrayList<MyLocation>();

        Cursor cursor = db.rawQuery("Select * from TutorialsPoint", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MyLocation loc = new MyLocation();
            loc.longitude = cursor.getDouble(1);
            loc.latitude = cursor.getDouble(0);
            loc.name = cursor.getString(2);
            loc.description = cursor.getString(3);
            list.add(loc);
            cursor.moveToNext();
        }
        cursor.close();

        List<MyLocation> external = getLocationsFromServer(context);
        list.addAll(external);

        Collections.shuffle(list);
        return list;
    }

    public static class MyLocation {
        public double longitude = 50.319139334568966;
        public double latitude = 17.3773578438666;
        public String name = "";
        public String description = "";
    }
}
