package com.app.pfe2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "data2.db";
    private static final int DBVERSION = 3;

    public DBHelper(Context context){
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table if not exists sensors_data(" +
                "acc_x double not null," +
                "acc_y double not null," +
                "acc_z double not null," +
                "gyro_x double not null," +
                "gyro_y double not null," +
                "gyro_z double not null," +
                "y varchar(100)" +
                ");");

        sqLiteDatabase.execSQL("create table if not exists user_data(" +
                "fname varchar not null," +
                "lname varchar not null," +
                "email varchar not null unique," +
                "fuser varchar not null," +
                "fpass varchar not null" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists sensors_data");
        sqLiteDatabase.execSQL("drop table if exists user_data");
        onCreate(sqLiteDatabase);
    }

    public void insertRow(double acc_x, double acc_y, double acc_z,
                         double gyro_x, double gyro_y, double gyro_z,
                         String y){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("acc_x", acc_x);
        contentValues.put("acc_y", acc_y);
        contentValues.put("acc_z", acc_z);
        contentValues.put("gyro_x", gyro_x);
        contentValues.put("gyro_y", gyro_y);
        contentValues.put("gyro_z", gyro_z);
        contentValues.put("y", y);
        database.insert("sensors_data", null, contentValues);

    }

    public void insertUser(String fname, String lname, String email,
                          String user, String pass){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname", fname);
        contentValues.put("lname", lname);
        contentValues.put("email", email);
        contentValues.put("fuser", user);
        contentValues.put("fpass", pass);
        database.insert("user_data", null, contentValues);
    }

    public boolean checkUser(String user, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery
                ("select * from user_data where fuser = '" + user + "' and fpass = '" + pass + "'", null);
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public String getUserPass(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery
                ("select * from user_data where email = '" + email + "'", null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor.getString(cursor.getColumnIndex("fpass"));
        }else{
            return "";
        }
    }

    public void getAllRecords(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from sensors_data", null);
        cursor.moveToFirst();
        while (cursor.isBeforeFirst() == false){
            Log.d("result", cursor.getString(cursor.getColumnIndex("acc_x")));
            cursor.moveToNext();
        }

    }
}
