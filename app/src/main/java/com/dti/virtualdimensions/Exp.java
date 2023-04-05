package com.dti.virtualdimensions;

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
    public static Exp sum(Exp a, Exp b) {
        long dif = a.exponent - b.exponent;
        if (dif >7 | dif < -7){
            return a;
        }
        if (a.exponent > b.exponent){
            float dmb = (float) (b.mantissa/Math.pow(10, a.exponent-b.exponent));

        }
        return null;
    }

    public static Exp dif(Exp a, Exp b) {
        return null;
    }
    public static Exp mlt(Exp a, Exp b){
        float ma = a.mantissa;
        float mb = b.mantissa;
        long ea = a.exponent;
        long eb = b.exponent;
        long temp1 = ea+eb; //new ex
        float temp2 = ma*mb; //new mn
        if (temp2/10>=1){
            long temp3 = (long) (temp2%10);
            temp2-=10*temp3;
            temp1+=temp3;
        }
        return new Exp(temp2, temp1);
    }
    public static Exp div(Exp a, Exp b){
        float ma = a.mantissa;
        float mb = b.mantissa;
        long ea = a.exponent;
        long eb = b.exponent;
        long temp1 = ea-eb;
        float temp2 = ma/mb;
        if (temp2 < 1){
            temp2*=10;
            temp1--;
        }
        return new Exp(temp2, temp1);

    }


}
