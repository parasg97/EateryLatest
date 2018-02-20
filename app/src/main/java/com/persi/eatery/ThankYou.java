package com.persi.eatery;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.util.CartHelper;

public class ThankYou extends AppCompatActivity {
    private Cart mCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        mCart= CartHelper.getCart();
        mCart.clear();
    }


    public void goToHotelMenu(View v) {
        Intent intent = new Intent(this,RestaurantActivity.class);
        intent.putExtra("USER_NAME", "parasg1997");
        finish();
        startActivity(intent);
    }
}
