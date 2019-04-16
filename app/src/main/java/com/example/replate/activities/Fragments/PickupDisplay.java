package com.example.replate.activities.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.example.replate.R;
import com.example.replate.models.PickupRequest;

import java.util.ArrayList;


public class PickupDisplay extends Fragment {


    public PickupDisplay() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pickup_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ArrayList<PickupRequest> pickupRequests = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        RecyclerView.Adapter mAdapter = new PickupsListAdapter(pickupRequests);
        recyclerView.setAdapter(mAdapter);
    }
}
