package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


        public DatabaseHelper(Context context, String DbName){
            //super(context, "Login.db", null, 1 );
            super(context, DbName, null, 1 );



}


    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("Create table user(email text primary key, password text)");
        db.execSQL("create table " + TABLE_NAME +" (ID Float PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS Float)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public boolean insert (String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email) ;
        contentValues.put("password", password) ;
        long ins = db.insert("user",null, contentValues);    Context context;


            if(ins==-1) return false;
        else return true;

    }
    public Boolean chkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?",new String[]{email});
        if(cursor.getCount()>0) return false;
        else return true;
    }
    public Boolean emailpassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?",new String[]{email, password});
                if(cursor.getCount()>0) return true;
        else return false;


    }

    public void updateData(String toString, String toString1, String toString2, String toString3,String id,String name,String surname,String marks) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });

    }








    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";







    public boolean insertData(String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}
