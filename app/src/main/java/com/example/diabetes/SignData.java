package com.example.diabetes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignData extends AppCompatActivity {

    private EditText rTextAge, rTextWeight, rTextHeight;
    private Spinner spGender;
    private Button continueButton;

    private String rname, remail, rpassword, rgender;
    private String rage, rweight, rheight;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_data_activity);
        changeStatusBarColor();

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        rTextAge = (EditText) findViewById(R.id.reditTextAge);
        rTextWeight = (EditText) findViewById(R.id.reditTextWeight);
        rTextHeight = (EditText) findViewById(R.id.reditTextHeight);
        spGender = (Spinner) findViewById(R.id.spinnerGender);
        continueButton = (Button) findViewById(R.id.continueButton);

        rname = getIntent().getExtras().getString("name");
        remail = getIntent().getExtras().getString("email");
        rpassword = getIntent().getExtras().getString("password");

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rage = rTextAge.getText().toString().trim();
                rgender = spGender.getSelectedItem().toString().trim();
                rweight = rTextWeight.getText().toString().trim();
                rheight = rTextHeight.getText().toString().trim();

                if(!rage.isEmpty() && !rgender.isEmpty() && !rweight.isEmpty() && !rheight.isEmpty()){
                    if(Integer.parseInt(rage) >= 5 && Integer.parseInt(rage) <= 125){
                        if(Integer.parseInt(rheight) <= 200 ){
                            if (Integer.parseInt(rweight) <= 200){
                                registerData();
                            }else {
                                Toast.makeText(SignData.this, R.string.error_weight , Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(SignData.this, R.string.error_height , Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(SignData.this, R.string.error_age, Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(SignData.this, R.string.signup_m_fields, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void registerData() {
        mAuth.createUserWithEmailAndPassword(remail, rpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", rname);
                    map.put("email", remail);
                    map.put("password", rpassword);
                    map.put("age", rage);
                    map.put("gender", rgender);
                    map.put("height", rheight);
                    map.put("weight", rweight);

                    String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(SignData.this, LoginActivity.class));
                            } else {
                                Toast.makeText(SignData.this, R.string.signup_incorrect, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(SignData.this, R.string.signup_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
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
        startActivity(new Intent(this, SignupActivity.class));
        finish();
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}