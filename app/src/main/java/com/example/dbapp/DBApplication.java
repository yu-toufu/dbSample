package com.example.dbapp;

import android.app.Application;
import android.util.Log;

import com.example.dbapp.db.AppDatabase;

public class DBApplication extends Application {

    static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("DBApplication","======== start ========");
        db = getDatabase();
    }

    private AppDatabase getDatabase() {
        return AppDatabase.getInstance(getApplicationContext());
    }
}
