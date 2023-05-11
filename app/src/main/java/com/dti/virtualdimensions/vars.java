package com.dti.virtualdimensions;

import java.math.BigDecimal;

public class vars {
    static double inf=1.79e308;
    static double GAME_TICKSPEED=0.1;
    public static double GAME_TICKSPEED_MULTIPLIER=1;
    static double VP = 1; //Double.parseDouble("1000"); //в.ч.
    static double VCl; //в.к.
    static double VCl_size = 10; static double VCl_size0 = 10;  double VCl_size0_default = 10; static double VCl_cost=1.1;  static double VCl_costUp_cost=10;//стоимость кластера, стоимость по умолчанию
    static double VP_delay = 1;  double VP_delay_default = 1; //задержка исчезновения (секунды)
    static double VP_delayUpd_cost = 1;  double VP_delayUpd_cost_default = 1;//стоимость апгрейда ⬆
    static double VP_dvn = 10;  double VP_dvn_default = 10;//деитель при исчезновении
    static double VP_dvn_UpCost = 2;  double VP_dvn_UpCost_default = 2;//стоимость апгрейда делителя
//    static double VP_brake_cost=1024; // стоимость сжигания учебника квантовой физики
    static double VP_perClick = 1; double VP_perClick_default = 1;//в.ч. за клик
    static double VP_perClick_upCost=1;  double VP_perClick_upCost_default = 1;
//    static double VP_cp_bought;
//    static double UP_perCLick_upMod=2;
//    static double VCl_max = inf; //максимум кластеров в.ч.
    static boolean isVPBroken; //квантоваяФизика.сломать();
    static int FPS = 40; //~~50~~
    static double VP_perCLick_mlt_total=1;
    static double VP_prestige0_multiplier=1;
    static double VP_prestige0_multiplier_new=1;
    public static double VP_prestige0_mlt=1;
    public static double VP_prestige0_mlt_cost=10;
    public static double VP_extraCP_mlt=1;
    public static double VP_extraCP_cost=4;



    //VP DIMENSIONS
    public static boolean isVPPhaseDestroyed=false;
    public static BigDecimal vBuyMlt=BigDecimal.valueOf(1);
    public static BigDecimal v_VP=BigDecimal.valueOf(0f);
    public static BigDecimal v_tickspeed=BigDecimal.valueOf(1f);


//    public static BigDecimal vDim1_count;
//    public static BigDecimal vDim1_price;
//    public static BigDecimal vDim1_mlt;
//    public static BigDecimal vDim1_priceUp=BigDecimal.valueOf(1.1);
//    public static boolean vDim1_isUnlocked=true;
//
//    public static BigDecimal vDim2_count;
//    public static BigDecimal vDim2_price;
//    public static BigDecimal vDim2_mlt;
//    public static BigDecimal vDim2_priceUp=BigDecimal.valueOf(1.2);
//    public static boolean vDim2_isUnlocked=true;
//
//    public static BigDecimal vDim3_count;
//    public static BigDecimal vDim3_price;
//    public static BigDecimal vDim3_mlt;
//    public static BigDecimal vDim3_priceUp=BigDecimal.valueOf(1.3);
//    public static boolean vDim3_isUnlocked=true;
//
//    public static BigDecimal vDim4_count;
//    public static BigDecimal vDim4_price;
//    public static BigDecimal vDim4_mlt;
//    public static BigDecimal vDim4_priceUp=BigDecimal.valueOf(1.4);
//    public static boolean vDim4_isUnlocked;
//
//    public static BigDecimal vDim5_count;
//    public static BigDecimal vDim5_price;
//    public static BigDecimal vDim5_mlt;
//    public static BigDecimal vDim5_priceUp=BigDecimal.valueOf(1.5);
//    public static boolean vDim5_isUnlocked;
//
//    public static BigDecimal vDim6_count;
//    public static BigDecimal vDim6_price;
//    public static BigDecimal vDim6_mlt;
//    public static BigDecimal vDim6_priceUp=BigDecimal.valueOf(1.6);
//    public static boolean vDim6_isUnlocked;

    public static byte v_maxDim=3;
    public static BigDecimal vCollapse_count=BigDecimal.valueOf(0);
    public static BigDecimal vCollapse_mlt=BigDecimal.valueOf(0);
    public static BigDecimal vCollapse_price=BigDecimal.valueOf(0);



    public static void RESET_VP(){
        vars.VCl_size0=10;
        vars.VP_delay=1;
        vars.VP_delayUpd_cost=1;
        vars.VP_dvn=10;
        vars.VP_dvn_UpCost=2;
        vars.VP_perClick_upCost=1;
        vars.VP_perClick=1;
        vars.VCl=0;
        vars.VP=0;
    }
}
