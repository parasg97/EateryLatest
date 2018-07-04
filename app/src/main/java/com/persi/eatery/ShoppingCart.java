package com.persi.eatery;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tonyvu.sc.exception.ProductNotFoundException;
import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.model.Saleable;
import com.android.tonyvu.sc.util.CartHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart extends AppCompatActivity {
    private TextView mUser_name;
    private String mUsername;
    private String mPreviousActivity;
    private Cart mCart;
    private ListView mListView;
    private String mUserEmail;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private CartListAdapter cartListAdapter;

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
            mListView=findViewById(R.id.listView);
            cartListAdapter=new CartListAdapter(ShoppingCart.this);
            mListView.setAdapter(cartListAdapter);
        }
        }catch (Exception e){
            Log.d("Eatery",e.toString());
        }

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            mUsername = extras.getString("USER_NAME");
            mPreviousActivity = extras.getString("Acitivity");

        }

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        mUserEmail=mUser.getEmail().toString();
        mUsername=mUser.getEmail().toString().substring(0,mUser.getEmail().toString().indexOf('@'));
        mUser_name = findViewById(R.id.restaurant_listView);
        mUser_name.setText(mUsername);
    }

    public void checkEmpty() {
        ArrayList<Saleable> mData = new ArrayList<>(mCart.getProducts());
        if(mData.isEmpty()) {
            Log.d("Eatery", "DATA EMPTY");
            setContentView(R.layout.activity_shopping_cart);
        } else {
            Log.d("Eatery", "DATA NOT EMPTY");
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
    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public Boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal==0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    /*public void placeOrder(View v) {

        SendMail sm = new SendMail(this, mUserEmail,"New order", mCart.toString());
        if(isNetworkAvailable()){
            sm.execute();
            Log.d("Eatery","mail:"+mUserEmail);
        }

        else
            Toast.makeText(this,"Internet is not available",Toast.LENGTH_SHORT).show();
    }
*/

    @Override
    public void onBackPressed(){
       Log.d("Eatery","back_pressed");

    }

    public void checkout(View view)
    {
        Intent a = new Intent(this, Checkout.class);
        finish();
        startActivity(a);
    }

    public void changeLoginId(View v) {
        //Log.d("Eatery",changeLoginIdRestaurentActivitymAuth.getCurrentUser().getUid());
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this,Login_Activity.class);
        intent.putExtra("USER_NAME", mUsername);
        finish();
        startActivity(intent);
    }
    public void clearCart(View v){
        mCart.clear();
        cartListAdapter.notifyDataSetChanged();
        setContentView(R.layout.activity_shopping_cart);

    }
}
