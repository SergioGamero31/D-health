package Fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.diabetes.R;

public class IntermediateRutineFragment extends Fragment {

    private androidx.cardview.widget.CardView flexiones, crunch, sentadillas, plank, lunges;
    View layoutView;
    FragmentManager fragmentManager;

    public static IntermediateRutineFragment newInstance(String param1, String param2) {
        IntermediateRutineFragment fragment = new IntermediateRutineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layoutView = inflater.inflate(R.layout.fragment_intermediate_rutine, container, false);
        flexiones = (androidx.cardview.widget.CardView) layoutView.findViewById(R.id.flexiones);
        crunch = (androidx.cardview.widget.CardView) layoutView.findViewById(R.id.crunch);
        sentadillas = (androidx.cardview.widget.CardView) layoutView.findViewById(R.id.sentadillas);
        plank = (androidx.cardview.widget.CardView) layoutView.findViewById(R.id.plank);
        lunges = (androidx.cardview.widget.CardView) layoutView.findViewById(R.id.lunges);

        changeStatusBarColor();

        flexiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","1"); // set parameteres
                ChooseBeginnerRutineFragment nextFragment = new ChooseBeginnerRutineFragment();
                nextFragment.setArguments(bundle);
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, nextFragment).addToBackStack(null).commit();
            }
        });
        crunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","2"); // set parameteres
                ChooseBeginnerRutineFragment nextFragment = new ChooseBeginnerRutineFragment();
                nextFragment.setArguments(bundle);
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, nextFragment).addToBackStack(null).commit();
            }
        });
        sentadillas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","3"); // set parameteres
                ChooseBeginnerRutineFragment nextFragment = new ChooseBeginnerRutineFragment();
                nextFragment.setArguments(bundle);
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, nextFragment).addToBackStack(null).commit();
            }
        });
        plank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","4"); // set parameteres
                ChooseBeginnerRutineFragment nextFragment = new ChooseBeginnerRutineFragment();
                nextFragment.setArguments(bundle);
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, nextFragment).addToBackStack(null).commit();
            }
        });
        lunges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("key","5"); // set parameteres
                ChooseBeginnerRutineFragment nextFragment = new ChooseBeginnerRutineFragment();
                nextFragment.setArguments(bundle);
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container, nextFragment).addToBackStack(null).commit();
            }
        });
        return layoutView;
    }
    public void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}