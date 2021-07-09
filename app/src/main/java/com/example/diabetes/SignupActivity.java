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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private EditText rTextName;
    private EditText rTextEmail;
    private EditText rTextPassword;
    private EditText rTextRpassword;
    private Button  registerButton;

    private String rname = "";
    private String remail = "";
    private String rpassword = "";
    private String rrpassword = "";

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
                            Toast.makeText(SignupActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(SignupActivity.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(SignupActivity.this, "Debe completar los campos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void registerUser(){
        mAuth.createUserWithEmailAndPassword(remail, rpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", rname);
                    map.put("email", remail);
                    map.put("password", rpassword);

                    String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                finish();
                            }else{
                                Toast.makeText(SignupActivity.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(SignupActivity.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
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

        startActivity(new Intent(this, LoginActivity.class));
        finish();
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}