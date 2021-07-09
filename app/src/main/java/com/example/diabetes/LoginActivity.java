 package com.example.diabetes;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Array;
import java.util.Arrays;

 public class LoginActivity extends AppCompatActivity {

    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnLogin;

    private String email = "";
    private String password = "";
    private FirebaseAuth mAuth;

    private Button btnFacebook;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        changeStatusBarColor();

        mAuth = FirebaseAuth.getInstance();

        TextEmail = (EditText) findViewById(R.id.editTextEmail);
        TextPassword = (EditText) findViewById(R.id.editTextPassword);
        btnLogin = (Button) findViewById(R.id.cirLoginButton);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = TextEmail.getText().toString().trim();
                password = TextPassword.getText().toString().trim();
                if(!email.isEmpty() && !password.isEmpty()){
                    loginUser();
                }else{
                    Toast.makeText(LoginActivity.this, "Debe completar los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        callbackManager = CallbackManager.Factory.create();
        btnFacebook = (Button) findViewById(R.id.btnFacebook);
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScreen();
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "user_friends"));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void loginUser(){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, "No se pudo iniciar sesión, compruebe los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goMainScreen(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onLoginClick(View view){
        startActivity(new Intent(this, SignupActivity.class));
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }

     public void changeStatusBarColor(){
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

             Window window = getWindow();
             window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
             window.setStatusBarColor(getResources().getColor(R.color.login_bk_color));
         }
     }
}