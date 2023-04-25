package com.dti.virtualdimensions;

public class vars {
    static double inf=1.79e308;
    static double VP = Double.parseDouble("1000"); //в.ч.
    static double VCl; //в.к.
    static double VCl_size = 10; static double VCl_size0 = 10; //стоимость кластера, стоимость по умолчанию
    static double VP_delay = 1; //задержка исчезновения (секунды)
    static double VP_delayUpd_cost = 1; //стоимость апгрейда ⬆
    static double VP_dvn = 10; //деитель при исчезновении
    static double VP_dvn_UpCost = 2; //стоимость апгрейда делителя
    static double VP_brake_cost=10; // стоимость сжигания учебника квантовой физики
    static double VP_perClick = 1; //в.ч. за клик
    static double VP_perClick_upCost=1;
    static double VP_cp_bought;
    static double UP_perCLick_upMod=2;
    static double VCl_max = inf; //максимум кластеров в.ч.
    static boolean isVPBroken; //квантоваяФизика.сломать();
    static int FPS = 50;
}
