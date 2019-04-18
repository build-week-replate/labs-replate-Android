package com.example.replate.activities.Dashboard.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.replate.R;
import com.example.replate.daos.PickupRequestsDao;
import com.example.replate.models.PickupRequest;
import com.example.replate.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;


public class PickupsDisplayFragment extends Fragment {

    User user;
    ArrayList<PickupRequest> pickupRequests = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;


    public PickupsDisplayFragment() {
    }

    @Override
    public void onAttach(Context context) {
        if (getArguments() != null) {
            user = (User)getArguments().getSerializable("user");
        }
        super.onAttach(context);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pickup_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Setting Up RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view_pickups_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new PickupsListAdapter(pickupRequests, user);
        recyclerView.setAdapter(mAdapter);
    }

    public void getAllPickups() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = PickupRequestsDao.getAllPickups(user.getToken());
                try {
                    JSONArray results = new JSONArray(result);
                    for (int i = 0; i < results.length(); ++i) {
                        JSONObject temp = results.getJSONObject(i);
                        pickupRequests.add(new PickupRequest(temp));
                    }
                    Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    public void getCompanyPickups(final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = PickupRequestsDao.getAllPickups(user.getToken());
                try {
                    JSONArray results = new JSONArray(result);
                    for (int i = 0; i < results.length(); ++i) {
                        JSONObject temp = results.getJSONObject(i);
                        PickupRequest request = new PickupRequest(temp);
                        if (request.getCompany_id() == id) {    //only adds to list if it matches ID
                            pickupRequests.add(new PickupRequest(temp));
                        }
                    }
                    Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void getVolunteerPickups(final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = PickupRequestsDao.getAllPickups(user.getToken());
                try {
                    JSONArray results = new JSONArray(result);
                    for (int i = 0; i < results.length(); ++i) {
                        JSONObject temp = results.getJSONObject(i);
                        PickupRequest request = new PickupRequest(temp);
                        if (request.getVolunteer_id() == id) {    //only adds to list if it matches ID
                            pickupRequests.add(new PickupRequest(temp));
                        }
                    }
                    Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
