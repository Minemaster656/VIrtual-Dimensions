package com.dti.virtualdimensions;

import java.math.BigDecimal;
import java.util.ArrayList;

public class vars {
    static double inf = 1.79e308;
    static double GAME_TICKSPEED = 0.1;
    public static double GAME_TICKSPEED_MULTIPLIER = 1;
    static double VP = 1; //Double.parseDouble("1000"); //в.ч.
    static double VCl = 500; //в.к.
    static double VCl_size = 10;
    static double VCl_size0 = 10;
    double VCl_size0_default = 10;
    static double VCl_cost = 1.1;
    static double VCl_costUp_cost = 10;//стоимость кластера, стоимость по умолчанию
    static double VP_delay = 1;
    double VP_delay_default = 1; //задержка исчезновения (секунды)
    static double VP_delayUpd_cost = 1;
    double VP_delayUpd_cost_default = 1;//стоимость апгрейда ⬆
    static double VP_dvn = 10;
    double VP_dvn_default = 10;//деитель при исчезновении
    static double VP_dvn_UpCost = 2;
    double VP_dvn_UpCost_default = 2;//стоимость апгрейда делителя
    //    static double VP_brake_cost=1024; // стоимость сжигания учебника квантовой физики
    static double VP_perClick = 1;
    double VP_perClick_default = 1;//в.ч. за клик
    static double VP_perClick_upCost = 1;
    double VP_perClick_upCost_default = 1;
    //    static double VP_cp_bought;
//    static double UP_perCLick_upMod=2;
//    static double VCl_max = inf; //максимум кластеров в.ч.
    static boolean isVPBroken; //квантоваяФизика.сломать();
    static int FPS = 30; //~~50~~
    static double VP_perCLick_mlt_total = 1;
    static double VP_prestige0_multiplier = 1;
    static double VP_prestige0_multiplier_new = 1;
    public static double VP_prestige0_mlt = 1;
    public static double VP_prestige0_mlt_cost = 10;
    public static double VP_extraCP_mlt = 1;
    public static double VP_extraCP_cost = 4;


    //VP DIMENSIONS
    public static volatile boolean isVPPhaseDestroyed = false;
    public static BigDecimal vBuyMlt = BigDecimal.valueOf(1);
    public static BigDecimal v_VP = BigDecimal.valueOf(1);
    public static BigDecimal v_tickspeed = BigDecimal.valueOf(1);
    public static BigDecimal v_tickspeedBought = BigDecimal.valueOf(0);
    public static BigDecimal v_tickspeedPrice = BigDecimal.valueOf(1000);


    static ArrayList<Dim> dims = new ArrayList<Dim>();


    public static final BigDecimal bd_1 = BigDecimal.valueOf(1);
    public static final BigDecimal bd_0 = BigDecimal.valueOf(0);


    public static BigDecimal vCollapse_count = BigDecimal.valueOf(0);
    public static BigDecimal vCollapse_mlt = BigDecimal.valueOf(1.5);
    public static BigDecimal vCollapse_price = BigDecimal.valueOf(1E50);
    public static BigDecimal vCollapse_priceMlt = BigDecimal.valueOf(1E50);
    public static BigDecimal vCollapse_priceMltMlt = BigDecimal.valueOf(1E5);
    public static boolean doSave=true;



    public static void RESET_VP() {
        vars.VCl_size0 = 10;
        vars.VP_delay = 1;
        vars.VP_delayUpd_cost = 1;
        vars.VP_dvn = 10;
        vars.VP_dvn_UpCost = 2;
        vars.VP_perClick_upCost = 1;
        vars.VP_perClick = 1;
        vars.VCl = 0;
        vars.VP = 0;
    }

    public static void RESET_VDims() {
        vars.v_VP = BigDecimal.valueOf(1);
        vars.v_tickspeed = BigDecimal.valueOf(1);
        vars.v_tickspeedBought = BigDecimal.valueOf(0);
        vars.v_tickspeedPrice = BigDecimal.valueOf(1000);
        vars.dims.set(0, new Dim(1, 10f));
        vars.dims.set(1, new Dim(100, 50f));
        vars.dims.set(2, new Dim(10000, 100f));
        vars.dims.set(3, new Dim(1000000, 1E3));
        vars.dims.set(4, new Dim(100000000, 1E7));
        vars.dims.set(5, new Dim(10000000000L, 1E11));
        vars.dims.set(6, new Dim(-1L, 1E308));

    }

}
