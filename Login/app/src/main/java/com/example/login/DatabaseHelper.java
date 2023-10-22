package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DBNAME="Login.db";
    public DatabaseHelper (Context context) {
        super (context,"Login.db",  null,  1);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists Users(nickname VARCHAR, nameLastname VARCHAR, password VARCHAR, email VARCHAR)"); }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Users");
    }
    public Boolean insertData(String username, String pass) {
        SQLiteDatabase db= this.getWritableDatabase ();
        ContentValues values = new ContentValues();
        values.put("nickname", username);
        values.put("password", pass);
        long result = db.insert( "Users",  null, values);

        if(result ==-1) return false;
        else
            return true;
    }
    public Boolean checkusername (String username) {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select * from Users where nickname=?", new String[] {username});
        if (cursor.getCount() >0) return true;
        else
            return false;
    }
    public Boolean checkusernamepassword (String username, String pass) {
        SQLiteDatabase db= this.getWritableDatabase ();
        Cursor cursor = db.rawQuery( "select * from Users where nickname=? and password=?", new String[] {username, pass});
        if (cursor.getCount()>0)
            return true;
        else
            return false; }
}
