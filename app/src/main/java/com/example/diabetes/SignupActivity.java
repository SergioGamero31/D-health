package com.example.diabetes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import Fragments.PedometerFragment;

public class SignupActivity extends AppCompatActivity {

    private EditText rTextName, rTextEmail, rTextPassword, rTextRpassword;
    private Button  registerButton;

    private String rname, remail, rpassword, rrpassword;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        changeStatusBarColor();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        rTextName = (EditText) findViewById(R.id.reditTextName);
        rTextEmail = (EditText) findViewById(R.id.reditTextEmail);
        rTextPassword = (EditText) findViewById(R.id.reditTextPassword);
        rTextRpassword = (EditText) findViewById(R.id.reditTextRpassword);
        registerButton = (Button) findViewById(R.id.cirRegisterButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rname = rTextName.getText().toString().trim();
                remail = rTextEmail.getText().toString().trim();
                rpassword = rTextPassword.getText().toString().trim();
                rrpassword = rTextRpassword.getText().toString().trim();

                if(!rname.isEmpty() && !remail.isEmpty() && !rpassword.isEmpty() && !rrpassword.isEmpty() ){
                    if(rpassword.length()>=6){
                        if(rpassword.equals(rrpassword)){
                            registerUser();
                        }else{
                            Toast.makeText(SignupActivity.this, R.string.signup_m_pass , Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(SignupActivity.this, R.string.signup_m_pass2, Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(SignupActivity.this, R.string.signup_m_fields, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void registerUser(){
        Intent intent = new Intent(this, SignData.class);
        intent.putExtra("name", rname);
        intent.putExtra("email", remail);
        intent.putExtra("password", rpassword);
        startActivity(intent);
    }
   public void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    public void onLoginClick(View view){

        startActivity(new Intent(this, LoginActivity.class));
        finish();
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}