package com.dti.virtualdimensions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Quarks#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Quarks extends Fragment {
    Button clearTheVoid;
    TextView quarks;
    Button quarksMlt;
    Button quarksToDimMulti;
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
        clearTheVoid = view.findViewById(R.id.clearTheVoid);
        quarks = view.findViewById(R.id.quark_count);
        quarksMlt=view.findViewById(R.id.quarkMulti);
        quarksToDimMulti = view.findViewById(R.id.quarks_to_dimMulti);
        Runnable holdChecker = () -> {
            while (Thread.currentThread().isAlive()) {
                try {
                    Thread.sleep(1000 / vars.FPS);
                    if (quarksMlt.isPressed()) vars.BuyQuarkMlt();
                    if (clearTheVoid.isPressed()) vars.ClearTheVoid();
                    if (quarksToDimMulti.isPressed()) vars.BuyDimMltPerQuarks();
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
//        clearTheVoid.setOnClickListener(v -> {
//            if (vars.quarks.compareTo(BigDecimal.valueOf(1)) >= 0) {
//                vars.q_isVoidCleared = true;
//                vars.quarks = vars.quarks.subtract(BigDecimal.valueOf(1));
//            }
//        });
    }

    private String getStr(int id) {
        return getResources().getString(id);
    }

    private void Update_UI() {
        quarks.setText(rsStr.get("quarks") + ": " + Utils.bd2txt(vars.quarks));
//        if (vars.q_isVoidCleared) {
//            clearTheVoid.setEnabled(false);
////            clearTheVoid.setVisibility(View.GONE);
//        }
        quarksMlt.setText(rsStr.get("quark_x2mlt") + "\n" + rsStr.get("word_price") + ": " + Utils.bd2txt(vars.quarkMltPrice) + "\n" + rsStr.get("word_gain") + ": " + Utils.bd2txt(vars.quarkMlt));
        clearTheVoid.setText(rsStr.get("clearTheVoid") + " " + Utils.bd2txt(vars.clearTheVoidPrice) + "\n" + rsStr.get("word_mult") + ": " + Utils.bd2txt(vars.clearTheVoidBought.multiply(BigDecimal.valueOf(0.5))));
        quarksToDimMulti.setText("+"+Utils.bd2txt(vars.dimMltPerQuark)+" "+rsStr.get("quarksToDimMulti")+"\n"+rsStr.get("word_price")+": "+Utils.bd2txt(vars.dimMltPerQuarkPrice));
//        quarksMlt.setBackgroundTintList(vars.quarkMlt.compareTo(vars.quarkMltPrice)>=0 ? );
//        clearTheVoid.setBackgroundTintList(vars.quarks.compareTo(vars.clearTheVoidPrice)>=0? );
    }

    public void initMap(Map m) {
        m.put("quarks", getStr(R.string.quarks));
        m.put("quark_x2mlt", getStr(R.string.quark_x2mlt));
        m.put("word_price", getStr(R.string.word_price));
        m.put("word_gain", getStr(R.string.word_gain));
        m.put("clearTheVoid", getStr(R.string.clearTheVoid));
        m.put("word_mult", getStr(R.string.word_mult));
        m.put("quarksToDimMulti", getStr(R.string.quarksToDimMulti));
    }
    //    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public Quarks() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Quarks.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Quarks newInstance(String param1, String param2) {
//        Quarks fragment = new Quarks();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.quarks_fragment, container, false);
    }
}