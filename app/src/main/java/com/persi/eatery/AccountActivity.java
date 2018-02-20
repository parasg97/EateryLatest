package com.persi.eatery;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountActivity extends AppCompatActivity {
    private TextView mUser_name;
    private String mUsername;
    private String mnewHostel;
    private String mnewRoom;
    private String mnewNumber;
    private TextInputEditText mHostelView;
    private TextInputEditText mRoomView;
    private TextInputEditText mNumberView;
    private DatabaseReference mUserDetails;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String mUid;
    private String mPreviousActivity;
    private  Intent mIntent;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        setContentView(R.layout.activity_account);
        //Log.d("Eatery","onCreateAccountActivityaccount");
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            //Log.d("Eatery","onCreateAccountActivitywtfff");
            mUsername = extras.getString("USER_NAME");
            mPreviousActivity=extras.getString("Acitivity");
            mUser_name=findViewById(R.id.restaurant_listView);
            mUser_name.setText(mUsername);
        }
        mHostelView=findViewById(R.id.hostel);
        mRoomView=findViewById(R.id.room);
        mNumberView=findViewById(R.id.phone_number);

    }
    public void goToHotelMenu(View v) {
         mIntent = new Intent(this,RestaurantActivity.class);
        mIntent.putExtra("USER_NAME", mUsername);
        mIntent.putExtra("Acitivity","AccountActivity.class");
        finish();
        startActivity(mIntent);
    }
    public void goToCart(View v) {
        mIntent = new Intent(this,ShoppingCart.class);
        mIntent.putExtra("USER_NAME", mUsername);
        mIntent.putExtra("Acitivity","AccountActivity.class");
        finish();
        startActivity(mIntent);
    }
    public void saveChanges(View v) {
        mnewHostel=mHostelView.getText().toString();
        mnewNumber=mNumberView.getText().toString();
        mnewRoom=mRoomView.getText().toString();
        mUserDetails= FirebaseDatabase.getInstance().getReference();
        mAuth= FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        mUid=mUser.getUid();
        try {
            mUserDetails.child("UserDetails").child(mUid).child("hostel").setValue(mnewHostel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mUserDetails.child("UserDetails").child(mUid).child("room").setValue(mnewRoom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mUserDetails.child("UserDetails").child(mUid).child("phone_no").setValue(mnewNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mIntent = new Intent(this,RestaurantActivity.class);
        mIntent.putExtra("USER_NAME", mUsername);
        mIntent.putExtra("Acitivity","AccountActivity.class");
        finish();
        startActivity(mIntent);
    }
    @Override
    public void onBackPressed(){
        switch (mPreviousActivity) {
            case "RestaurantActivity.class":
                 mIntent = new Intent(this,RestaurantActivity.class);
                break;
            case "ShoppingCart.class":
                 mIntent = new Intent(this,ShoppingCart.class);
                break;
        }

        mIntent.putExtra("USER_NAME", mUsername);
        mIntent.putExtra("Acitivity","AccountActivity.class");
        finish();
        startActivity(mIntent);
        /*Intent intent = new Intent(this,LastActivity.class);
        intent.putExtra("USER_NAME", mUsername);
        finish();
        startActivity(intent);*/
    }

    public void changeLoginId(View v) {
        //Log.d("Eatery",changeLoginIdRestaurentActivitymAuth.getCurrentUser().getUid());
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this,Login_Activity.class);
        intent.putExtra("USER_NAME", mUsername);
        finish();
        startActivity(intent);
    }
}
