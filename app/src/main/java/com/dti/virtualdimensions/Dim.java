package com.dti.virtualdimensions;

import java.math.BigDecimal;

public class Dim {
    public BigDecimal count;
    public BigDecimal price;
    public BigDecimal mlt;
    public float priceUp;
    public Dim(BigDecimal count, BigDecimal price, BigDecimal mlt, float priceUp){
        this.count=count;
        this.price=price;
        this.mlt=mlt;
        this.priceUp=priceUp;
    }
    public Dim(long price, float priceUp){
        this.count=BigDecimal.valueOf(0f);
        this.price=BigDecimal.valueOf(price);
        this.priceUp=priceUp;
        this.mlt=BigDecimal.valueOf(1f);
    }
    public void buy(){
        if (vars.v_VP.compareTo(this.price)>=0){
            this.count = this.count.add(vars.vBuyMlt);
            vars.v_VP=vars.v_VP.subtract(this.price);
            this.price=this.price.multiply(BigDecimal.valueOf(this.priceUp));
        }
    }
}
