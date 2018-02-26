package com.persi.eatery;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.util.CartHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeSet;

public class Checkout extends AppCompatActivity {

    private ArrayList mData = new ArrayList();
    private TreeSet mSeparatorsSet = new TreeSet();
    private ListView mListView;
    private CheckoutAdapter mCheckoutAdapter;
    private Cart mCart;
    private TextView mTotalPrice;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String mHostel;
    private String mRoom;
    private String mRealName;
    private String mPhoneNumber;
    private String mUid;
    private String mUserEmail;
    private DataSnapshot mDataSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        mTotalPrice=findViewById(R.id.total_price_num);
        mCart=CartHelper.getCart();
        mTotalPrice.setText(mCart.getTotalPrice().toString());
        mListView=findViewById(R.id.listView_checkout);
        mCheckoutAdapter = new CheckoutAdapter(this);
        mListView.setAdapter(mCheckoutAdapter);

        //;//mDataSnapshot
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        mUid=mUser.getUid();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("UserDetails").child(mUid);
        Log.d("Eatery",mUid);
        mUserEmail=mUser.getEmail();
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Eatery","whatt");
                mRealName=dataSnapshot.child("name").getValue().toString();
                mHostel=dataSnapshot.child("hostel").getValue().toString();
                mRoom=dataSnapshot.child("room").getValue().toString();
                mPhoneNumber=dataSnapshot.child("phone_no").getValue().toString();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



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
    public void placeOrder(View v) {

        SendMail sm1 = new SendMail(this,mUserEmail ,"New order", "Hi a new order is placed by you:\n"+mCart.toString());
       // SendMail sm2 = new SendMail(this, "eateryps@gmail.com","New order", "New order has" +
         //       "been placed by "+mRealName+" "+mPhoneNumber+" "+mHostel+" "+mRoom + mCart.toString());
        if(isNetworkAvailable()){
            sm1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            //sm2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            Log.d("Eatery","mail:"+mUserEmail);
        }

        else
            Toast.makeText(this,"Internet is not available",Toast.LENGTH_SHORT).show();
        Log.d("Eatery","realname:"+mRealName);
    }

}
