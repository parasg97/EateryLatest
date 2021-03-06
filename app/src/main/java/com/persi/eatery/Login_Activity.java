package com.persi.eatery;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Login_Activity extends AppCompatActivity {

    private TextInputEditText mEmailView;
    private TextInputEditText mPasswordView;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        mEmailView=(TextInputEditText)findViewById(R.id.login_email);
        mPasswordView=(TextInputEditText)findViewById(R.id.login_password);
        mAuth = FirebaseAuth.getInstance();
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == 15 || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

    }

    public void signInExistingUser(View v)   {

       attemptLogin();

    }

    private void attemptLogin() {

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (email.isEmpty())
            if (email.equals("") || password.equals("")) return;
        Toast.makeText(this, "Login in progress...", Toast.LENGTH_SHORT).show();


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                //Log.d("Eatery", "AttemptLogin,LoGinActivitysignInWithEmail() onComplete: " + task.isSuccessful());

                if (!task.isSuccessful()) {
                    //Log.d("Eatery", "AttemptLogin,LoGinActivityProblem signing in: " + task.getException());
                    showErrorDialog("There was a problem signing in");
                } else {
                    //Log.d("Eatery","AttemptLogin,LoGinActivitystartin new intent");
                    Intent intent = new Intent(Login_Activity.this,RestaurantActivity.class);
                    finish();
                    startActivity(intent);
                }

            }
        });


    }

    public void registerNewUser(View v) {
        Intent intent = new Intent(this,Register.class);
        finish();
        startActivity(intent);
    }
    private void showErrorDialog(String message) {

        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
