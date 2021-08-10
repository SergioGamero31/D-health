package Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import android.widget.EditText;
import android.widget.TextView;

import com.example.diabetes.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GlucosaFragment extends Fragment {

    CalendarView calendarview;
    TextView myDate;
    EditText editGlucosa;
    View view;
    Button btnConfirm;

    DatabaseReference mDatabase;

    private String gGlucosa;

    public GlucosaFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_glucosa, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        calendarview = view.findViewById(R.id.calendar);
        myDate =  view.findViewById(R.id.txDate);
        btnConfirm = view.findViewById(R.id.btnConfirm);
        editGlucosa = view.findViewById(R.id.editTextGlucosa);

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
                if (!gGlucosa.isEmpty()){
                    registerGlucosa();
                }
            }
        });

        return view;
    }
    private void registerGlucosa(){

    }

}