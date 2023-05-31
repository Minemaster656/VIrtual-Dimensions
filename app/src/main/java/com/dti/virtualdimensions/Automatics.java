package com.dti.virtualdimensions;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@SuppressLint("ClickableViewAccessibility")
public class Automatics extends Fragment {

    Map<String, String> rsStr = new HashMap<String, String>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (isAdded()) {
                Update_UI();
            }
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
    Button aAnnihilate;
    EditText aAnnihilateData;
    Button submitAAD;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMap(rsStr);
        ad1 = view.findViewById(R.id.AbuyDim1);
        ad2 = view.findViewById(R.id.AbuyDim2);
        ad3 = view.findViewById(R.id.AbuyDim3);
        ad4 = view.findViewById(R.id.AbuyDim4);
        ad5 = view.findViewById(R.id.AbuyDim5);
        ad6 = view.findViewById(R.id.AbuyDim6);
        aTick = view.findViewById(R.id.AbuyTickspeed);
        aCollapse = view.findViewById(R.id.AbuyCollapse);
        aAnnihilate = view.findViewById(R.id.AbuyAnnihilate);
        aAnnihilateData = view.findViewById(R.id.AbuyAnnihilateDATA);
        submitAAD=view.findViewById(R.id.submitAADATA);
        submitAAD.setText(Utils.bd2txt(vars.numExtraAutoData.get(2)));
        Log.i(TAG, "onViewCreated: sAAD text: "+Utils.bd2txt(vars.numExtraAutoData.get(2)));
        ad1.setOnClickListener(v -> {
            if (vars.dimAutoUnlocks[0]) {
                vars.dimAutoToggles[0] = !vars.dimAutoToggles[0];
            } else {
                if (vars.v_VP.compareTo(vars.dimAutoPrices[0]) >= 0) {
                    vars.v_VP = vars.v_VP.subtract(vars.dimAutoPrices[0]);
                    vars.dimAutoUnlocks[0] = true;
                }
            }
        });
        ad2.setOnClickListener(v -> {
            if (vars.dimAutoUnlocks[1]) {
                vars.dimAutoToggles[1] = !vars.dimAutoToggles[1];
            } else {
                if (vars.v_VP.compareTo(vars.dimAutoPrices[1]) >= 0) {
                    vars.v_VP = vars.v_VP.subtract(vars.dimAutoPrices[1]);
                    vars.dimAutoUnlocks[1] = true;
                }
            }
        });
        ad3.setOnClickListener(v -> {
            if (vars.dimAutoUnlocks[2]) {
                vars.dimAutoToggles[2] = !vars.dimAutoToggles[2];
            } else {
                if (vars.v_VP.compareTo(vars.dimAutoPrices[2]) >= 0) {
                    vars.v_VP = vars.v_VP.subtract(vars.dimAutoPrices[2]);
                    vars.dimAutoUnlocks[2] = true;
                }
            }
        });
        ad4.setOnClickListener(v -> {
            if (vars.dimAutoUnlocks[3]) {
                vars.dimAutoToggles[3] = !vars.dimAutoToggles[3];
            } else {
                if (vars.v_VP.compareTo(vars.dimAutoPrices[3]) >= 0) {
                    vars.v_VP = vars.v_VP.subtract(vars.dimAutoPrices[3]);
                    vars.dimAutoUnlocks[3] = true;
                }
            }
        });
        ad5.setOnClickListener(v -> {
            if (vars.dimAutoUnlocks[4]) {
                vars.dimAutoToggles[4] = !vars.dimAutoToggles[4];
            } else {
                if (vars.v_VP.compareTo(vars.dimAutoPrices[4]) >= 0) {
                    vars.v_VP = vars.v_VP.subtract(vars.dimAutoPrices[4]);
                    vars.dimAutoUnlocks[4] = true;
                }
            }
        });
        ad6.setOnClickListener(v -> {
            if (vars.dimAutoUnlocks[5]) {
                vars.dimAutoToggles[5] = !vars.dimAutoToggles[5];
            } else {
                if (vars.v_VP.compareTo(vars.dimAutoPrices[5]) >= 0) {
                    vars.v_VP = vars.v_VP.subtract(vars.dimAutoPrices[5]);
                    vars.dimAutoUnlocks[5] = true;
                }
            }
        });
        aTick.setOnClickListener(v -> {
            if (vars.extraAutoUnlocks.get(0)) {
                vars.extraAutoToggles.set(0, !vars.extraAutoToggles.get(0));
            } else {
                if (vars.v_VP.compareTo(vars.extraAutoPrices.get(0)) >= 0) {
                    vars.v_VP = vars.v_VP.subtract(vars.extraAutoPrices.get(0));
                    vars.extraAutoUnlocks.set(0, true);
                }
            }
        });
        aCollapse.setOnClickListener(v -> {
            if (vars.extraAutoUnlocks.get(1)) {
                vars.extraAutoToggles.set(1, !vars.extraAutoToggles.get(1));
            } else {
                if (vars.v_VP.compareTo(vars.extraAutoPrices.get(1)) >= 0) {
                    vars.v_VP = vars.v_VP.subtract(vars.extraAutoPrices.get(1));
                    vars.extraAutoUnlocks.set(1, true);

                }
            }
        });
        aAnnihilate.setOnClickListener(v -> {
            if (vars.extraAutoUnlocks.get(2)) {
                vars.extraAutoToggles.set(2, !vars.extraAutoToggles.get(2));
            } else {
                if (vars.quarks.compareTo(vars.extraAutoPrices.get(2)) >= 0) {
                    vars.quarks = vars.quarks.subtract(vars.extraAutoPrices.get(2));
                    vars.extraAutoUnlocks.set(2, true);

                }
            }
        });
        submitAAD.setOnClickListener(v->{
            try {
                //if (!(aAnnihilateData.getText().toString().compareTo("") > 0)){
                    vars.numExtraAutoData.set(2, new BigDecimal(aAnnihilateData.getText().toString()));//}
                Log.i(TAG, "onViewCreated: "+aAnnihilateData.getText().toString());
            }catch (NumberFormatException e){
                Log.e(TAG, "onViewCreated: " + e.toString() + "   "+aAnnihilateData.getText().toString());
            }
        });
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
        if (vars.dimAutoUnlocks[0]) {
            ad1.setText(rsStr.get("autobyer") + ": " + rsStr.get("dim1"));
        } else {
            ad1.setText(rsStr.get("autobyer") + ": " + rsStr.get("dim1") + " \n" + rsStr.get("word_price") + ": " + Utils.bd2txt(vars.dimAutoPrices[0]));
        }
        if (vars.dimAutoToggles[0] & vars.dimAutoUnlocks[0])
            ad1.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoDim));
        else
            ad1.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoDisabled));

        if (vars.dimAutoUnlocks[1]) {
            ad2.setText(rsStr.get("autobyer") + ": " + rsStr.get("dim2"));
        } else {
            ad2.setText(rsStr.get("autobyer") + ": " + rsStr.get("dim2") + " \n" + rsStr.get("word_price") + ": " + Utils.bd2txt(vars.dimAutoPrices[1]));
        }
        if (vars.dimAutoToggles[1] & vars.dimAutoUnlocks[1])
            ad2.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoDim));
        else
            ad2.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoDisabled));

        if (vars.dimAutoUnlocks[2]) {
            ad3.setText(rsStr.get("autobyer") + ": " + rsStr.get("dim3"));
        } else {
            ad3.setText(rsStr.get("autobyer") + ": " + rsStr.get("dim3") + " \n" + rsStr.get("word_price") + ": " + Utils.bd2txt(vars.dimAutoPrices[2]));
        }
        if (vars.dimAutoToggles[2] & vars.dimAutoUnlocks[2])
            ad3.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoDim));
        else
            ad3.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoDisabled));

        if (vars.dimAutoUnlocks[3]) {
            ad4.setText(rsStr.get("autobyer") + ": " + rsStr.get("dim4"));
        } else {
            ad4.setText(rsStr.get("autobyer") + ": " + rsStr.get("dim4") + " \n" + rsStr.get("word_price") + ": " + Utils.bd2txt(vars.dimAutoPrices[3]));
        }
        if (vars.dimAutoToggles[3] & vars.dimAutoUnlocks[3])
            ad4.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoDim));
        else
            ad4.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoDisabled));

        if (vars.dimAutoUnlocks[4]) {
            ad5.setText(rsStr.get("autobyer") + ": " + rsStr.get("dim5"));
        } else {
            ad5.setText(rsStr.get("autobyer") + ": " + rsStr.get("dim5") + " \n" + rsStr.get("word_price") + ": " + Utils.bd2txt(vars.dimAutoPrices[4]));
        }
        if (vars.dimAutoToggles[4] & vars.dimAutoUnlocks[4])
            ad5.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoDim));
        else
            ad5.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoDisabled));

        if (vars.dimAutoUnlocks[5]) {
            ad6.setText(rsStr.get("autobyer") + ": " + rsStr.get("dim6"));
        } else {
            ad6.setText(rsStr.get("autobyer") + ": " + rsStr.get("dim6") + " \n" + rsStr.get("word_price") + ": " + Utils.bd2txt(vars.dimAutoPrices[5]));
        }
        if (vars.dimAutoToggles[5] & vars.dimAutoUnlocks[5])
            ad6.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoDim));
        else
            ad6.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoDisabled));

        if (vars.extraAutoUnlocks.get(0)) {
            aTick.setText(rsStr.get("autobyer") + ": " + rsStr.get("tickspeed"));
        } else {
            aTick.setText(rsStr.get("autobyer") + ": " + rsStr.get("tickspeed") + " \n" + rsStr.get("word_price") + ": " + Utils.bd2txt(vars.extraAutoPrices.get(0)));
        }
        if (vars.extraAutoToggles.get(0) & vars.extraAutoUnlocks.get(0))
            aTick.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoSpec));
        else
            aTick.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoDisabled));

        if (vars.extraAutoUnlocks.get(1)) {
            aCollapse.setText(rsStr.get("autobyer") + ": " + rsStr.get("collapse"));
        } else {
            aCollapse.setText(rsStr.get("autobyer") + ": " + rsStr.get("collapse") + " \n" + rsStr.get("word_price") + ": " + Utils.bd2txt(vars.extraAutoPrices.get(1)));
        }
        if (vars.extraAutoToggles.get(1) & vars.extraAutoUnlocks.get(1))
            aCollapse.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoSpec));
        else
            aCollapse.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoDisabled));


        if (vars.extraAutoUnlocks.get(2)) {
            aAnnihilate.setText(rsStr.get("autoAnnihilator") + "\n" + rsStr.get("annihilateOn")+" "+Utils.bd2txt(vars.numExtraAutoData.get(2))+ " "+rsStr.get("quarks_tab"));
        } else {
            aAnnihilate.setText(rsStr.get("autoAnnihilator") + " \n" + rsStr.get("word_price") + ": " + Utils.bd2txt(vars.extraAutoPrices.get(2))+" "+rsStr.get("quarks_tab"));
        }
        if (vars.extraAutoToggles.get(2) & vars.extraAutoUnlocks.get(2)) {
            aAnnihilate.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.quark_bg));

        }
        else
            aAnnihilate.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.autoDisabled));
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
        m.put("autoAnnihilator", getStr(R.string.autoAnnihilator));
        m.put("annihilateOn", getStr(R.string.annihilateOn));
        m.put("quarks_tab", getStr(R.string.quarks_tab));
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