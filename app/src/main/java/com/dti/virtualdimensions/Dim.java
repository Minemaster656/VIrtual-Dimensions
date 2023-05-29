package com.dti.virtualdimensions;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public void update(){
        this.count=this.count.setScale(10, RoundingMode.DOWN);
        this.realCount=this.realCount.setScale(10, RoundingMode.DOWN);
        this.price=this.price.setScale(10, RoundingMode.DOWN);
        this.mlt=this.mlt.setScale(10, RoundingMode.DOWN);
        this.priceUp=this.priceUp.setScale(10, RoundingMode.DOWN);
        if (this.count.compareTo(BigDecimal.valueOf(0.0001))<0){
            this.count=BigDecimal.valueOf(0);
        }
        if (this.realCount.compareTo(BigDecimal.valueOf(0.0001))<0){
            this.realCount=BigDecimal.valueOf(0);
        }
        if (this.price.compareTo(BigDecimal.valueOf(0.0001))<0){
            this.price=BigDecimal.valueOf(0);
        }
        if (this.priceUp.compareTo(BigDecimal.valueOf(0.0001))<0){
            this.priceUp=BigDecimal.valueOf(0);
        }
        if (this.mlt.compareTo(BigDecimal.valueOf(0.0001))<0){
            this.mlt=BigDecimal.valueOf(0);
        }
    }
    @NonNull
    public String toString(){
        this.update();
        return ":"+Utils.bd2txt(this.count)+"_"+Utils.bd2txt(this.realCount)+"_"+Utils.bd2txt(this.price)+"_"+Utils.bd2txt(this.mlt)+"_"+Utils.bd2txt(this.priceUp)+":";

//        return ":"+this.count.toEngineeringString()+"_"+this.realCount.toEngineeringString()+"_"+this.price.toEngineeringString()+"_"+this.mlt.toEngineeringString()+"_"+this.priceUp.toEngineeringString()+":";
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
