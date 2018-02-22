package com.persi.eatery;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.util.CartHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeSet;

public class Checkout extends AppCompatActivity {

    private ArrayList mData = new ArrayList();
    private TreeSet mSeparatorsSet = new TreeSet();
    private ListView mListView;
    private CheckoutAdapter mCheckoutAdapter;
    private Cart mCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        mListView=findViewById(R.id.listView_checkout);
        mCheckoutAdapter = new CheckoutAdapter(this);
        mListView.setAdapter(mCheckoutAdapter);

    }
    public void back(View v) {
        Intent intent = new Intent(this,ShoppingCart.class);
        finish();
        startActivity(intent);
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
/*
    public void placeOrder(View v) {

        SendMail sm = new SendMail(this, mUserEmail,"New order", mCart.toString());
        if(isNetworkAvailable()){
            sm.execute();
            Log.d("Eatery","mail:"+mUserEmail);
        }

        else
            Toast.makeText(this,"Internet is not available",Toast.LENGTH_SHORT).show();
    }
    */
}
