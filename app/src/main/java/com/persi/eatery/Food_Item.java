package com.persi.eatery;

import com.android.tonyvu.sc.model.Saleable;

import java.math.BigDecimal;

/**
 * Created by Persi on 06-02-2018.
 */

public class Food_Item implements Saleable{

    private String mFoodname, mFoodDetail;
    private int mVegnonVeg;
    private BigDecimal price;

    public Food_Item(String foodname, String foodDetail, int vegnonVeg, BigDecimal price) {
        mFoodname = foodname;
        mFoodDetail = foodDetail;
        mVegnonVeg = vegnonVeg;
        this.price = price;
    }


    public String getFoodDetail() {
        return mFoodDetail;
    }

    public int getVegnonVeg() {
        return mVegnonVeg;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return mFoodname;
    }
}
