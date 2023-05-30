package com.dti.virtualdimensions;

import android.graphics.Paint;
import android.graphics.text.LineBreaker;
import android.support.v4.os.IResultReceiver;
import android.text.Layout;
import android.util.TypedValue;
import android.widget.Button;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static String bd2s(BigDecimal value, int scale) {
        BigDecimal formattedValue = value.setScale(scale, RoundingMode.HALF_UP); // определение количества знаков после запятой
        return formattedValue.toString(); // конвертирование BigDecimal в строку с тремя знаками после запятой
    }

    public static String bd2txt(BigDecimal value) {
        BigDecimal svalue = value.setScale(2, RoundingMode.DOWN);
//        svalue=value;
        String result;
//        svalue.setScale(2, RoundingMode.DOWN);
//        if (value.compareTo(BigDecimal.valueOf(1E-7))<0&value.compareTo(BigDecimal.valueOf(-1E-7))>0) return "0";
        if (svalue.compareTo(BigDecimal.valueOf(1E10)) >= 0) {
//            result = String.format("%e", svalue.toString());
            result = String.format("%e", svalue);
            result = result.replace("+", "");
            result = result.replace("E", "e");
            String[] temp0 = result.split("e");
            temp0[0]=temp0[0].replace(",", ".");
            float mant = Float.valueOf(temp0[0]);
            BigDecimal bd = new BigDecimal(Float.toString(mant));
            bd = bd.setScale(2, BigDecimal.ROUND_DOWN);
            float rs = bd.floatValue();
            result = rs + "e" + temp0[1];
        } else {
            result = svalue.toString();
        }
        result = result.replace("+", "");
        result = result.replace("E", "e");
        return result;
//        NumberFormat formatter = new DecimalFormat("0.0E0");
//        formatter.setMinimumFractionDigits(2);
//        return formatter.format(value);
    }

    public static String millsToTime(long millis) {
//        long sec = mills % 1000;
//        long min = sec % 60;
//        long hr = min % 60;
//        long day = hr % 24;
//        long month = (long) (day % 30.417);
//        long year = (long) (month % 12.008);
//        long rmil = mills - sec;
//        long rsec = sec - min;
//        long rmin = min - hr;

        long years = TimeUnit.MILLISECONDS.toDays(millis) / 365;
        long days = TimeUnit.MILLISECONDS.toDays(millis) % 365;
        long months = days / 30;
        days = days % 30;
        long hours = TimeUnit.MILLISECONDS.toHours(millis) % 24;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60;
        long milliseconds = millis % 1000;

        return String.format("%d:%02d:%02d:%02d:%02d:%02d.%03d", years, months, days, hours, minutes, seconds, milliseconds);


    }

    public static void FitText(Button button) {
        button.post(new Runnable() {
            @Override
            public void run() {
                button.setMaxEms(button.getWidth() / 2);
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

    public static BigDecimal loge(BigDecimal num) {
        BigDecimal x = num; // число e
//        MathContext mc = new MathContext(10); // точность вычислений, заданная в 50 знаков после запятой
//
//        BigDecimal result = BigDecimal.ZERO;
//        BigDecimal term = BigDecimal.ONE; // первый член ряда Тейлора
//
//        for (int i = 1; i <= 100; i++) { // ограничение на количество итераций
//            result = result.add(term, mc);
//            term = term.multiply(x.subtract(BigDecimal.ONE).divide(x, mc), mc).setScale(mc.getPrecision(), BigDecimal.ROUND_HALF_UP);
//        }
//
////        System.out.println(result);
//        return result;


//        BigDecimal result = BigDecimal.ZERO;
//        int precision = 100;
//        BigDecimal term = BigDecimal.ONE;
//        BigDecimal oneMinusX = BigDecimal.ONE.subtract(x);
//        for (int n = 1; n <= precision; n++) {
//            term = term.multiply(oneMinusX).divide(new BigDecimal(n), MathContext.DECIMAL128);
//            result = result.add(term);
//        }

        String[] parts = Utils.bd2txt(num).split("e");
        BigDecimal result = new BigDecimal(parts[1]);
        return result;
    }
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

