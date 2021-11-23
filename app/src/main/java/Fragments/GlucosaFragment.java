package Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diabetes.R;
import com.example.diabetes.SignData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GlucosaFragment extends Fragment {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    CalendarView calendarview;
    TextView myDate;
    EditText editGlucosa;
    Spinner spUnit;
    Button btnConfirm, btnBack;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    private String gGlucosa, gUnit, gDate, gTime;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_glucosa, container, false);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        calendarview = view.findViewById(R.id.calendar);
        myDate =  view.findViewById(R.id.txDate);
        btnConfirm = view.findViewById(R.id.btnConfirm);
        btnBack = view.findViewById(R.id.btngBack);
        editGlucosa = view.findViewById(R.id.editTextGlucosa);
        spUnit = view.findViewById(R.id.spUnit);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        gTime = simpleDateFormat.format(new Date());

        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1) + "/" + i2 + "/" + i;
                myDate.setText(date);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gGlucosa = editGlucosa.getText().toString().trim();
                gUnit = spUnit.getSelectedItem().toString().trim();
                gDate = myDate.getText().toString().trim();

                if (!gGlucosa.isEmpty() && !gUnit.isEmpty() ){
                    registerGlucosa();
                }else {
                    Toast.makeText(getActivity(), R.string.signup_m_fields , Toast.LENGTH_LONG).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }
    private void registerGlucosa(){

        Map<String, Object> map = new HashMap<>();
        map.put("quantity", gGlucosa);
        map.put("unit", gUnit);
        map.put("date", gDate);
        map.put("time", gTime);

        String id = mAuth.getCurrentUser().getUid();

        mDatabase.child("Users").child(id).child("Glucose").push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity(), R.string.glucose_message, Toast.LENGTH_SHORT).show();
                    editGlucosa.setText("");
                }else {
                    Toast.makeText(getActivity(), R.string.glucose_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}