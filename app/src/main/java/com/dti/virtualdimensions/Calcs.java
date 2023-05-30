package com.dti.virtualdimensions;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import java.lang.Thread;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calcs {
    public MathContext mc = new MathContext(5, RoundingMode.DOWN);
    Runnable calct = new Runnable() {
        @Override
        public void run() {
//        super.run();
            //System.out.println("a");
//        if (vars.VP >= vars.VCl_size){
            while (currentThread().isAlive()) {
                vars.VCl_size = vars.VCl_size0 * (Math.pow(vars.VCl_cost, vars.VCl));
                vars.VP_perCLick_mlt_total = vars.VP_perClick * vars.VP_prestige0_multiplier * vars.VP_extraCP_mlt;//*vars.GAME_TICKSPEED_MULTIPLIER;
                if (vars.VP_perCLick_mlt_total == 0) {
                    vars.VP_perCLick_mlt_total = 1;
                }
                vars.VP_prestige0_multiplier_new = (vars.VCl / 100) * vars.VP_prestige0_mlt + 1;

//            if(vars.VP<=1000000){
//            vars.VP = new Double(String.format("%.2f", vars.VP + ""));}
                if (vars.VP <= 0.1) {
                    vars.VP = 0;
                }
                while (vars.VP >= vars.VCl_size) {
                    vars.VP -= vars.VCl_size;
                    vars.VCl++;
                    //vars.VCl_size*=1.1;
                }
//        }

            }
        }
    };
    Thread ct = new Thread(calct);
    Runnable frb = new Runnable() {
        float counter;

        @Override
        public void run() {
            while (currentThread().isAlive()) {
                try {
                    sleep(1000 / vars.FPS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                counter++;
                if (counter >= vars.FPS * (vars.VP_delay + 0.5) & !vars.isVPBroken) {
                    vars.VP /= vars.VP_dvn;
                    counter = 0;

                }
            }
        }
    };
    Thread fct = new Thread(frb);
    Runnable prb = new Runnable() {
        @Override
        public void run() {
            while (currentThread().isAlive()) {
                try {
                    sleep(250 / vars.FPS);
                    Calcs.calc(true, 1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };
    Thread prt = new Thread(prb);

    VD_frg_old secondFragment = null;

    public void setSecondFragment(VD_frg_old vd) {
        this.secondFragment = vd;
    }

    //    static UpdateInvoker ut = new UpdateInvoker();
    public static void doAuto() {
        for (int i = 0; i < 6; i++) {
            if (vars.dimAutoUnlocks[i] & vars.dimAutoToggles[i]) {
                while (vars.dims.get(i).price.compareTo(vars.v_VP) <= 0) {
                    vars.dims.get(i).buy();
                }
            }
        }
        if (vars.extraAutoUnlocks.get(0) & vars.extraAutoToggles.get(0)) {
            while (vars.v_tickspeedPrice.compareTo(vars.v_VP) <= 0) {
                vars.buyTickspeed_();
            }
        }
        if (vars.extraAutoUnlocks.get(0) & vars.extraAutoToggles.get(0)) vars.doCollapse();

    }

    public static void calc(boolean useFPS, int ticks) {
        BigDecimal collapseMlt;
        MathContext mc = new MathContext(5, RoundingMode.DOWN);
        if (vars.vCollapse_count.compareTo(BigDecimal.valueOf(0)) <= 0) {
            collapseMlt = BigDecimal.valueOf(1);
        } else {
            collapseMlt = vars.vCollapse_count.multiply(vars.vCollapse_mlt);
        }

        if (!vars.q_isVoidCleared) {
//                    vars.v_tickspeed = vars.v_tickspeedBought.divide(BigDecimal.valueOf(10), mc).add(BigDecimal.valueOf(1));
            vars.v_tickspeed = vars.v_tickspeedBought.multiply(BigDecimal.valueOf(0.1)).add(BigDecimal.valueOf(1));
        } else {
            vars.v_tickspeed = vars.v_tickspeedBought.multiply(BigDecimal.valueOf(0.1)).add(BigDecimal.valueOf(1)).add(vars.clearTheVoidBought.multiply(BigDecimal.valueOf(0.5)).multiply(vars.v_tickspeedBought));//(BigDecimal.valueOf(0.5).multiply(vars.v_tickspeedBought));
        }

        if (vars.v_VP.compareTo(BigDecimal.valueOf(1E100)) < 0) {
            vars.quarksOnAnnihilate = BigDecimal.valueOf(0);
        } else {
            vars.quarksOnAnnihilate = Utils.loge(vars.v_VP).divide(BigDecimal.valueOf(100), mc).multiply(vars.quarkMlt);//vars.v_VP.divide(BigDecimal.valueOf(1E100), mc);
            if (vars.quarksOnAnnihilate.compareTo(BigDecimal.ONE) < 0)
                vars.quarksOnAnnihilate = (BigDecimal.ONE).multiply(vars.quarkMlt);

        }

        if (vars.quarksOnAnnihilate.compareTo(BigDecimal.valueOf(1)) >= 1 & vars.v_VP.compareTo(BigDecimal.valueOf(1E100)) >= 0) {
            vars.q_isUnlocked = true;
        }
        //dim_MLT
        if (useFPS) {
            for (int i = 0; i < 6; i++) {
                vars.dims.get(i).mlt = vars.dims.get(i).count.divide(BigDecimal.valueOf(1E10), mc).multiply(collapseMlt).add(vars.dims.get(i).realCount.pow(3).divide(BigDecimal.valueOf(vars.FPS), mc)).add(BigDecimal.valueOf(1));
            }
            //dim_CALCS
            for (int i = 0; i < 6; i++) {
                vars.dims.get(i).count = vars.dims.get(i).count.add((vars.dims.get(i + 1).count).multiply(vars.dims.get(i + 1).mlt).multiply(vars.v_tickspeed).divide(BigDecimal.valueOf(vars.FPS), mc));
            }
            vars.v_VP = vars.v_VP.add((vars.dims.get(0).count).multiply(vars.dims.get(0).mlt).multiply(vars.v_tickspeed).divide(BigDecimal.valueOf(vars.FPS), mc));
        } else {
            for (int i = 0; i < 6; i++) {
                vars.dims.get(i).mlt = vars.dims.get(i).count.divide(BigDecimal.valueOf(1E10), mc).multiply(collapseMlt).add(vars.dims.get(i).realCount.pow(3)).multiply(BigDecimal.valueOf(ticks)).add(BigDecimal.valueOf(1));
            }
            //dim_CALCS
            for (int i = 0; i < 6; i++) {
                vars.dims.get(i).count = vars.dims.get(i).count.add((vars.dims.get(i + 1).count).multiply(vars.dims.get(i + 1).mlt).multiply(vars.v_tickspeed).multiply(BigDecimal.valueOf(ticks)));
            }
            vars.v_VP = vars.v_VP.add((vars.dims.get(0).count).multiply(vars.dims.get(0).mlt).multiply(vars.v_tickspeed).multiply(BigDecimal.valueOf(ticks)));
        }
        Calcs.doAuto();
    }

    public static void calcOffline(Context context) {


        Runnable rb = new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                BigDecimal start_vp = vars.v_VP;
                BigDecimal start_1dim_count = vars.dims.get(0).count;
                BigDecimal start_6dim_count = vars.dims.get(5).count;
                BigDecimal start_TS_bought = vars.v_tickspeedBought;
                BigDecimal start_collapseCount = vars.vCollapse_count;
                BigDecimal start_quarks = vars.quarks;
                Log.d(TAG, "started offline calc");
                //расчёт
                long ticks = (vars.offTime / 1000); //*4;
                int ticksPerCalc = ticks > 1000 ? (int) (ticks / 1000) : 0;
                Log.d(TAG, ticks + " ticks. " + ticksPerCalc + " tpc.");
                if (ticksPerCalc == 0) {
                    Log.d(TAG, "Calc 1 sec.");
                    Calcs.calc(false, (int) ticks);

                } else {
                    Log.d(TAG, "Calc ??? secs.");
                    for (int i = 0; i < 1000; i++) {
                        Calcs.calc(false, ticksPerCalc);
                    }
                }
                Log.d(TAG, "completed offline calc");
                //диалог
                vars.invoke("CALC_offline_ddata", "MAIN", Utils.bd2txt(start_vp) + "#" + Utils.bd2txt(start_1dim_count) + "#" + Utils.bd2txt(start_6dim_count) + "#" + Utils.bd2txt(start_TS_bought) + "#" + Utils.bd2txt(start_collapseCount) + "#" + Utils.bd2txt(start_quarks));
            }

            public String getStr(int id) {
                return context.getResources().getString(id);
            }
        };
        Thread offct = new Thread(rb);
        offct.start();
    }

}


