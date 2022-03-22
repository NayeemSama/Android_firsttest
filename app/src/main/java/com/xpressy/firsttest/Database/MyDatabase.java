package com.xpressy.firsttest.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDatabase extends SQLiteAssetHelper {

    public static final String DATABASE = "Userdatabase.db";
    public static final int VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE, null, VERSION);
    }
}
