package com.dti.virtualdimensions;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.lang.Thread;
import java.util.HashMap;
import java.util.Map;

public class VP_Frg extends Fragment {

    Map<String, String> rsStr = new HashMap<String, String>();
    private static final String TAG = "VP_Frg";

    Button VPClick_Btn;
    TextView VPCount_Txt;
    TextView VCCount_Txt;
    Button VPDelayUpdate_Btn;
    Button VPDvnUp_Btn;
    Button VPCPUp_Btn;
    int counter=0;
    boolean isVPClick_holded;
    boolean isVPClick_holded1=true;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Update_VP();
        }
    };
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        InitMap(rsStr);
        super.onViewCreated(view, savedInstanceState);
        VPCount_Txt = view.findViewById(R.id.VP_count);
        VCCount_Txt = view.findViewById(R.id.VC_count);
        VPDelayUpdate_Btn = view.findViewById(R.id.VP_delayUpgrade);
        VPClick_Btn = view.findViewById(R.id.clickMe_vp);
        VPDvnUp_Btn = view.findViewById(R.id.VP_dvnUpgrade);
        VPCPUp_Btn = view.findViewById(R.id.clickUpgrade);

//        VPClick_Btn.setOnClickListener(v -> {
//            vars.VP+=vars.VP_perClick;
////            Log.d(TAG, "CLICK!");
//        }); //vars.VP+=vars.VP_perClick

        VPClick_Btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int evT = event.getAction();
                if (evT == MotionEvent.ACTION_BUTTON_PRESS){
                    ButtonVP_clickHold();
                }
                else if(evT==MotionEvent.ACTION_BUTTON_RELEASE){

                }
                return false;

            }

        });
        VPDelayUpdate_Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (vars.VCl >= vars.VP_delayUpd_cost){
                    vars.VP_delay++;
                    vars.VCl-=vars.VP_delayUpd_cost;
                    vars.VP_delayUpd_cost*=2;
//                    System.out.println("CLICK LISTENER");
                }
            }
        });
        VPDvnUp_Btn.setOnClickListener(v->{if (vars.VCl >= vars.VP_dvn_UpCost){
            vars.VP_dvn--;
            vars.VCl-=vars.VP_dvn_UpCost;
            vars.VP_dvn_UpCost*=3;
//                    System.out.println("CLICK LISTENER");
        }});
        VPCPUp_Btn.setOnClickListener(v->{
            if (vars.VCl >= vars.VP_perClick_upCost){
                if (vars.VP_perClick>10) {
                    vars.VP_perClick *= 1.1;
                }
                else{
                    vars.VP_perClick+=2;
                }
                vars.VCl-=vars.VP_perClick_upCost;
                vars.VP_perClick_upCost*=2;
            }
        });
        //🈷🈷🈷🈷🈷 добавь функциоал кнопки чилы клика!!!
//        VPDelayUpdate_Btn.setOnClickListener(v -> {
//            if (vars.VCl >= vars.VP_delayUpd_cost){
//                vars.VP_delay++;
//                vars.VCl-=vars.VP_delayUpd_cost;
//                vars.VP_delayUpd_cost*=2;
//                System.out.println("CLICK LISTENER");
//            }
//        });
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                while (true){
                    try {
                        Thread.sleep(1000/vars.FPS);
                        handler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }}
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }


    public VP_Frg(){

        super(R.layout.virtual_particles);


    }
    public void Update_VP(){
        VPCount_Txt.setText(Double.toString(vars.VP));
        VCCount_Txt.setText(Double.toString(vars.VCl));
        counter++;
        VPDelayUpdate_Btn.setText(
                rsStr.get("Up_delay")
                +"\n"
                +rsStr.get("wrd_cost")
                +": "
                +Double.toString(vars.VP_delayUpd_cost)
        );
        VPDvnUp_Btn.setText(rsStr.get("Up_dvn") + "\n" + rsStr.get("wrd_cost")+": " + Double.toString(vars.VP_dvn_UpCost));
        VPCPUp_Btn.setText(rsStr.get("CP_up")+"\n"+rsStr.get("wrd_cost")+": "+vars.VP_perClick_upCost);

//        Log.d(TAG, "Update_VP: "+VPDelayUpdate_Btn.getText());
        if (counter>=vars.FPS){

        }
        if (counter>=vars.FPS*(vars.VP_delay+0.5) & !vars.isVPBroken){
            vars.VP/=vars.VP_dvn;
            counter=0;
        }
    }
    private String getStr(int id){
        return getResources().getString(id);
    }
    private void InitMap(Map m){
        m.put("Up_delay", getStr(R.string.VPUpgrade_delay));
        m.put("wrd_cost", getStr(R.string.word_cost));
        m.put("Up_dvn", getStr(R.string.VPUpgrade_dvn));
        m.put("CP_up", getStr(R.string.VPUpgrade_cp));
    }
    public void ButtonVP_clickHold(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                while (true){
                    try {
                        Thread.sleep((1000/vars.FPS)*5);
                        vars.VP+=vars.VP_perClick;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }}
            }
        };
        Thread thread = new Thread(runnable);
        if (!isVPClick_holded&isVPClick_holded1){


        thread.start(); isVPClick_holded=true;}
        if(isVPClick_holded1==false){
            thread.stop();
            isVPClick_holded1=true;
        }

    }
    public void OnButtonVP_clickRelease(){
        isVPClick_holded1=false;
        ButtonVP_clickHold();
    }

}