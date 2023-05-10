package com.dti.virtualdimensions;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VD_frg extends Fragment {

    ArrayList<Dim> dims = new ArrayList<Dim>();

    Button[] dimBuyBtns;
    TextView[] dimCountTxts;
    TextView[] dimMltsTxts;
    final byte zero = 0;
    final byte none = -1;
    final byte one = 1;
    Map<String, String> rsStr=new HashMap<String, String>();
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
        dims.add(new Dim(1, 1.1f));
        dims.add(new Dim(100, 1.2f));
        dims.add(new Dim(10000, 1.3f));
        dims.add(new Dim(1000000, 1.4f));
        dims.add(new Dim(100000000, 1.5f));
        dims.add(new Dim(10000000000L, 1.6f));
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
        Runnable holdChecker = () ->{
            while (true){
                try {
                    Thread.sleep(250);
                    if (dimBuyBtns[0].isPressed()) buyDim1();
                    if (dimBuyBtns[1].isPressed()) buyDim2();
                    if (dimBuyBtns[2].isPressed()) buyDim3();
                    if (dimBuyBtns[3].isPressed()) buyDim4();
                    if (dimBuyBtns[4].isPressed()) buyDim5();
                    if (dimBuyBtns[5].isPressed()) buyDim6();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread holdChcr = new Thread(holdChecker);
        holdChcr.run();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                while (true) {
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

    public VD_frg() {
        super(R.layout.virtual_dimensions);
    }
    public void Update_UI(){
        dimBuyBtns[0].setText(rsStr.get("word_buy")+"\n"+rsStr.get("word_price")+": \n"+vars.vDim1_price.toString());
        dimBuyBtns[1].setText(rsStr.get("word_buy")+"\n"+rsStr.get("word_price")+": \n"+vars.vDim2_price.toString());
        dimBuyBtns[2].setText(rsStr.get("word_buy")+"\n"+rsStr.get("word_price")+": \n"+vars.vDim3_price.toString());
        dimBuyBtns[3].setText(rsStr.get("word_buy")+"\n"+rsStr.get("word_price")+": \n"+vars.vDim4_price.toString());
        dimBuyBtns[4].setText(rsStr.get("word_buy")+"\n"+rsStr.get("word_price")+": \n"+vars.vDim5_price.toString());
        dimBuyBtns[5].setText(rsStr.get("word_buy")+"\n"+rsStr.get("word_price")+": \n"+vars.vDim6_price.toString());
        dimCountTxts[0].setText(vars.vDim1_count.toString());
        dimCountTxts[1].setText(vars.vDim2_count.toString());
        dimCountTxts[2].setText(vars.vDim3_count.toString());
        dimCountTxts[3].setText(vars.vDim4_count.toString());
        dimCountTxts[4].setText(vars.vDim5_count.toString());
        dimCountTxts[5].setText(vars.vDim6_count.toString());
        dimMltsTxts[0].setText(vars.vDim1_mlt.toString());
        dimMltsTxts[1].setText(vars.vDim2_mlt.toString());
        dimMltsTxts[2].setText(vars.vDim3_mlt.toString());
        dimMltsTxts[3].setText(vars.vDim4_mlt.toString());
        dimMltsTxts[4].setText(vars.vDim5_mlt.toString());
        dimMltsTxts[5].setText(vars.vDim6_mlt.toString());
    }
    public void buyDim1(){
        if (vars.v_VP.compareTo(vars.vDim1_price)>=0){
            vars.vDim1_count = vars.vDim1_count.add(vars.vBuyMlt);
            vars.v_VP=vars.v_VP.subtract(vars.vDim1_price);
            vars.vDim1_price=vars.vDim1_price.multiply(vars.vDim1_priceUp);
        }
    }
    public void buyDim2(){
        if (vars.v_VP.compareTo(vars.vDim2_price)>=0){
            vars.vDim2_count = vars.vDim2_count.add(vars.vBuyMlt);
            vars.v_VP=vars.v_VP.subtract(vars.vDim2_price);
            vars.vDim2_price=vars.vDim2_price.multiply(vars.vDim2_priceUp);
        }
    }
    public void buyDim3(){
        if (vars.v_VP.compareTo(vars.vDim3_price)>=0){
            vars.vDim3_count = vars.vDim3_count.add(vars.vBuyMlt);
            vars.v_VP=vars.v_VP.subtract(vars.vDim3_price);
            vars.vDim3_price=vars.vDim3_price.multiply(vars.vDim3_priceUp);
        }
    }
    public void buyDim4(){
        if (vars.v_VP.compareTo(vars.vDim4_price)>=0){
            vars.vDim4_count = vars.vDim4_count.add(vars.vBuyMlt);
            vars.v_VP=vars.v_VP.subtract(vars.vDim4_price);
            vars.vDim4_price=vars.vDim4_price.multiply(vars.vDim4_priceUp);
        }
    }
    public void buyDim5(){
        if (vars.v_VP.compareTo(vars.vDim5_price)>=0){
            vars.vDim5_count = vars.vDim5_count.add(vars.vBuyMlt);

            vars.v_VP=vars.v_VP.subtract(vars.vDim5_price);
            vars.vDim5_price=vars.vDim5_price.multiply(vars.vDim5_priceUp);
        }
    }
    public void buyDim6(){
        if (vars.v_VP.compareTo(vars.vDim6_price)>=0){
            vars.vDim6_count = vars.vDim6_count.add(vars.vBuyMlt);
            vars.v_VP=vars.v_VP.subtract(vars.vDim6_price);
            vars.vDim6_price=vars.vDim6_price.multiply(vars.vDim6_priceUp);
        }
    }
    private String getStr(int id) {
        return getResources().getString(id);
    }
    public void initMap(Map m){
        m.put("word_price", getStr(R.string.word_price));
        m.put("word_buy", getStr(R.string.word_buy));
    }
}
