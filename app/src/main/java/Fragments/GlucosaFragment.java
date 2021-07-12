package Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.diabetes.R;

import org.w3c.dom.Text;

import java.util.Calendar;

public class GlucosaFragment extends Fragment {

    CalendarView calendarview;
    TextView myDate;
    View view;

    public GlucosaFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_glucosa, container, false);

        calendarview = view.findViewById(R.id.calendar);
        myDate =  view.findViewById(R.id.txDate);

        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1) + "/" + i2 + "/" + i;
                myDate.setText(date);
            }
        });
        return view;
    }

}