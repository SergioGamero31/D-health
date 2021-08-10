package com.example.diabetes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Fragments.ForumFragment;
import Fragments.GlucosaFragment;
import Fragments.MainFragment;
import Fragments.MenuFragment;
import Fragments.NutritionFragment;
import Fragments.PedometerFragment;
import Interfaces.IComDashboard;


public class MainActivity extends AppCompatActivity implements IComDashboard {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    MainFragment mainFragment = new MainFragment();
    ForumFragment forumFragment = new ForumFragment();
    MenuFragment menuFragment = new MenuFragment();

    private FirebaseAuth mAuth;

    int newPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(mainFragment,0);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.mainFragment:
                    loadFragment(mainFragment,0);
                    return true;
                case R.id.forumFragment:
                    loadFragment(forumFragment,1);
                    return true;
                case R.id.menuFragment:
                    loadFragment(menuFragment,2);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment, int newPosition){
        if(fragment !=null) {
            if (newPosition < 1) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_left_right,R.anim.exit_left_right);
                transaction.replace(R.id.frame_container, fragment);
                transaction.disallowAddToBackStack();
                transaction.commit();
            }
            if (newPosition == 1){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_right_left,R.anim.exit_right_left);
                transaction.replace(R.id.frame_container, fragment);
                transaction.commit();
            }
            if (newPosition > 1){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_right_left,R.anim.exit_left_right);
                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
    }


    @Override
    public void regSugar() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, new GlucosaFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void doExcercise() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, new PedometerFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        //Intent intent = new Intent(this, ExcerciseActivity.class);
        //startActivity(intent);
    }

    @Override
    public void regMedicine() {
        Toast.makeText(getApplicationContext(),"Registrar medicina", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void regFood() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, new NutritionFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void doConsult() {
        Toast.makeText(getApplicationContext(),"Realizar Consulta", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void seeProgress() {
        Toast.makeText(getApplicationContext(),"Ver Progreso", Toast.LENGTH_SHORT).show();
    }

    private void goLoginScreen(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}