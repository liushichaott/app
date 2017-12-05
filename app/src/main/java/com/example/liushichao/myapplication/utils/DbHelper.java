package com.example.liushichao.myapplication.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liushichao on 2017/11/30.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "shichao.db";
    public static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE HTML(" +
                "id integer primary key," +
                "title varchar," +
                "url varchar," +
                "time varchar," +
                "type varchar" +
                ")");

        sqLiteDatabase.execSQL("CREATE TABLE HTML_TYPE(" +
                "id integer primary key," +
                "type varchar" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
