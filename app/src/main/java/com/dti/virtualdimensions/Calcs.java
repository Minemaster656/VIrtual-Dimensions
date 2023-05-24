package com.dti.virtualdimensions;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

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
                    calc(true);
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
    public void doAuto(){
        for (int i = 0; i < 6; i++) {
            if (vars.dimAutoUnlocks[i]&vars.dimAutoToggles[i]){
                vars.dims.get(i).buy();
            }
        }
        if (vars.extraAutoUnlocks.get(0)&vars.extraAutoToggles.get(0)){
            vars.buyTickspeed_();
        }
        if (vars.extraAutoUnlocks.get(0)&vars.extraAutoToggles.get(0)) vars.doCollapse();

    }
    public void calc(boolean useFPS){
        BigDecimal collapseMlt;
        if (vars.vCollapse_count.compareTo(BigDecimal.valueOf(0)) <= 0) {
            collapseMlt = BigDecimal.valueOf(1);
        } else {
            collapseMlt = vars.vCollapse_count.multiply(vars.vCollapse_mlt);
        }

        if (!vars.q_isVoidCleared) {
//                    vars.v_tickspeed = vars.v_tickspeedBought.divide(BigDecimal.valueOf(10), mc).add(BigDecimal.valueOf(1));
            vars.v_tickspeed = vars.v_tickspeedBought.multiply(BigDecimal.valueOf(0.1)).add(BigDecimal.valueOf(1));
        } else {
            vars.v_tickspeed = vars.v_tickspeedBought.multiply(BigDecimal.valueOf(0.1)).add(BigDecimal.valueOf(1)).add(BigDecimal.valueOf(0.5).multiply(vars.v_tickspeedBought));
        }

        if (vars.v_VP.compareTo(BigDecimal.valueOf(1E100)) < 1) {
            vars.quarksOnAnnihilate = BigDecimal.valueOf(0);
        } else {
            vars.quarksOnAnnihilate = Utils.loge(vars.v_VP).divide(BigDecimal.valueOf(1E100),mc);//vars.v_VP.divide(BigDecimal.valueOf(1E100), mc);
            if (vars.quarksOnAnnihilate.compareTo(BigDecimal.ONE)<0) vars.quarksOnAnnihilate=BigDecimal.ONE;

        }

        if (vars.quarksOnAnnihilate.compareTo(BigDecimal.valueOf(1)) >= 1&vars.v_VP.compareTo(BigDecimal.valueOf(1E100))>=0) {
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
        }
        else {
            for (int i = 0; i < 6; i++) {
                vars.dims.get(i).mlt = vars.dims.get(i).count.divide(BigDecimal.valueOf(1E10), mc).multiply(collapseMlt).add(vars.dims.get(i).realCount.pow(3)).add(BigDecimal.valueOf(1));
            }
            //dim_CALCS
            for (int i = 0; i < 6; i++) {
                vars.dims.get(i).count = vars.dims.get(i).count.add((vars.dims.get(i + 1).count).multiply(vars.dims.get(i + 1).mlt).multiply(vars.v_tickspeed));
            }
            vars.v_VP = vars.v_VP.add((vars.dims.get(0).count).multiply(vars.dims.get(0).mlt).multiply(vars.v_tickspeed));
        }
        doAuto();
    }

}

//class CalcThr extends Thread {
//    @Override
//    public void run() {
////        super.run();
//        //System.out.println("a");
////        if (vars.VP >= vars.VCl_size){
//        while (currentThread().isAlive()) {
//            vars.VCl_size = vars.VCl_size0 * (Math.pow(vars.VCl_cost, vars.VCl));
//            vars.VP_perCLick_mlt_total = vars.VP_perClick * vars.VP_prestige0_multiplier * vars.VP_extraCP_mlt;//*vars.GAME_TICKSPEED_MULTIPLIER;
//            if (vars.VP_perCLick_mlt_total == 0) {
//                vars.VP_perCLick_mlt_total = 1;
//            }
//            vars.VP_prestige0_multiplier_new = (vars.VCl / 100) * vars.VP_prestige0_mlt + 1;
//
////            if(vars.VP<=1000000){
////            vars.VP = new Double(String.format("%.2f", vars.VP + ""));}
//            if (vars.VP <= 0.1) {
//                vars.VP = 0;
//            }
//            while (vars.VP >= vars.VCl_size) {
//                vars.VP -= vars.VCl_size;
//                vars.VCl++;
//                //vars.VCl_size*=1.1;
//            }
////        }
//
//        }
//    }
//}

//class FpsCalcThr extends Thread {
//    float counter;
//
//    @Override
//    public void run() {
//        while (currentThread().isAlive()) {
//            try {
//                sleep(1000 / vars.FPS);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            counter++;
//            if (counter >= vars.FPS * (vars.VP_delay + 0.5) & !vars.isVPBroken) {
//                vars.VP /= vars.VP_dvn;
//                counter = 0;
//
//            }
//        }
//    }
//
//}

//class Production extends Thread {
//    public MathContext mc = new MathContext(5, RoundingMode.DOWN);
//
//    @Override
//    public void run() {
//        while (currentThread().isAlive()) {
//            try {
//                sleep(250 / vars.FPS);
//                BigDecimal collapseMlt;
//                if (vars.vCollapse_count.compareTo(BigDecimal.valueOf(0)) <= 0) {
//                    collapseMlt = BigDecimal.valueOf(1);
//                } else {
//                    collapseMlt = vars.vCollapse_count.multiply(vars.vCollapse_mlt);
//                }
//
//                if (!vars.q_isVoidCleared) {
////                    vars.v_tickspeed = vars.v_tickspeedBought.divide(BigDecimal.valueOf(10), mc).add(BigDecimal.valueOf(1));
//                    vars.v_tickspeed = vars.v_tickspeedBought.multiply(BigDecimal.valueOf(0.1)).add(BigDecimal.valueOf(1));
//                } else {
//                    vars.v_tickspeed = vars.v_tickspeedBought.multiply(BigDecimal.valueOf(0.1)).add(BigDecimal.valueOf(1)).add(BigDecimal.valueOf(0.5).multiply(vars.v_tickspeedBought));
//                }
//
//                if (vars.v_VP.compareTo(BigDecimal.valueOf(1E100)) < 1) {
//                    vars.quarksOnAnnihilate = BigDecimal.valueOf(0);
//                } else {
//                    vars.quarksOnAnnihilate = Utils.loge(vars.v_VP).divide(BigDecimal.valueOf(1E100),mc);//vars.v_VP.divide(BigDecimal.valueOf(1E100), mc);
//                    if (vars.quarksOnAnnihilate.compareTo(BigDecimal.ONE)<0) vars.quarksOnAnnihilate=BigDecimal.ONE;
//
//                }
//
//                if (vars.quarksOnAnnihilate.compareTo(BigDecimal.valueOf(1)) >= 1&vars.v_VP.compareTo(BigDecimal.valueOf(1E100))>=0) {
//                    vars.q_isUnlocked = true;
//                }
//                //dim_MLT
//                for (int i = 0; i < 6; i++) {
//                    vars.dims.get(i).mlt = vars.dims.get(i).count.divide(BigDecimal.valueOf(1E10), mc).multiply(collapseMlt).add(vars.dims.get(i).realCount.pow(3).divide(BigDecimal.valueOf(vars.FPS), mc)).add(BigDecimal.valueOf(1));
//                }
//                //dim_CALCS
//                for (int i = 0; i < 6; i++) {
//                    vars.dims.get(i).count = vars.dims.get(i).count.add((vars.dims.get(i + 1).count).multiply(vars.dims.get(i + 1).mlt).multiply(vars.v_tickspeed).divide(BigDecimal.valueOf(vars.FPS), mc));
//                }
//                vars.v_VP = vars.v_VP.add((vars.dims.get(0).count).multiply(vars.dims.get(0).mlt).multiply(vars.v_tickspeed).divide(BigDecimal.valueOf(vars.FPS), mc));
//
//
//                for (int i = 0; i < 6; i++) {
//                    if (vars.dimAutoUnlocks[i]){
//                        vars.dims.get(i).buy();
//                    }
//                }
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//}
//class UpdateInvoker extends Thread{
//    @Override
//    public void run() {
////        super.run();
//        try {
//            sleep(20);
//
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
