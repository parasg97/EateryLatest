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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        Log.d("Eatery","wtfff2");
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            Log.d("Eatery","wtfff");
            mUsername = extras.getString("USER_NAME");
            mUser_name=findViewById(R.id.name_textview);
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
}
