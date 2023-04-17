package com.dti.virtualdimensions;
//
public class Exp {
//    public static void main(String[] args) {
//        Exp a = st2ex("1e10");
//        Exp b = st2ex("1e3");
//        System.out.println(ex2st(mlt(a, b)));
//        System.out.println(ex2st(div(a, b)));
//    }
    float mantissa;
    long exponent;

    public Exp(float mantissa, long exponent) {
        this.mantissa = mantissa;
        this.exponent = exponent;
    }
    public static Exp st2ex(String a){
        String[] tmp = a.split("e");
        return new Exp(Float.parseFloat(tmp[0]), Long.parseLong(tmp[1]));
    }
    public static Exp ex(String a){
        String[] tmp = a.split("e");
        return new Exp(Float.parseFloat(tmp[0]), Long.parseLong(tmp[1]));
    }
    public static String ex2st(Exp a){
        return a.mantissa + "e" + a.exponent;
    }
//    public static Exp db2ex(double db) {
//        String sdb = Double.toString(db);
////        System.out.println(sdb);
//
//        String[] tmp = sdb.split("E");
//        tmp[1]=tmp[1].substring(1);
//        return new Exp(Float.parseFloat(tmp[0]), Long.parseLong(tmp[1]));
//    }
    public static Exp sum(Exp a, Exp b) {
//        long dif = a.exponent - b.exponent;
//        if (dif >7 | dif < -7){
//            return a;
//        }
//        if (a.exponent > b.exponent){
//            float dmb = (float) (b.mantissa/Math.pow(10, a.exponent-b.exponent));
//
//        }
//        return null;
        if (a.exponent != b.exponent) {
            if (a.exponent > b.exponent) {
                b = new Exp(b.mantissa * (float) Math.pow(10, a.exponent - b.exponent), a.exponent);
            } else {
                a.exponent = b.exponent;
                a.mantissa = a.mantissa * (float) Math.pow(10, b.exponent - a.exponent);
            }
        }

        return Exp.normalize(new Exp(a.mantissa + b.mantissa, a.exponent));
    }

    public static Exp dif(Exp a, Exp b) {
        if (a.exponent != b.exponent) {
            if (a.exponent > b.exponent) {
                b = new Exp(b.mantissa * (float) Math.pow(10, a.exponent - b.exponent), a.exponent);
            } else {
                a.exponent = b.exponent;
                a.mantissa = a.mantissa * (float) Math.pow(10, b.exponent - a.exponent);
            }
        }

        return Exp.normalize(new Exp(a.mantissa - b.mantissa, a.exponent));
    }
    public static Exp mlt(Exp a, Exp b){
//        float ma = a.mantissa;
//        float mb = b.mantissa;
//        long ea = a.exponent;
//        long eb = b.exponent;
//        long temp1 = ea+eb; //new ex
//        float temp2 = ma*mb; //new mn
//        if (temp2/10>=1){
//            long temp3 = (long) (temp2%10);
//            temp2-=10*temp3;
//            temp1+=temp3;
//        }
//        return new Exp(temp2, temp1);
        return Exp.normalize(new Exp(a.mantissa * b.mantissa, a.exponent + b.exponent));

    }
    public static Exp div(Exp a, Exp b){
//        float ma = a.mantissa;
//        float mb = b.mantissa;
//        long ea = a.exponent;
//        long eb = b.exponent;
//        long temp1 = ea-eb;
//        float temp2 = ma/mb;
//        if (temp2 < 1){
//            temp2*=10;
//            temp1--;
//        }
//        return new Exp(temp2, temp1);
        return Exp.normalize(new Exp(a.mantissa / b.mantissa, a.exponent - b.exponent));

    }
    public static Exp normalize(Exp in){
        while (in.mantissa < 0.1 && in.mantissa > 0){
            in.mantissa*=10;
            in.exponent-=1;
        }
        while (in.mantissa >=10){
            in.mantissa/=10;
            in.exponent+=1;
        }
        return in;
    }


//}
//public class Exp {
//    private float mantissa;
//    private long exponent;

    // Конструктор класса
//    public Exp(float mantissa, long exponent) {
//        this.mantissa = mantissa;
//        this.exponent = exponent;
//    }

    // Конвертация строки в правильной записи в объект класса
    public static Exp parseString(String str) {
        String[] parts = str.split("e");
        float mantissa = Float.parseFloat(parts[0]);
        long exponent = Long.parseLong(parts[1]);
        return new Exp(mantissa, exponent);
    }

    // Объект класса в строку с записью через e
    public String toString() {
        return mantissa + "e" + exponent;
    }

    // Сложение
    public Exp add(Exp other) {
        // Если экспоненты разные, делаем их одинаковыми
        if (this.exponent != other.exponent) {
            if (this.exponent > other.exponent) {
                other = new Exp(other.mantissa * (float) Math.pow(10, this.exponent - other.exponent), this.exponent);
            } else {
                this.exponent = other.exponent;
                this.mantissa = this.mantissa * (float) Math.pow(10, other.exponent - this.exponent);
            }
        }

        return new Exp(this.mantissa + other.mantissa, this.exponent);
    }

    // Вычитание
    public Exp subtract(Exp other) {
        // Если экспоненты разные, делаем их одинаковыми
        if (this.exponent != other.exponent) {
            if (this.exponent > other.exponent) {
                other = new Exp(other.mantissa * (float) Math.pow(10, this.exponent - other.exponent), this.exponent);
            } else {
                this.exponent = other.exponent;
                this.mantissa = this.mantissa * (float) Math.pow(10, other.exponent - this.exponent);
            }
        }

        return new Exp(this.mantissa - other.mantissa, this.exponent);
    }

    // Деление
    public Exp divide(Exp other) {
        return new Exp(this.mantissa / other.mantissa, this.exponent - other.exponent);
    }

    // Умножение
    public Exp multiply(Exp other) {
        return new Exp(this.mantissa * other.mantissa, this.exponent + other.exponent);
    }

    // Возведение в степень
    public Exp pow(long power) {
        return new Exp((float) Math.pow(this.mantissa, power), this.exponent * power);
    }

    // Получение корня
    public Exp sqrt() {
        return new Exp((float) Math.sqrt(this.mantissa), this.exponent / 2);
    }

    // Логарифм с произвольным основанием
    public double log(double base) {
        return Math.log10(this.mantissa) / Math.log10(base) + exponent;
    }

    // Перевод из float в объект класса
    public static Exp fromFloat(float f) {
        long exponent = (long) Math.floor(Math.log10(f));
        float mantissa = f / (float) Math.pow(10, exponent);
        return new Exp(mantissa, exponent);
    }

    // Перевод из long в объект класса
    public static Exp fromLong(long l) {
        return new Exp(l, 0);
    }

    // Логические операции
    public boolean equals(Exp other) {
        return this.mantissa == other.mantissa && this.exponent == other.exponent;
    }
}