package com.persi.eatery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ShoppingCart extends AppCompatActivity {
    private TextView mUser_name;
    private String mUsername;
    private String mPreviousActivity;
    private  Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        setContentView(R.layout.activity_shopping_cart);
        Log.d("Eatery","wtfff2");
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            Log.d("Eatery","wtfff");
            mUsername = extras.getString("USER_NAME");
            mPreviousActivity=extras.getString("Acitivity");
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
    public void goToAccountSetting(View v) {
        Intent intent = new Intent(this,AccountActivity.class);
        intent.putExtra("USER_NAME", mUsername);
        finish();
        startActivity(intent);
    }
    @Override
    public void onBackPressed(){
        switch (mPreviousActivity) {
            case "RestaurantActivity.class":
                mIntent = new Intent(this,RestaurantActivity.class);
                break;
            case "AccountActivity.class":
                mIntent = new Intent(this,AccountActivity.class);
                break;
        }

        mIntent.putExtra("USER_NAME", mUsername);
        mIntent.putExtra("Acitivity","ShoppingCart.class");
        finish();
        startActivity(mIntent);

    }
}
