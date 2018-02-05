package com.persi.eatery;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {


    //UI variables
    private TextInputEditText mNameView;
    private TextInputEditText mEmailView;
    private TextInputEditText mHostelView;
    private TextInputEditText mRoomView;
    private TextInputEditText mPhoneView;
    private TextInputEditText mPasswordView;
    private TextInputEditText mConfirmPasswordView;
    private DatabaseReference mUserDetails;
    private String uId;

    //Firebase auth
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mNameView=(TextInputEditText)findViewById(R.id.name);
        mEmailView=(TextInputEditText)findViewById(R.id.register_email);
        mHostelView=(TextInputEditText)findViewById(R.id.hostel);
        mRoomView=(TextInputEditText)findViewById(R.id.room);
        mPhoneView=(TextInputEditText)findViewById(R.id.register_ph_no);
        mPasswordView=(TextInputEditText)findViewById(R.id.register_password);
        mConfirmPasswordView=(TextInputEditText)findViewById(R.id.register_confirm_password);

        mAuth=FirebaseAuth.getInstance();

        mConfirmPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == 15 || id == EditorInfo.IME_NULL) {
                    attemptRegistration();
                    return true;
                }
                return false;
            }
        });


    }

    public void cancelRegistration(View v) {
        Intent intent = new Intent(this,Login_Activity.class);
        finish();
        startActivity(intent);
    }

    public void signUp(View v) {
        attemptRegistration();
    }

    private void attemptRegistration() {

        // Reset errors displayed in the form.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mNameView.setError(null);
        mHostelView.setError(null);
        mRoomView.setError(null);
        mConfirmPasswordView.setError(null);
        mPhoneView.setError(null);



        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String name = mNameView.getText().toString();
        String hostel = mHostelView.getText().toString();
        String room = mRoomView.getText().toString();
        String phone_no = mPhoneView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView = mNameView;
            cancel = true;
        }
        if (TextUtils.isEmpty(hostel)) {
            mHostelView.setError(getString(R.string.error_field_required));
            focusView = mHostelView;
            cancel = true;
        }
        if (TextUtils.isEmpty(room)) {
            mRoomView.setError(getString(R.string.error_field_required));
            focusView = mRoomView;
            cancel = true;
        }
        if (TextUtils.isEmpty(phone_no)) {
            mPhoneView.setError(getString(R.string.error_field_required));
            focusView = mPhoneView;
            cancel = true;
        }



        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            createFirebaseUser();

        }
    }


    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        String confirmPassword = mConfirmPasswordView.getText().toString();
        return confirmPassword.equals(password) && password.length() > 4;
    }


    private void createFirebaseUser() {

        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();
        final String name = mNameView.getText().toString();
        final String hostel = mHostelView.getText().toString();
        final String room = mRoomView.getText().toString();
        final String phone_no = mPhoneView.getText().toString();
        mUserDetails= FirebaseDatabase.getInstance().getReference();




        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Eatery", "createUser onComplete: " + task.isSuccessful());

                        if(!task.isSuccessful()){
                            Log.d("Eatery", "user creation failed");
                            showErrorDialog("Registration attempt failed");
                        } else {
                            //saveDisplayName();

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                uId=user.getUid();
                            } else {
                                Log.d("Eatery","no user signed in");
                            }
                            UserDetails details=new UserDetails(name,email,hostel,room,phone_no,uId);
                            mUserDetails= FirebaseDatabase.getInstance().getReference();
                            mUserDetails.child("UserDetails").push().setValue(details);
                            Intent intent = new Intent(Register.this, Login_Activity.class);
                            finish();
                            startActivity(intent);
                        }
                    }
                });
    }

    private void showErrorDialog(String message){

        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

}
