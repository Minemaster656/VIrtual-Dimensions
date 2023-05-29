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
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link VDims#newInstance} factory method to
// * create an instance of this fragment.
// */
public class VDims extends Fragment {


    Button[] dimBuyBtns = new Button[6];
    TextView[] dimCountTxts = new TextView[6];
    TextView[] dimMltsTxts = new TextView[6];

    Map<String, String> rsStr = new HashMap<String, String>();
    Button max;
    Button xam;
    TextView VP_count;
    TextView tickspeedDisplay;
    Button buyTickspeed;
    Button collapse;
    Button Annihilate;
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
        max = view.findViewById(R.id.max);
        xam = view.findViewById(R.id.xam);
        VP_count = view.findViewById(R.id.v_VP_count);
        dimBuyBtns[0] = view.findViewById(R.id.buyDim1);
        dimBuyBtns[1] = view.findViewById(R.id.buyDim2);
        dimBuyBtns[2] = view.findViewById(R.id.buyDim3);
        dimBuyBtns[3] = view.findViewById(R.id.buyDim4);
        dimBuyBtns[4] = view.findViewById(R.id.buyDim5);
        dimBuyBtns[5] = view.findViewById(R.id.buyDim6);
        dimCountTxts[0] = view.findViewById(R.id.dim1Count);
        dimCountTxts[1] = view.findViewById(R.id.dim2Count);
        dimCountTxts[2] = view.findViewById(R.id.dim3Count);
        dimCountTxts[3] = view.findViewById(R.id.dim4Count);
        dimCountTxts[4] = view.findViewById(R.id.dim5Count);
        dimCountTxts[5] = view.findViewById(R.id.dim6Count);
        dimMltsTxts[0] = view.findViewById(R.id.dim1Mlt);
        dimMltsTxts[1] = view.findViewById(R.id.dim2Mlt);
        dimMltsTxts[2] = view.findViewById(R.id.dim3Mlt);
        dimMltsTxts[3] = view.findViewById(R.id.dim4Mlt);
        dimMltsTxts[4] = view.findViewById(R.id.dim5Mlt);
        dimMltsTxts[5] = view.findViewById(R.id.dim6Mlt);
        buyTickspeed = view.findViewById(R.id.buyTickspeed);
        tickspeedDisplay = view.findViewById(R.id.tickspeed);
        collapse = view.findViewById(R.id.Collapse);
        Annihilate=view.findViewById(R.id.Annihilate);
        Runnable holdChecker = () -> {
            while (Thread.currentThread().isAlive()) {
                try {
                    Thread.sleep(1000 / vars.FPS);
                    if (dimBuyBtns[0].isPressed()) vars.dims.get(0).buy();
                    if (dimBuyBtns[1].isPressed()) vars.dims.get(1).buy();
                    if (dimBuyBtns[2].isPressed()) vars.dims.get(2).buy();
                    if (dimBuyBtns[3].isPressed()) vars.dims.get(3).buy();
                    if (dimBuyBtns[4].isPressed()) vars.dims.get(4).buy();
                    if (dimBuyBtns[5].isPressed()) vars.dims.get(5).buy();
                    if (buyTickspeed.isPressed())
                        if (vars.v_VP.compareTo(vars.v_tickspeedPrice) >= 0) {
                            vars.v_VP = vars.v_VP.subtract(vars.v_tickspeedPrice);
                            vars.v_tickspeedPrice = vars.v_tickspeedPrice.multiply(BigDecimal.valueOf(10));
                            vars.v_tickspeedBought = vars.v_tickspeedBought.add(BigDecimal.valueOf(1));
                        }
                    if (max.isPressed()) {
                        for (int i = 0; i < 6; i++) {
                            vars.dims.get(i).buy();
                        }
                        vars.buyTickspeed_();
                    }
                    if (xam.isPressed()) {
                        vars.buyTickspeed_();
                        for (int i = 5; i >= 0; i--) {
                            vars.dims.get(i).buy();
                        }
                    }
                    if (collapse.isPressed()){
                        vars.doCollapse();
                    }
                    if(Annihilate.isPressed()){
                        vars.doAnnihilate();
                    }
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

    public void Update_UI() {
        for (int i = 0; i < 6; i++) {
            dimBuyBtns[i].setText(rsStr.get("word_buy") + "\n" + rsStr.get("word_price") + ": \n" + Utils.bd2txt(vars.dims.get(i).price));
            dimCountTxts[i].setText(Utils.bd2txt(vars.dims.get(i).count));
            dimMltsTxts[i].setText(Utils.bd2txt(vars.dims.get(i).mlt));
        }
        VP_count.setText(Utils.bd2txt(vars.v_VP));
        tickspeedDisplay.setText("" + rsStr.get("tickspeed") + ": " + Utils.bd2txt(vars.v_tickspeed));
        buyTickspeed.setText(rsStr.get("word_buy") + "! " + rsStr.get("word_price") + ":\n" + Utils.bd2txt(vars.v_tickspeedPrice));
//        Annihilate.setText(rsStr.get("annihilate")+" \n"+rsStr.get("word_get")+": "+Utils.bd2txt(vars.quarksOnAnnihilate)+rsStr.get("quarks"));
        if(vars.q_isUnlocked||(vars.v_VP.compareTo(BigDecimal.valueOf(1E100))>=0)){
            Annihilate.setEnabled(true);
            Annihilate.setVisibility(View.VISIBLE);
        } else {
            Annihilate.setEnabled(false);
            Annihilate.setVisibility(View.INVISIBLE);
        }
        collapse.setText(rsStr.get("collapse")+"("+Utils.bd2txt(vars.vCollapse_mlt)+" "+rsStr.get("dim")+" "+rsStr.get("word_mult")+")\n"+rsStr.get("word_price")+": " +Utils.bd2txt(vars.vCollapse_price));
        collapse.setEnabled(vars.vCollapse_price.compareTo(vars.v_VP) >= 0);
        Annihilate.setText(rsStr.get("annihilateVPfor")+" "+Utils.bd2txt(vars.quarksOnAnnihilate)+rsStr.get("quarks_tab"));
        Annihilate.setEnabled(vars.quarksOnAnnihilate.compareTo(BigDecimal.ZERO) > 0);

//        collapse.setText();
    }

    private String getStr(int id) {
        return getResources().getString(id);
    }

    public void initMap(Map m) {
        m.put("word_price", getStr(R.string.word_price));
        m.put("word_buy", getStr(R.string.word_buy));
        m.put("tickspeed", getStr(R.string.tickspeed));
        m.put("word_gain",getStr(R.string.word_gain));
        m.put("collapse",getStr(R.string.collapse));
        m.put("collapse_full",getStr(R.string.collapse_full));
        m.put("annihilate",getStr(R.string.annihilate));
        m.put("word_get",R.string.word_get);
        m.put("quarks", R.string.quarks);
        m.put("annihilateVPfor", getStr(R.string.annihilateVPfor));
        m.put("dim",getStr(R.string.dim));
        m.put("word_mult", getStr(R.string.word_mult));
        m.put("quarks_tab",getStr(R.string.quarks_tab));
    }

    //    public VDims() {
//        super(R.layout.virtual_dimensions);
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.virtual_dimensions, container, false);

    }


//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public VDims() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment VDims.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static VDims newInstance(String param1, String param2) {
//        VDims fragment = new VDims();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }


}