package com.dti.virtualdimensions;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Dim {
    public BigDecimal count;
    public BigDecimal realCount;
    public BigDecimal price;
    public BigDecimal mlt;
    public BigDecimal priceUp;
    public Dim(BigDecimal count, BigDecimal price, BigDecimal mlt, float priceUp){
        this.count=count;
        this.price=price;
        this.mlt=mlt;
        this.priceUp=BigDecimal.valueOf(priceUp);
        this.realCount=BigDecimal.valueOf(0);
    }
    public Dim(BigDecimal count, BigDecimal realCount, BigDecimal price, BigDecimal mlt, BigDecimal priceUp){
        this.count=count;
        this.price=price;
        this.mlt=mlt;
        this.priceUp=priceUp;
        this.realCount=realCount;
    }
    public Dim(long price, double priceUp){
        this.count=BigDecimal.valueOf(0f);
        this.price=BigDecimal.valueOf(price);
        this.priceUp=BigDecimal.valueOf(priceUp);
        this.mlt=BigDecimal.valueOf(1f);
        this.realCount=BigDecimal.valueOf(0);
    }
    public void buy(){
        if (vars.v_VP.compareTo(this.price)>=0){
            this.count = this.count.add(vars.vBuyMlt);
            vars.v_VP=vars.v_VP.subtract(this.price);
            this.price=this.price.multiply(this.priceUp);
            this.realCount=this.realCount.add(vars.vBuyMlt);
        }
    }
    public String toString(){
        return ":"+this.count.toString()+"_"+this.realCount.toString()+"_"+this.price.toString()+"_"+this.mlt.toString()+"_"+this.priceUp.toString()+":";
    }
    public static Dim fromString(String str){
        str=str.replace(":", "");
        String[] data;
        data = str.split("_");
        BigDecimal count = new BigDecimal(data[0]);
        BigDecimal rCount = new BigDecimal(data[1]);
        BigDecimal price = new BigDecimal(data[2]);
        BigDecimal mlt = new BigDecimal(data[3]);
        BigDecimal priceUp = new BigDecimal(data[4]);
        return new Dim(count, rCount, price, mlt, priceUp);
    }
}
