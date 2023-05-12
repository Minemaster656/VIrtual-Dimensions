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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link VDims#newInstance} factory method to
// * create an instance of this fragment.
// *
// */
public class VDims extends Fragment {
    ArrayList<Dim> dims = new ArrayList<Dim>();

    Button[] dimBuyBtns;
    TextView[] dimCountTxts;
    TextView[] dimMltsTxts;
//    private VirtualDimensionsBinding binding;
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
            while (Thread.currentThread().isAlive()){
                try {
                    Thread.sleep(250);
                    if (dimBuyBtns[0].isPressed()) dims.get(0).buy();
                    if (dimBuyBtns[1].isPressed()) dims.get(1).buy();
                    if (dimBuyBtns[2].isPressed()) dims.get(2).buy();
                    if (dimBuyBtns[3].isPressed()) dims.get(3).buy();
                    if (dimBuyBtns[4].isPressed()) dims.get(4).buy();
                    if (dimBuyBtns[5].isPressed()) dims.get(5).buy();
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
    public void Update_UI(){
        dimBuyBtns[0].setText(rsStr.get("word_buy")+"\n"+rsStr.get("word_price")+": \n"+dims.get(0).price.toString());
        dimBuyBtns[1].setText(rsStr.get("word_buy")+"\n"+rsStr.get("word_price")+": \n"+dims.get(1).price.toString());
        dimBuyBtns[2].setText(rsStr.get("word_buy")+"\n"+rsStr.get("word_price")+": \n"+dims.get(2).price.toString());
        dimBuyBtns[3].setText(rsStr.get("word_buy")+"\n"+rsStr.get("word_price")+": \n"+dims.get(3).price.toString());
        dimBuyBtns[4].setText(rsStr.get("word_buy")+"\n"+rsStr.get("word_price")+": \n"+dims.get(4).price.toString());
        dimBuyBtns[5].setText(rsStr.get("word_buy")+"\n"+rsStr.get("word_price")+": \n"+dims.get(5).price.toString());
        dimCountTxts[0].setText(dims.get(0).count.toString());
        dimCountTxts[1].setText(dims.get(1).count.toString());
        dimCountTxts[2].setText(dims.get(2).count.toString());
        dimCountTxts[3].setText(dims.get(3).count.toString());
        dimCountTxts[4].setText(dims.get(4).count.toString());
        dimCountTxts[5].setText(dims.get(5).count.toString());
        dimMltsTxts[0].setText(dims.get(0).mlt.toString());
        dimMltsTxts[1].setText(dims.get(1).mlt.toString());
        dimMltsTxts[2].setText(dims.get(2).mlt.toString());
        dimMltsTxts[3].setText(dims.get(3).mlt.toString());
        dimMltsTxts[4].setText(dims.get(4).mlt.toString());
        dimMltsTxts[5].setText(dims.get(5).mlt.toString());
    }
    private String getStr(int id) {
        return getResources().getString(id);
    }
    public void initMap(Map m){
        m.put("word_price", getStr(R.string.word_price));
        m.put("word_buy", getStr(R.string.word_buy));
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
//
//    public VDims() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.virtual_dimensions, container, false);
    }
}