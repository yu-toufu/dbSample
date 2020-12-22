package com.example.dbapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class LoggerViewModel extends ViewModel {

    private final MutableLiveData<List<String>> logger = new MutableLiveData<>();

    public LoggerViewModel() {
        List<String> list = new ArrayList<>();
        logger.postValue(list);
    }

    void setAll(List<String> objects) {
        logger.postValue(new ArrayList<String>(objects));
    }

    LiveData<List<String>> getAll() {
        return logger;
    }
}
