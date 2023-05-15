package com.dti.virtualdimensions;

import java.math.BigDecimal;

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
}
