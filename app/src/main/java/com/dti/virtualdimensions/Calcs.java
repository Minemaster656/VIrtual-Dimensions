package com.dti.virtualdimensions;

import java.lang.Thread;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Calcs {
    public MathContext mc = new MathContext(5, RoundingMode.DOWN);
     CalcThr ct = new CalcThr();
     FpsCalcThr fct = new FpsCalcThr();
     Production prt = new Production();

     VD_frg_old secondFragment = null;
    public void setSecondFragment(VD_frg_old vd) {
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
        while (Thread.currentThread().isAlive()){
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
    public MathContext mc = new MathContext(5, RoundingMode.DOWN);
    @Override
    public void run() {
        while (Thread.currentThread().isAlive()){
        try {
            sleep(250/vars.FPS);

            vars.v_tickspeed=vars.v_tickspeedBought.divide(BigDecimal.valueOf(10), mc).add(BigDecimal.valueOf(1));
            //dim_MLT
            for (int i=0; i<6; i++){
                vars.dims.get(i).mlt=vars.dims.get(i).count.divide(BigDecimal.valueOf(1E10), mc).add(vars.dims.get(i).realCount.pow(3).divide(BigDecimal.valueOf(vars.FPS), mc)).add(BigDecimal.valueOf(1));
            }
            //dim_CALCS
            for (int i=0; i<6; i++){
                vars.dims.get(i).count=vars.dims.get(i).count.add((vars.dims.get(i+1).count).multiply(vars.dims.get(i+1).mlt).multiply(vars.v_tickspeed).divide(BigDecimal.valueOf(vars.FPS), mc));
            }
            vars.v_VP=vars.v_VP.add((vars.dims.get(0).count).multiply(vars.dims.get(0).mlt).multiply(vars.v_tickspeed).divide(BigDecimal.valueOf(vars.FPS), mc));




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
