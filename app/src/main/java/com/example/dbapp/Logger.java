package com.example.dbapp;

import android.content.Context;

import androidx.room.Room;

import com.example.dbapp.db.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private final List<String> log = new ArrayList<String>();

    private static Logger logger;

    private final Callback callback;

    public static class Callback {
        public void onNew(String msg) {}
    }
    public Logger(Callback callback) {
        this.callback = callback;
    }

    public void log(String msg){
        synchronized (this.log){
            this.log.add(msg);
        }
    }

    public List<String> getAll(){
        return this.log;
    }
}
