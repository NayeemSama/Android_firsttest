package com.xpressy.firsttest.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.xpressy.firsttest.Model.UserModel;

import java.util.ArrayList;

public class Tbl_User extends MyDatabase{

    public Tbl_User(Context context) {
        super(context);
    }

    public static final String TABLE_NAME = "Registered_User";
    public static final String USERNAME = "Username";
    public static final String PASSWORD = "Password";
    public static final String USERID = "UserID";
    public static final String EMAIL = "Email";
    public static final String PHONE = "Phone";
    public static final String AGE = "Age";
    public static final String COUNTRY = "Country";
    public static final String BIRTHDATE = "Birthdate";
    public static final String BIRTHTIME = "Birthtime";
    public static final String RATE = "Rating";
    public static final String GENDER = "Gender";



    public long setUser(String name, String pass, String email, long phone, int age,
                        String country, String date, String time, float rating, int gender){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USERNAME, name);
        cv.put(PASSWORD, pass);
        cv.put(EMAIL, email);
        cv.put(PHONE, phone);
        cv.put(AGE, age);
        cv.put(COUNTRY, country);
        cv.put(BIRTHDATE, date);
        cv.put(BIRTHTIME, time);
        cv.put(RATE, rating);
        cv.put(GENDER, gender);

        long id = db.insert(TABLE_NAME, null, cv);
        db.close();

        return id;
    }

    public ArrayList<UserModel> getTable(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<UserModel> userList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        for (int i=0;i<cursor.getCount();i++)
        {
            UserModel userModel = new UserModel();
            userModel.setUserID(cursor.getInt(cursor.getColumnIndexOrThrow(USERID)));
            userModel.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(USERNAME)));
            userModel.setPassword(cursor.getInt(cursor.getColumnIndexOrThrow(PASSWORD)));
            userModel.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(EMAIL)));
            userModel.setPhone(cursor.getInt(cursor.getColumnIndexOrThrow(PHONE)));
            userModel.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(AGE)));
            userModel.setCountry(cursor.getString(cursor.getColumnIndexOrThrow(COUNTRY)));
            userModel.setDate(cursor.getString(cursor.getColumnIndexOrThrow(BIRTHDATE)));
            userModel.setTime(cursor.getString(cursor.getColumnIndexOrThrow(BIRTHTIME)));
            userModel.setRating(cursor.getInt(cursor.getColumnIndexOrThrow(RATE)));
            userModel.setGender(cursor.getInt(cursor.getColumnIndexOrThrow(GENDER)));
            userList.add(userModel);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return userList;
    }

    public boolean validUser(String user, String pass){
        SQLiteDatabase db = getReadableDatabase();
        String[] params = {user,pass};
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + USERNAME + " = ?" +" AND "+PASSWORD+" = ?";
        Cursor cursor = db.rawQuery(query,params);
        cursor.moveToFirst();
        boolean flag = true;
        if (cursor.getCount()<1){
            flag = false;
        }
        cursor.close();
        db.close();
        return flag;
    }



}
