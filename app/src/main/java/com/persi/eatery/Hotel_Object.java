package com.persi.eatery;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by Persi on 03-02-2018.
 */

public class Hotel_Object {
    private String mHotelName;
    private int mHotelImage;
    private String mFirstrow;
    private String mSecondrow;

    public Hotel_Object(String hotelName, int hotelImage, String firstrow, String secondrow) {
        mHotelName = hotelName;
        mHotelImage = hotelImage;
        mFirstrow = firstrow;
        mSecondrow = secondrow;
    }

    public String getHotelName() {
        return mHotelName;
    }

    public int getHotelImage() {
        return mHotelImage;
    }

    public String getFirstrow() {
        return mFirstrow;
    }

    public String getSecondrow() {
        return mSecondrow;
    }
}
