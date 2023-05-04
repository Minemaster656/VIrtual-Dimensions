package com.dti.virtualdimensions;

import java.math.BigDecimal;

public class vars {
    static double inf=1.79e308;
    static double GAME_TICKSPEED=0.1;
    public static double GAME_TICKSPEED_MULTIPLIER=1;
    static double VP = 1; //Double.parseDouble("1000"); //в.ч.
    static double VCl; //в.к.
    static double VCl_size = 10; static double VCl_size0 = 10; static double VCl_size0_default = 10; static double VCl_cost=1.1; static double VCl_costUp_cost=10;//стоимость кластера, стоимость по умолчанию
    static double VP_delay = 1; static double VP_delay_default = 1; //задержка исчезновения (секунды)
    static double VP_delayUpd_cost = 1; static double VP_delayUpd_cost_default = 1;//стоимость апгрейда ⬆
    static double VP_dvn = 10; static double VP_dvn_default = 10;//деитель при исчезновении
    static double VP_dvn_UpCost = 2; static double VP_dvn_UpCost_default = 2;//стоимость апгрейда делителя
    static double VP_brake_cost=1024; // стоимость сжигания учебника квантовой физики
    static double VP_perClick = 1; static double VP_perClick_default = 1;//в.ч. за клик
    static double VP_perClick_upCost=1; static double VP_perClick_upCost_default = 1;
    static double VP_cp_bought;
    static double UP_perCLick_upMod=2;
    static double VCl_max = inf; //максимум кластеров в.ч.
    static boolean isVPBroken; //квантоваяФизика.сломать();
    static int FPS = 40; //~~50~~
    static double VP_perCLick_mlt_total=1;
    static double VP_prestige0_multiplier=1;
    static double VP_prestige0_multiplier_new=1;
    public static double VP_prestige0_mlt=1;
    public static double VP_prestige0_mlt_cost=10;
    public static double VP_extraCP_mlt=1;
    public static double VP_extraCP_cost=4;



    //QUARKS
    public static BigDecimal quarks;
    public static BigDecimal q_tickspeed;

    public static BigDecimal qDim1_count;
    public static BigDecimal qDim1_price;
    public static BigDecimal qDim1_mlt;
    public static boolean qDim1_isUnlocked;

    public static BigDecimal qDim2_count;
    public static BigDecimal qDim2_price;
    public static BigDecimal qDim2_mlt;
    public static boolean qDim2_isUnlocked;

    public static BigDecimal qDim3_count;
    public static BigDecimal qDim3_price;
    public static BigDecimal qDim3_mlt;
    public static boolean qDim3_isUnlocked;

    public static BigDecimal qDim4_count;
    public static BigDecimal qDim4_price;
    public static BigDecimal qDim4_mlt;
    public static boolean qDim4_isUnlocked;

    public static BigDecimal qDim5_count;
    public static BigDecimal qDim5_price;
    public static BigDecimal qDim5_mlt;
    public static boolean qDim5_isUnlocked;

    public static BigDecimal qDim6_count;
    public static BigDecimal qDim6_price;
    public static BigDecimal qDim6_mlt;
    public static boolean qDim6_isUnlocked;

    public static byte q_maxDim=4;
    public static BigDecimal qCollapse_count;
    public static BigDecimal qCollapse_mlt;
    public static BigDecimal qCollapse_price;



    public static void RESET_VP(){
        vars.VCl_size0=vars.VCl_size0_default;
        vars.VP_delay=vars.VP_delay_default;
        vars.VP_delayUpd_cost=vars.VP_delayUpd_cost_default;
        vars.VP_dvn=vars.VP_dvn_default;
        vars.VP_dvn_UpCost=vars.VP_dvn_UpCost_default;
        vars.VP_perClick_upCost=vars.VP_perClick_upCost_default;
        vars.VP_perClick=vars.VP_perClick_default;
        vars.VCl=0;
        vars.VP=0;
    }
}
