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
import com.example.replate.adapters.NetworkAdapter;
import com.example.replate.daos.PickupRequestsDao;
import com.example.replate.models.PickupRequest;
import com.example.replate.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PickupDisplayFragment extends Fragment {

    String username;
    User user;
    ArrayList<PickupRequest> pickupRequests = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;


    public PickupDisplayFragment() {
    }

    @Override
    public void onAttach(Context context) {
        username = getArguments().getString("username", null);
        user = (User)getArguments().getSerializable("user");
        super.onAttach(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pickup_display, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view_pickups_list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
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
                    getActivity().runOnUiThread(new Runnable() {
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
                    getActivity().runOnUiThread(new Runnable() {
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
                    getActivity().runOnUiThread(new Runnable() {
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
