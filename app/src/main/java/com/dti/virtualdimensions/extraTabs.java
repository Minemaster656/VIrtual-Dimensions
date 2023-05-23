package com.dti.virtualdimensions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;


public class extraTabs extends Fragment {

    Button auto;
    Map<String, String> rsStr = new HashMap<String, String>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Update_UI();
        }
    };
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMap(rsStr);
        auto = view.findViewById(R.id.tabAuto);
        auto.setOnClickListener(v->vars.invoke("tab_extra", "MAIN", "open fragment_auto"));
        Runnable holdChecker = () -> {
            while (Thread.currentThread().isAlive()) {
                try {
                    Thread.sleep(1000 / vars.FPS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread holdChcr = new Thread(holdChecker);
        holdChcr.start();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                while (Thread.currentThread().isAlive()) {
                    try {
                        Thread.sleep(1000 / vars.FPS);
                        handler.sendEmptyMessage(1);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    private String getStr(int id) {
        return getResources().getString(id);
    }

    private void Update_UI() {

    }

    public void initMap(Map m) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.extra_tabs, container, false);
    }
}