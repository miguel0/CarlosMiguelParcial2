package com.example.parcial2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerFragment extends Fragment implements Handler.Callback {

    private ArrayList<Person> people;
    private RecyclerView rvPeople;
    private PeopleAdapter adapter;
    private Handler handler;

    public RecyclerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        people = new ArrayList<>();
        rvPeople = getView().findViewById(R.id.rvPeople);
        adapter = new PeopleAdapter(people, getContext());
        rvPeople.setAdapter(adapter);
        rvPeople.setLayoutManager(new LinearLayoutManager(getContext()));

        handler = new Handler(Looper.getMainLooper(), this);

        view.findViewById(R.id.btLoad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request r = new Request("https://raw.githubusercontent.com/miguel0/CarlosMiguelParcial2/master/parcial2.json", handler);
                r.start();
            }
        });
    }

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        try {
            JSONArray data = (JSONArray) msg.obj;
            JSONObject obj;

            for (int i = 0; i < data.length(); i++) {
                obj = data.getJSONObject(i);

                people.add(new Person(obj.getString("nombre"), obj.getInt("edad")));
            }

            adapter.notifyDataSetChanged();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
