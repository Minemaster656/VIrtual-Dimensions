package com.dti.virtualdimensions;

public class vars {
    static double inf=1.79e308;
    static double VP = 1; //Double.parseDouble("1000"); //в.ч.
    static double VCl; //в.к.
    static double VCl_size = 10; static double VCl_size0 = 10; static double VCl_size0_default = 10;//стоимость кластера, стоимость по умолчанию
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
    static int FPS = 50;
    static double VP_perCLick_mlt_total=1;
    static double VP_prestige0_multiplier=1;
    static double VP_prestige0_multiplier_new=1;

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
