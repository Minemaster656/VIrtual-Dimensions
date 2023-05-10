package com.dti.virtualdimensions;

import java.lang.Thread;
import java.util.ArrayList;

public class Calcs {
     CalcThr ct = new CalcThr();
     FpsCalcThr fct = new FpsCalcThr();
     Production prt = new Production();

     VD_frg secondFragment = null;
    public void setSecondFragment(VD_frg vd) {
        this.secondFragment = vd;
    }
//    static UpdateInvoker ut = new UpdateInvoker();

}

class CalcThr extends Thread {
    @Override
    public void run() {
//        super.run();
        //System.out.println("a");
//        if (vars.VP >= vars.VCl_size){
        while (currentThread().isAlive()) {
            vars.VCl_size = vars.VCl_size0 * (Math.pow(vars.VCl_cost, vars.VCl));
        vars.VP_perCLick_mlt_total=vars.VP_perClick*vars.VP_prestige0_multiplier*vars.VP_extraCP_mlt;//*vars.GAME_TICKSPEED_MULTIPLIER;
            if (vars.VP_perCLick_mlt_total==0){
                vars.VP_perCLick_mlt_total=1;
            }
            vars.VP_prestige0_multiplier_new=(vars.VCl/100)*vars.VP_prestige0_mlt+1;

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
}
class FpsCalcThr extends Thread{
    float counter;
    @Override
    public void run() {
        while (true){
            try {
                sleep(1000/vars.FPS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            counter++;
            if (counter>=vars.FPS*(vars.VP_delay+0.5) & !vars.isVPBroken){
                vars.VP/=vars.VP_dvn;
                counter=0;

            }
        }
    }

}
class Production extends Thread{
    @Override
    public void run() {
        while (true){
        try {
            sleep(250);





        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }}
}
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
