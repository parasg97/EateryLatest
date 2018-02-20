package com.persi.eatery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.tonyvu.sc.exception.ProductNotFoundException;
import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.model.Saleable;
import com.android.tonyvu.sc.util.CartHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart extends AppCompatActivity {
    private TextView mUser_name;
    private String mUsername;
    private String mPreviousActivity;
    private ArrayList<Saleable> mData=new ArrayList<>();
    private Cart mCart;
    private ListView mListView;
    private Map<Saleable, Integer> cartItemMap = new HashMap<Saleable, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        mCart = CartHelper.getCart();
        cartItemMap=mCart.getItemWithQuantity();
        try{if(mCart.getProducts().size()==0){
            setContentView(R.layout.activity_shopping_cart);
        }
        else {
            setContentView(R.layout.cart_non_empty);
            for (final Map.Entry<Saleable, Integer> entry : cartItemMap.entrySet()) {
                mData.add(new Saleable() {
                    @Override
                    public BigDecimal getPrice() {
                        return entry.getKey().getPrice();
                    }

                    @Override
                    public String getName() {
                        return entry.getKey().getName();
                    }
                });

            }

            mListView=findViewById(R.id.listView);
            CartListAdapter cartListAdapter=new CartListAdapter(getApplicationContext(),mData);
            mListView.setAdapter(cartListAdapter);
        }
        }catch (Exception e){
            Log.d("Eatery",e.toString());
        }

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            mUsername = extras.getString("USER_NAME");
            mPreviousActivity = extras.getString("Acitivity");
            mUser_name = findViewById(R.id.restaurant_listView);
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
    public void placeOrder(View v) {

        SendMail sm = new SendMail(this, "parasgupta24feb@gmail.com","New order", mCart.toString());
        sm.execute();
        if(SendMail.done){
            Log.d("Eatery","wtf");
            SendMail.done=false;
            Intent intent = new Intent(this,ThankYou.class);
            //intent.putExtra("USER_NAME", mUsername);
            finish();
            startActivity(intent);
        }



    }
    @Override
    public void onBackPressed(){
       Log.d("Eatery","back_pressed");

    }
}
