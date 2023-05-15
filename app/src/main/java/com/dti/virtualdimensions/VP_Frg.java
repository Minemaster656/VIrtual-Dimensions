package com.dti.virtualdimensions;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView VP_div_display;
    TextView VP_cp_display;
    TextView VP_cd_display;
    Button prestige0_btn;
    Button prestige0_btn_mlt;
    Button extraMlt;
    Button VCl_costMlt_decrease;
    Button BREAKQPHYS;
    Button DESTROY_VP;//переменные интерфейса
    int counter = 0; //Delay counter
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Update_VP();
        }
    }; //хандлер для обновления интерфейса

    @SuppressLint("ClickableViewAccessibility")
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
        VP_div_display = view.findViewById(R.id.VP_div_display);
        VP_cp_display = view.findViewById(R.id.VP_cp_display);
        VP_cd_display = view.findViewById(R.id.VP_cd_display);
        prestige0_btn = view.findViewById(R.id.prestige0);
        prestige0_btn_mlt=view.findViewById(R.id.prestige0_upMlt);
        extraMlt=view.findViewById(R.id.CPmultiplier);
        VCl_costMlt_decrease=view.findViewById(R.id.VCl_mlt_decrease);
        BREAKQPHYS=view.findViewById(R.id.VP_brakePhys);
        DESTROY_VP=view.findViewById(R.id.toPhase1);//поиск интерфейса

        @SuppressLint("ClickableViewAccessibility")
        Runnable VP_press = () -> {
            while (Thread.currentThread().isAlive()) {
                while (VPClick_Btn.isPressed()) {
                    //Log.d(TAG, "PRESSED!!!");
                    try {
                        Thread.sleep(Math.round((1000 / vars.FPS) * 4));
                        vars.VP += 1 * vars.VP_perCLick_mlt_total;//vars.VP_perClick*vars.VP_perCLick_mlt_total;
                        if(vars.isVPPhaseDestroyed) break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        prestige0_btn_mlt.setOnClickListener(v->{
            if(vars.VCl>=vars.VP_prestige0_mlt_cost){
                vars.VP_prestige0_mlt*=2;
                vars.VCl-=vars.VP_prestige0_mlt_cost;
                if (vars.VP_prestige0_mlt_cost<=1000){
                vars.VP_prestige0_mlt_cost*=5;}
                else{
                    vars.VP_prestige0_mlt_cost*=10;
                }
            }
        });
        DESTROY_VP.setOnClickListener(v->{
            vars.isVPPhaseDestroyed=true;
        });
        BREAKQPHYS.setOnClickListener(v -> {
            if(vars.VCl>=200&&vars.VP_dvn<=5&&!vars.isVPBroken){
                vars.VCl-=200;
                vars.VP_dvn=1;
                vars.isVPBroken=true;
                Toast.makeText(requireContext(), getStr(R.string.BREAK_QPHYS_toast), Toast.LENGTH_LONG).show();

            }
        });
        VCl_costMlt_decrease.setOnClickListener(v->{if(vars.VCl>=vars.VCl_costUp_cost&vars.VCl_cost-0.02>=1){
            vars.VCl_cost-=0.02;
            vars.VCl-=vars.VCl_costUp_cost;
            vars.VCl_costUp_cost*=8;
        }

        });
        extraMlt.setOnClickListener(v->{
            if (vars.VCl>=vars.VP_extraCP_cost){
                vars.VP_extraCP_mlt*=1.2;
                vars.VCl-=vars.VP_extraCP_cost;
                vars.VP_extraCP_cost*=2;

            }
        });
        Thread th = new Thread(VP_press);
        if (!th.isAlive()) {
            th.start();
        }

        VPDelayUpdate_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vars.VCl >= vars.VP_delayUpd_cost) {
                    if (vars.VP_delay <= 10) {
                        vars.VP_delay += 1;
                    } else {
                        vars.VP_delay *= 1.1;
                    }
                    vars.VCl -= vars.VP_delayUpd_cost;
                    vars.VP_delayUpd_cost *= 2;

                }
            }
        });
        prestige0_btn.setOnClickListener(v -> {
            if (vars.VP_prestige0_multiplier < vars.VP_prestige0_multiplier_new) {


                vars.VP_prestige0_multiplier = vars.VP_prestige0_multiplier_new;
                vars.RESET_VP();

            }
        });
        prestige0_btn.setOnLongClickListener(v -> {
            Toast.makeText(requireContext(), getStr(R.string.prestige0_desc), Toast.LENGTH_LONG).show();
            return false;
        });
        VPDvnUp_Btn.setOnClickListener(v -> {
            if (vars.VCl >= vars.VP_dvn_UpCost) {
                vars.VP_dvn--;
                vars.VCl -= vars.VP_dvn_UpCost;
                vars.VP_dvn_UpCost *= 3;

            }
        });
        VPCPUp_Btn.setOnClickListener(v -> {
            if (vars.VCl >= vars.VP_perClick_upCost) {
                if (vars.VP_perClick > 20) {
//                    if (vars.VP_perClick<=100){
                    vars.VP_perClick *= 1.5;//}
//                    else if(vars.VP_perClick<=1000){
//                        vars.VP_perClick*=1.45;
//                    } else{vars.VP_perClick*=1.4;}
                } else {
                    vars.VP_perClick *= 3;
                }
                vars.VCl -= vars.VP_perClick_upCost;
                vars.VP_perClick_upCost *= 2;
            }
        });

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                while (Thread.currentThread().isAlive()) {
                    try {
                        Thread.sleep(1000 / vars.FPS);
                        handler.sendEmptyMessage(1);
                        if(vars.isVPPhaseDestroyed) break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }


    public VP_Frg() {

        super(R.layout.virtual_particles);


    }

    public void Update_VP() {
        VPCount_Txt.setText(Double.toString(vars.VP));
        VCCount_Txt.setText(Double.toString(vars.VCl));
        counter++;
        if (!vars.isVPBroken){
            DESTROY_VP.setVisibility(View.INVISIBLE);
            DESTROY_VP.setEnabled(false);
        }
        else{
            DESTROY_VP.setVisibility(View.VISIBLE);
            DESTROY_VP.setEnabled(true);
            BREAKQPHYS.setHeight(1);
        }
        if (vars.VP_prestige0_multiplier > vars.VP_prestige0_multiplier_new) {
            prestige0_btn.setEnabled(false);
        } else {
            prestige0_btn.setEnabled(true);
        }
        VPCPUp_Btn.setText(rsStr.get("CP_up") + "\n" + rsStr.get("wrd_cost") + ": " + vars.VP_perClick_upCost);
        if (!vars.isVPBroken){
            VPDvnUp_Btn.setText(rsStr.get("Up_dvn") + "\n" + rsStr.get("wrd_cost") + ": " + Double.toString(vars.VP_dvn_UpCost));
            VPDelayUpdate_Btn.setText(
                    rsStr.get("Up_delay")
                            + "\n"
                            + rsStr.get("wrd_cost")
                            + ": "
                            + Double.toString(vars.VP_delayUpd_cost)
            );
        }
        else{
            VPDelayUpdate_Btn.setText(
                    rsStr.get("Up_delay")
                            + "\n"
                            + rsStr.get("locked_qphys_broken")

            );

            VPDvnUp_Btn.setText(rsStr.get("Up_dvn") + "\n" + rsStr.get("locked_qphys_broken"));
        }
        VP_cd_display.setText(rsStr.get("VP_cd") + ":\n" + vars.VP_delay);
        VP_cp_display.setText(rsStr.get("VP_cp") + ":\n" + vars.VP_perClick);
        VP_div_display.setText(rsStr.get("VP_div") + ":\n" + vars.VP_dvn);
        VPClick_Btn.setText(rsStr.get("VP_clickToGen") + "\n" + rsStr.get("word_multiplier") + ": " + vars.VP_perCLick_mlt_total);
        prestige0_btn.setText(rsStr.get("prs0_text") + "\n" + rsStr.get("word_multiplier") + ": " + vars.VP_prestige0_multiplier_new);
        prestige0_btn_mlt.setText(rsStr.get("prestige0_multiplier")+": \n"+vars.VP_prestige0_mlt+"\n"+rsStr.get("wrd_cost")+": "+vars.VP_prestige0_mlt_cost);
        extraMlt.setText(rsStr.get("CpMlt")+"\n"+rsStr.get("word_multiplier")+": "+vars.VP_extraCP_mlt+"\n"+rsStr.get("wrd_cost")+": "+vars.VP_extraCP_cost);
        VCl_costMlt_decrease.setText(rsStr.get("decrease_VCl_cost")+"\n"+rsStr.get("word_multiplier")+": "+vars.VCl_cost+"\n"+rsStr.get("wrd_cost")+": "+vars.VCl_costUp_cost);
        if(vars.isVPBroken){
            BREAKQPHYS.setVisibility(View.INVISIBLE);
            BREAKQPHYS.setHeight(1);
            VPDvnUp_Btn.setEnabled(false);
            VPDelayUpdate_Btn.setEnabled(false);
        }
        if(vars.VCl>=200&&vars.VP_dvn<=5&&!vars.isVPBroken){
            BREAKQPHYS.setEnabled(true);
            BREAKQPHYS.setVisibility(View.VISIBLE);
        }
        else{
            BREAKQPHYS.setEnabled(false);
            BREAKQPHYS.setVisibility(View.INVISIBLE);
        }
        //NORMALIZE ZONE

        if (counter >= vars.FPS) {
//            Utils.FitText(VPClick_Btn);
//            Utils.FitText(VPDelayUpdate_Btn);
//            Utils.FitText(VPDvnUp_Btn);
//            Utils.FitText(VPCPUp_Btn);
//            Utils.FitText(prestige0_btn);
//            Utils.FitText(prestige0_btn_mlt);
//            Utils.FitText(extraMlt);
//            Utils.FitText(VCl_costMlt_decrease);
            counter=0;
        }

    }

    private String getStr(int id) {
        return getResources().getString(id);
    }

    private void InitMap(Map m) {
        m.put("Up_delay", getStr(R.string.VPUpgrade_delay));
        m.put("wrd_cost", getStr(R.string.word_cost));
        m.put("Up_dvn", getStr(R.string.VPUpgrade_dvn));
        m.put("CP_up", getStr(R.string.VPUpgrade_cp));
        m.put("VP_div", getStr(R.string.VP_div));
        m.put("VP_cp", getStr(R.string.VP_cp));
        m.put("VP_cd", getStr(R.string.VP_cd));
        m.put("VP_clickToGen", getStr(R.string.VP_clickToGen));
        m.put("word_multiplier", getStr(R.string.word_multiplier));
        m.put("prs0_text", getStr(R.string.prestige0));
        m.put("prestige0_multiplier", getStr(R.string.prestige0_multiplier));
        m.put("CpMlt", getStr(R.string.CpMlt));
        m.put("IncreaseGameTickseedMlt", getStr(R.string.IncreaseGameTickseedMlt));
        m.put("decrease_VCl_cost", getStr(R.string.decrease_VCl_cost));
        m.put("locked_qphys_broken", getStr(R.string.locked_qphys_broken));
        m.put("VP_toPhase1Button", getStr(R.string.VP_toPhase1Button));

    }

}
