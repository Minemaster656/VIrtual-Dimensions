package com.dti.virtualdimensions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Utils {
    public static String bd2s(BigDecimal value, int scale){
        BigDecimal formattedValue = value.setScale(scale, RoundingMode.HALF_UP); // определение количества знаков после запятой
        return formattedValue.toString(); // конвертирование BigDecimal в строку с тремя знаками после запятой
    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (true){
//            Exp a = Exp.st2ex(in.nextLine());
//            Exp b = Exp.st2ex(in.nextLine());
//            System.out.println("-: "+ Exp.ex2st(Exp.dif(a, b)));
//            System.out.println("+: "+ Exp.ex2st(Exp.sum(a, b)));
//            System.out.println("*: "+ Exp.ex2st(Exp.mlt(a, b)));
//            System.out.println("/: "+ Exp.ex2st(Exp.div(a, b)));
//            System.out.println("^2: "+ Exp.ex2st(Exp.pow(a, 2)) + "^2 (b): "+ Exp.ex2st(Exp.pow(b, 2)));
//            System.out.println("sqrt: "+ Exp.ex2st(Exp.sqrt(a)) + "sqrt (b): "+ Exp.ex2st(Exp.sqrt(b)));
//            System.out.println("log10: "+ Exp.log(a, 10));
//            System.out.println(">, <, <=, >=, ==, !=: "+ Exp.cmp(a, b, ">") + Exp.cmp(a, b, ">")+ Exp.cmp(a, b, "<=")+ Exp.cmp(a, b, ">=")+ Exp.cmp(a, b, "==")+ Exp.cmp(a, b, "!="));
//
//        }
//    }
}
