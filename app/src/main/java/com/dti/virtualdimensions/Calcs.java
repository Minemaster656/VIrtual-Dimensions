package com.dti.virtualdimensions;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import java.lang.Thread;
import java.util.ArrayList;

public class Calcs {
    //     CalcThr ct = new CalcThr();
//     FpsCalcThr fct = new FpsCalcThr();
//     Production prt = new Production();
    long frameDelay = 1000 / vars.FPS;
    VDims VDims = null;
    Runnable CalcThr = new Runnable() {
        @Override
        public void run() {
            while (Thread.currentThread().isAlive()) {
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
    Thread ct = new Thread(CalcThr);
    float counter;
    Runnable FpsCalcThr = new Runnable() {
        @Override
        public void run() {
            while (Thread.currentThread().isAlive()) {
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
    Thread fct = new Thread(FpsCalcThr);
    Runnable Production = new Runnable() {
        @Override
        public void run() {
            while (Thread.currentThread().isAlive()) {
                try {
                    sleep(250);
                    vars.v_VP=vars.v_VP.add(VDims.dims.get(0).count.multiply(VDims.dims.get(0).mlt).multiply(vars.v_tickspeed));
                    VDims.dims.get(0).count = VDims.dims.get(0).count.add(VDims.dims.get(1).count.multiply(VDims.dims.get(1).mlt).multiply(vars.v_tickspeed));
                    VDims.dims.get(1).count = VDims.dims.get(1).count.add(VDims.dims.get(2).count.multiply(VDims.dims.get(2).mlt).multiply(vars.v_tickspeed));
                    VDims.dims.get(2).count = VDims.dims.get(2).count.add(VDims.dims.get(3).count.multiply(VDims.dims.get(3).mlt).multiply(vars.v_tickspeed));
                    VDims.dims.get(3).count = VDims.dims.get(3).count.add(VDims.dims.get(4).count.multiply(VDims.dims.get(4).mlt).multiply(vars.v_tickspeed));
                    VDims.dims.get(4).count = VDims.dims.get(4).count.add(VDims.dims.get(5).count.multiply(VDims.dims.get(5).mlt).multiply(vars.v_tickspeed));

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };
    Thread prt = new Thread(Production);


    public void setVDFragment(VDims vd) {
        this.VDims = vd;
    }
//    static UpdateInvoker ut = new UpdateInvoker();

    public void initAll(){
        VDims.dims.add(new Dim(1, 1.1f));
        VDims.dims.add(new Dim(100, 1.2f));
        VDims.dims.add(new Dim(10000, 1.3f));
        VDims.dims.add(new Dim(1000000, 1.4f));
        VDims.dims.add(new Dim(100000000, 1.5f));
        VDims.dims.add(new Dim(10000000000L, 1.6f));
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
//        vars.VP_perCLick_mlt_total=vars.VP_perClick*vars.VP_prestige0_multiplier*vars.VP_extraCP_mlt;//*vars.GAME_TICKSPEED_MULTIPLIER;
//            if (vars.VP_perCLick_mlt_total==0){
//                vars.VP_perCLick_mlt_total=1;
//            }
//            vars.VP_prestige0_multiplier_new=(vars.VCl/100)*vars.VP_prestige0_mlt+1;
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
//class FpsCalcThr extends Thread{
//    float counter;
//    @Override
//    public void run() {
//        while (true){
//            try {
//                sleep(1000/vars.FPS);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            counter++;
//            if (counter>=vars.FPS*(vars.VP_delay+0.5) & !vars.isVPBroken){
//                vars.VP/=vars.VP_dvn;
//                counter=0;
//
//            }
//        }
//    }
//
//}
//class Production extends Thread{
//    @Override
//    public void run() {
//        while (true){
//        try {
//            sleep(250);
//
//
//
//
//
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }}
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
