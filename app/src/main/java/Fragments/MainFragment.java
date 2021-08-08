package Fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.diabetes.R;

import Interfaces.IComDashboard;

public class MainFragment extends Fragment {

    View view;
    Activity main;
    CardView cardExcercise, cardSugar, cardMedicine, cardFood, cardStatistics, cardConsult;
    IComDashboard iComDashboard;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        cardSugar = view.findViewById(R.id.cardSugar);
        cardExcercise = view.findViewById(R.id.cardExcercise);
        cardMedicine = view.findViewById(R.id.cardMedicine);
        cardFood = view.findViewById(R.id.cardFood);
        cardConsult = view.findViewById(R.id.cardConsult);

        cardStatistics = view.findViewById(R.id.cardStatistics);

        dashboardEvents();

        changeStatusBarColor();

        return view;

    }

    private void dashboardEvents(){
        cardSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iComDashboard.regSugar();
            }
        });

        cardExcercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iComDashboard.doExcercise();
            }
        });

        cardMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iComDashboard.regMedicine();
            }
        });

        cardFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iComDashboard.regFood();
            }
        });

        cardStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iComDashboard.seeProgress();
            }
        });

        cardConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { iComDashboard.doConsult(); }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            main = (Activity) context;
            iComDashboard = (IComDashboard) main;
        }
    }

    public void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.themeColor));
        }
    }
}