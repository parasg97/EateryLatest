package com.persi.eatery;

import android.util.Log;

import com.android.tonyvu.sc.model.Saleable;

import java.math.BigDecimal;

/**
 * Created by Persi on 06-02-2018.
 */

public class Food_Item implements Saleable{

    private String mFoodname, mFoodDetail;
    private int mVegnonVeg;
    private BigDecimal mPrice;

    public Food_Item(String foodname, String foodDetail, int vegnonVeg, BigDecimal price) {
        mFoodname = foodname;
        mFoodDetail = foodDetail;
        mVegnonVeg = vegnonVeg;
        this.mPrice = price;
    }


    public String getFoodDetail() {
        return mFoodDetail;
    }

    public int getVegnonVeg() {
        return mVegnonVeg;
    }

    @Override
    public BigDecimal getPrice() {
        return mPrice;
    }

    @Override
    public String getName() {
        return mFoodname;
    }

    @Override
    public boolean equals(Object f){
        Food_Item food=(Food_Item)f;
        if(food.getName().equals(this.getName()) && food.getPrice().equals(this.getPrice())){
            return true;
        }
        else
            return false;
    }

    @Override
    public int hashCode(){
        String s = mFoodname;
        int hash=0;
        for(int i=0;i<s.length();i++)
        {
            char z = s.charAt(i);
            hash = hash+(int)z;
        }
        hash+=this.getPrice().intValue();
        return hash;
    }
}
