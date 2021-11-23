package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diabetes.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdvancedRutineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdvancedRutineFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private androidx.cardview.widget.CardView flexiones, crunch, sentadillas, plank, lunges;
    View layoutView;
    FragmentManager fragmentManager;

    public AdvancedRutineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdvancedRutineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdvancedRutineFragment newInstance(String param1, String param2) {
        AdvancedRutineFragment fragment = new AdvancedRutineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layoutView = inflater.inflate(R.layout.fragment_advanced_rutine, container, false);
        flexiones = (androidx.cardview.widget.CardView) layoutView.findViewById(R.id.flexiones);
        crunch = (androidx.cardview.widget.CardView) layoutView.findViewById(R.id.crunch);
        sentadillas = (androidx.cardview.widget.CardView) layoutView.findViewById(R.id.sentadillas);
        plank = (androidx.cardview.widget.CardView) layoutView.findViewById(R.id.plank);
        lunges = (androidx.cardview.widget.CardView) layoutView.findViewById(R.id.lunges);
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
}