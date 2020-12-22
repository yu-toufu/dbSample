package com.example.dbapp;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.example.dbapp.db.User;
import com.example.dbapp.db.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private AircraftViewModel mModel;
    private ModelAdapter<AircraftObject, ListItem> mItemAdapter;
    private FastAdapter<ListItem>  mAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (getActivity() == null)
            return;
        super.onActivityCreated(savedInstanceState);
        LoggerViewModel model = ViewModelProviders.of(getActivity()).get(LoggerViewModel.class);
        subscribeToModel(model);
    }

    private void subscribeToModel(LoggerViewModel model) {
        mModel = model;
        final Observer<Set<AircraftObject>> listObserver = aircraftList -> {
            if (aircraftList == null)
                return;
            Log.d(TAG, "DeviceList onChanged: " + aircraftList);
            mItemAdapter.setNewList(new ArrayList<>(aircraftList));
        };

        mModel.getAllAircraft().observe(getViewLifecycleOwner(), listObserver);
    }
}