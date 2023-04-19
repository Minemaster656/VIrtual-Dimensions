package com.dti.virtualdimensions;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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

        VPClick_Btn.setOnClickListener(v -> vars.VP = Exp.sum(vars.VP, vars.VP_perClick)); //vars.VP+=vars.VP_perClick

        VPDelayUpdate_Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (Exp.cmp(vars.VCl, vars.VP_delayUpd_cost, ">=")){ //vars.VCl >= vars.VP_delayUpd_cost
                    vars.VP_delay++;
                    vars.VCl=Exp.dif(vars.VCl, vars.VP_delayUpd_cost); //vars.VCl-=vars.VP_delayUpd_cost
                    vars.VP_delayUpd_cost = Exp.mlt(vars.VP_delayUpd_cost, Exp.lng2ex(2));
//                    System.out.println("CLICK LISTENER");
                }
            }
        });
        VPDvnUp_Btn.setOnClickListener(v->{if (Exp.cmp(vars.VCl, vars.VP_dvn_UpCost, ">=")){
            vars.VP_dvn=Exp.dif(vars.VP_dvn, Exp.lng2ex(1));
            vars.VCl=Exp.dif(vars.VCl, vars.VP_dvn_UpCost);
            vars.VP_dvn_UpCost= Exp.mlt(vars.VP_dvn_UpCost, Exp.lng2ex(3));
//                    System.out.println("CLICK LISTENER");
        }});
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
        VPCount_Txt.setText(Exp.ex2lbst(vars.VP));
        VCCount_Txt.setText(Exp.ex2lbst(vars.VCl));
        counter++;
        VPDelayUpdate_Btn.setText(
                rsStr.get("Up_delay")
                +"\n"
                +rsStr.get("wrd_cost")
                +": "
                +Exp.ex2lbst(vars.VP_delayUpd_cost)
        );
        VPDvnUp_Btn.setText(rsStr.get("Up_dvn") + "\n" + rsStr.get("wrd_cost")+": " + Exp.ex2lbst(vars.VP_dvn_UpCost));

//        Log.d(TAG, "Update_VP: "+VPDelayUpdate_Btn.getText());
        if (counter>=vars.FPS){

        }
        if (counter>=vars.FPS*(vars.VP_delay+0.5) & !vars.isVPBroken){
            vars.VP=Exp.div(vars.VP, vars.VP_dvn);
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
    }

}