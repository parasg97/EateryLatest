package com.persi.eatery;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity {
    private TextView mUser_name;
    private String mUsername;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        setContentView(R.layout.activity_account);
        Log.d("Eatery","account");
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            Log.d("Eatery","wtfff");
            mUsername = extras.getString("USER_NAME");
            mUser_name=findViewById(R.id.restaurant_listView);
            mUser_name.setText(mUsername);
        }
    }
    public void goToHotelMenu(View v) {
        Intent intent = new Intent(this,RestaurantActivity.class);
        intent.putExtra("USER_NAME", mUsername);
        finish();
        startActivity(intent);
    }
    public void goToCart(View v) {
        Intent intent = new Intent(this,ShoppingCart.class);
        intent.putExtra("USER_NAME", mUsername);
        finish();
        startActivity(intent);
    }
}
