package com.dti.virtualdimensions;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {
    public static String bd2s(BigDecimal value, int scale){
        BigDecimal formattedValue = value.setScale(scale, RoundingMode.HALF_UP); // определение количества знаков после запятой
        return formattedValue.toString(); // конвертирование BigDecimal в строку с тремя знаками после запятой
    }

    public static void main(String[] args) {

    }
}
