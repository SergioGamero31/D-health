package com.example.diabetes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Fragments.PedometerFragment;
import Interfaces.IComDashboard;


public class MainActivity extends AppCompatActivity implements IComDashboard {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    MainFragment mainFragment = new MainFragment();
    ForumFragment forumFragment = new ForumFragment();
    MenuFragment menuFragment = new MenuFragment();

    int newPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        changeStatusBarColor();

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
                transaction.commit();
            }
        }
    }

    /*public void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }*/
    public void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    @Override
    public void regSugar() {
        Toast.makeText(getApplicationContext(),"Registrar Azucar", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doExcercise() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, new PedometerFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        //Toast.makeText(getApplicationContext(),"Hacer ejercicio", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this, ExcerciseActivity.class);
        //startActivity(intent);
    }

    @Override
    public void regMedicine() {
        Toast.makeText(getApplicationContext(),"Registrar medicina", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void regFood() {
        Toast.makeText(getApplicationContext(),"Registrar Comida", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void seeProgress() {
        Toast.makeText(getApplicationContext(),"Ver Progreso", Toast.LENGTH_SHORT).show();
    }
}