package com.dkass.applicationone;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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
        db.execSQL("INSERT INTO TutorialsPoint VALUES(50.4758515256263, 17.331704837743985, 'nysa bardzo daleko','nysanysa');");

    }

    static List<MyLocation> getAllLocations() {
        List<MyLocation> list = new ArrayList<MyLocation>();

        Cursor cursor = db.rawQuery("Select * from TutorialsPoint", null);
        cursor.moveToFirst();
        {
            MyLocation loc = new MyLocation();
            loc.longitude = cursor.getDouble(1);
            loc.latitude = cursor.getDouble(0);
            loc.name = cursor.getString(2);
            loc.description = cursor.getString(3);
            list.add(loc);
        }

        cursor.close();
        return list;
    }

    public static class MyLocation {
        public double longitude;
        public double latitude;
        public String name;
        public String description;
    }
}
