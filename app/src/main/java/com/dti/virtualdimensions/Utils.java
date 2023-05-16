package com.dti.virtualdimensions;

import android.graphics.Paint;
import android.graphics.text.LineBreaker;
import android.text.Layout;
import android.util.TypedValue;
import android.widget.Button;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Utils {
    public static String bd2s(BigDecimal value, int scale){
        BigDecimal formattedValue = value.setScale(scale, RoundingMode.HALF_UP); // определение количества знаков после запятой
        return formattedValue.toString(); // конвертирование BigDecimal в строку с тремя знаками после запятой
    }
    public static String bd2txt(BigDecimal value){
        BigDecimal svalue=value.setScale(2, RoundingMode.DOWN);
//        svalue=value;
        String result;
//        svalue.setScale(2, RoundingMode.DOWN);
//        if (value.compareTo(BigDecimal.valueOf(1E-7))<0&value.compareTo(BigDecimal.valueOf(-1E-7))>0) return "0";
        if (svalue.compareTo(BigDecimal.valueOf(1E10))>=0){
//            result = String.format("%e", svalue.toString());
            result = String.format("%e", svalue);
        }
        else {
            result = svalue.toString();
        }
        result=result.replace("+","");
        result=result.replace("E","e");
        return result;
    }
    public static void FitText(Button button){
        button.post(new Runnable() {
            @Override
            public void run() {
                button.setMaxEms(button.getWidth()/2);
                button.setBreakStrategy(LineBreaker.BREAK_STRATEGY_SIMPLE);

                int maxWidth = button.getWidth();
                float textSize = button.getTextSize();
                Paint paint = new Paint();
                paint.setTextSize(textSize);
                float textWidth = paint.measureText(button.getText().toString());
                while (textWidth > maxWidth) {
                    textSize--;
                    paint.setTextSize(textSize);
                    textWidth = paint.measureText(button.getText().toString());
                }
                button.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }
        });
    }
    //Button button = findViewById(R.id.button);
//button.post(new Runnable() {
//        @Override
//        public void run() {
//            int maxWidth = button.getWidth();
//            float textSize = button.getTextSize();
//            Paint paint = new Paint();
//            paint.setTextSize(textSize);
//            float textWidth = paint.measureText(button.getText().toString());
//            while (textWidth > maxWidth) {
//                textSize--;
//                paint.setTextSize(textSize);
//                textWidth = paint.measureText(button.getText().toString());
//            }
//            button.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
//        }
//    });

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
