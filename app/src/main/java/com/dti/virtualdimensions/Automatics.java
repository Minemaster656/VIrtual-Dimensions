package com.dti.virtualdimensions;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.os.TokenWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@SuppressLint("ClickableViewAccessibility")
public class Automatics extends Fragment {

    Map<String, String> rsStr = new HashMap<String, String>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Update_UI();
        }
    };
//    ArrayList<TextView> auto = new ArrayList<TextView>();
//    ArrayList<View> auto_bg = new ArrayList<View>();
    Button ad1;
    Button ad2;
    Button ad3;
    Button ad4;
    Button ad5;
    Button ad6;
    Button aTick;
    Button aCollapse;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMap(rsStr);
        ad1=view.findViewById(R.id.AbuyDim1);
        ad2=view.findViewById(R.id.AbuyDim2);
        ad3=view.findViewById(R.id.AbuyDim3);
        ad4=view.findViewById(R.id.AbuyDim4);
        ad5=view.findViewById(R.id.AbuyDim5);
        ad6=view.findViewById(R.id.AbuyDim6);
        aTick=view.findViewById(R.id.AbuyTickspeed);
        aCollapse=view.findViewById(R.id.AbuyCollapse);



//        auto.add(view.findViewById(R.id.autoDim1b));
//        auto.add(view.findViewById(R.id.autoDim2b));
//        auto.add(view.findViewById(R.id.autoDim3b));
//        auto.add(view.findViewById(R.id.autoDim4b));
//        auto.add(view.findViewById(R.id.autoDim5b));
//        auto.add(view.findViewById(R.id.autoDim6b));
//        auto_bg.add(view.findViewById(R.id.autoDim1));
//        auto_bg.add(view.findViewById(R.id.autoDim2));
//        auto_bg.add(view.findViewById(R.id.autoDim3));
//        auto_bg.add(view.findViewById(R.id.autoDim4));
//        auto_bg.add(view.findViewById(R.id.autoDim5));
//        auto_bg.add(view.findViewById(R.id.autoDim6));
//
//        auto.add(view.findViewById(R.id.autoTickb));
//        auto.add(view.findViewById(R.id.autoCollapseb));
//        auto_bg.add(view.findViewById(R.id.autoTick));
//        auto_bg.add(view.findViewById(R.id.autoCollapse));


//        for (int i = 0; i < 6; i++) {
//            int finalI = i;
//            Log.w(TAG, "Try to attach CL: " + finalI);
//        auto.get(0).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    if (vars.dimAutoUnlocks[0]) {
//                        vars.dimAutoToggles[0] = !vars.dimAutoToggles[0];
//                    } else {
//                        if (vars.v_VP.compareTo(vars.dimAutoPrices[0]) >= 0) vars.v_VP = vars.v_VP.subtract(vars.dimAutoPrices[0]);
//                    }
//                }
//                return false;
//
//            }
//        });
//        auto.get(1).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    if (vars.dimAutoUnlocks[1]) {
//                        vars.dimAutoToggles[1] = !vars.dimAutoToggles[1];
//                    } else {
//                        if (vars.v_VP.compareTo(vars.dimAutoPrices[1]) >= 0) vars.v_VP = vars.v_VP.subtract(vars.dimAutoPrices[1]);
//                    }
//                }
//                return false;
//
//            }
//        });
//        auto.get(2).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    if (vars.dimAutoUnlocks[2]) {
//                        vars.dimAutoToggles[2] = !vars.dimAutoToggles[2];
//                    } else {
//                        if (vars.v_VP.compareTo(vars.dimAutoPrices[2]) >= 0) vars.v_VP = vars.v_VP.subtract(vars.dimAutoPrices[2]);
//                    }
//                }
//                return false;
//
//            }
//        });
//        auto.get(3).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    if (vars.dimAutoUnlocks[3]) {
//                        vars.dimAutoToggles[3] = !vars.dimAutoToggles[3];
//                    } else {
//                        if (vars.v_VP.compareTo(vars.dimAutoPrices[3]) >= 0) vars.v_VP = vars.v_VP.subtract(vars.dimAutoPrices[3]);
//                    }
//                }
//                return false;
//
//            }
//        });
//        auto.get(4).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    if (vars.dimAutoUnlocks[4]) {
//                        vars.dimAutoToggles[4] = !vars.dimAutoToggles[4];
//                    } else {
//                        if (vars.v_VP.compareTo(vars.dimAutoPrices[4]) >= 0) vars.v_VP = vars.v_VP.subtract(vars.dimAutoPrices[4]);
//                    }
//                }
//                return false;
//
//            }
//        });
//        auto.get(5).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    if (vars.dimAutoUnlocks[5]) {
//                        vars.dimAutoToggles[5] = !vars.dimAutoToggles[5];
//                    } else {
//                        if (vars.v_VP.compareTo(vars.dimAutoPrices[5]) >= 0) vars.v_VP = vars.v_VP.subtract(vars.dimAutoPrices[5]);
//                    }
//                }
//                return false;
//
//            }
//        });
//        }
//        auto.get(6).setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    if (vars.extraAutoUnlocks.get(0)) {
//
//                        vars.extraAutoToggles.set(0, !vars.extraAutoToggles.get(0));
//
//                    } else {
//                        if (vars.v_VP.compareTo(vars.extraAutoPrices.get(0)) >= 0) {
//                            vars.v_VP = vars.v_VP.subtract(vars.extraAutoPrices.get(0));
//                        }
//                    }
//
//                }
//                return false;
//            }
//        });
//        auto.get(7).setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    if (vars.extraAutoUnlocks.get(1)) {
//
//                        vars.extraAutoToggles.set(1, !vars.extraAutoToggles.get(1));
//
//                    } else {
//                        if (vars.v_VP.compareTo(vars.extraAutoPrices.get(1)) >= 1) {
//                            vars.v_VP = vars.v_VP.subtract(vars.extraAutoPrices.get(1));
//                        }
//                    }
//
//                }
//                return false;
//            }
//        });


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
        for (int i = 0; i < 6; i++) {
            String dimnum = "dim" + (i + 1);
            if (vars.dimAutoUnlocks[i]) {

                auto.get(i).setText(rsStr.get("autobyer") + ": " + rsStr.get(dimnum));
            } else {
                auto.get(i).setText(rsStr.get("autobyer") + ": " + rsStr.get(dimnum) + " \n" + rsStr.get("word_price") + ": " + Utils.bd2txt(vars.dimAutoPrices[i]));
            }
//            if (vars.dimAutoUnlocks[i] & vars.dimAutoToggles[i])
//                auto.get(i).setTextColor(getResources().getColor(R.color.black));
//            else auto.get(i).setTextColor(getResources().getColor(R.color.autoDisabled));
        }
        if (vars.extraAutoToggles.get(0)) {
            auto.get(6).setText(rsStr.get("autobyer") + ": " + rsStr.get("tickspeed"));
        } else {
            auto.get(6).setText(rsStr.get("autobyer") + ": " + rsStr.get("tickspeed") + " \n" + rsStr.get("word_price") + ": " + Utils.bd2txt(vars.extraAutoPrices.get(0)));
        }
//        if (vars.extraAutoUnlocks.get(0) & vars.extraAutoToggles.get(0))
//            auto.get(6).setTextColor(getResources().getColor(R.color.black));
//        else auto.get(6).setTextColor(getResources().getColor(R.color.autoDisabled));
        if (vars.extraAutoToggles.get(1)) {
            auto.get(7).setText(rsStr.get("autobyer") + ": " + rsStr.get("collapse"));
        } else {
            auto.get(7).setText(rsStr.get("autobyer") + ": " + rsStr.get("collapse") + "\n" + rsStr.get("word_price") + ": " + Utils.bd2txt(vars.extraAutoPrices.get(1)));
        }
//        if (vars.extraAutoUnlocks.get(1) & vars.extraAutoToggles.get(1))
//            auto.get(7).setTextColor(getResources().getColor(R.color.black));
//        else auto.get(7).setTextColor(getResources().getColor(R.color.autoDisabled));
    }

    public void initMap(Map m) {
        m.put("dim1", getStr(R.string.dim1));
        m.put("dim2", getStr(R.string.dim2));
        m.put("dim3", getStr(R.string.dim3));
        m.put("dim4", getStr(R.string.dim4));
        m.put("dim5", getStr(R.string.dim5));
        m.put("dim6", getStr(R.string.dim6));
        m.put("dim7", getStr(R.string.dim6));
        m.put("collapse", getStr(R.string.collapse));
        m.put("autobyer", getStr(R.string.autobyer));
        m.put("tickspeed", getStr(R.string.tickspeed));
        m.put("word_price", getStr(R.string.word_price));
    }


    public Automatics() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_automatics, container, false);
    }
}