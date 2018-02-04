package com.persi.eatery;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private TextView mUser_name;
    private String mUsername;
    private ListView mHotelListView;
    private ArrayList<Hotel_Object> mHotelListArray=new ArrayList<Hotel_Object>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        mHotelListArray.add(new Hotel_Object("Pizzeria",R.drawable.ic_pizza,"South Indian, North Indian","60 min Rs200 minimum"));
        mHotelListArray.add(new Hotel_Object("Mac n Cheese",R.drawable.ic_pizza,"South Indian, North Indian","60 min Rs200 minimum"));
        mHotelListArray.add(new Hotel_Object("12 Tables",R.drawable.ic_pizza,"South Indian, North Indian","60 min Rs200 minimum"));
        mHotelListArray.add(new Hotel_Object("SandP",R.drawable.ic_pizza,"South Indian, North Indian","60 min Rs200 minimum"));

        mHotelListView=findViewById(R.id.restaurant_name_listView);
        HotelListViewAdapter hotelAdapter=new HotelListViewAdapter(getApplicationContext(),mHotelListArray);
        mHotelListView.setAdapter(hotelAdapter);

        mAuth=FirebaseAuth.getInstance();
        mUser_name=(TextView)findViewById(R.id.name_textview);
        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                //mUser_name.setText(user.getEmail().toString());

                if(user!=null){
                    Log.d("Eatery","you are still logged in");
                    mUsername=user.getEmail().toString().substring(0,user.getEmail().toString().indexOf('@'));
                    mUser_name.setText(user.getEmail().toString().substring(0,user.getEmail().toString().indexOf('@')));

                }else{
                    Intent intent = new Intent(RestaurantActivity.this,Login_Activity.class);
                    intent.putExtra("USER_NAME", "unicorn");
                    finish();
                    startActivity(intent);
                }
            }
        };
    }
    public void changeLoginId(View v) {
        Log.d("Eatery",mAuth.getCurrentUser().getUid());
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this,Login_Activity.class);
        intent.putExtra("USER_NAME", mUsername);
        finish();
        startActivity(intent);
    }
    public void changeUserDetails(View v) {
        Intent intent = new Intent(this,AccountActivity.class);
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

    @Override
    public void onResume(){
        super.onResume();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
    @Override
    public void onPause(){
        super.onPause();
        if(mAuthStateListener!=null)
        mAuth.removeAuthStateListener(mAuthStateListener);

    }


}
