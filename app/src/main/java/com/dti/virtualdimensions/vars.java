package com.dti.virtualdimensions;

public class vars {
    static Exp inf=Exp.st2ex("1.8e308");
    static Exp VP = Exp.st2ex("0e0"); //в.ч.
    static Exp VCl = Exp.st2ex("1e2"); //в.к.
    static Exp VCl_size = Exp.lng2ex(10); static Exp VCl_size0 = Exp.lng2ex(10); //стоимость кластера, стоимость по умолчанию
    static double VP_delay = 1; //задержка исчезновения (секунды)
    static Exp VP_delayUpd_cost = Exp.lng2ex(1); //стоимость апгрейда ⬆
    static Exp VP_dvn = Exp.lng2ex(10); //деитель при исчезновении
    static Exp VP_dvn_UpCost = Exp.lng2ex(2); //стоимость апгрейда делителя
    static Exp VP_brake_cost=Exp.lng2ex(10); // стоимость сжигания учебника квантовой физики
    static Exp VP_perClick = Exp.lng2ex(1); //в.ч. за клик
    static Exp VP_perClick_upCost=Exp.lng2ex(1);
    static Exp VP_cp_bought = Exp.st2ex("0e0");
    static Exp UP_perCLick_upMod=Exp.lng2ex(2);
    static Exp VCl_max = inf; //максимум кластеров в.ч.
    static boolean isVPBroken; //квантоваяФизика.сломать();
    static int FPS = 50;
}
