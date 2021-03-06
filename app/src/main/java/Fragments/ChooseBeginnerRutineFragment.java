package Fragments;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.diabetes.R;

import java.util.Date;
import java.util.Locale;

public class ChooseBeginnerRutineFragment extends Fragment {

    View layoutView;
    //private static final long START_TIME_IN_MILLIS = 120000;
    private TextView mTextViewCountDown, mTextViewEndRutine;
    private Button mButtonStartPause;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    //private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mTimeLeftInMillis, mCountDownInterval;
    private ProgressBar mProgressBar;
    private String fecha;
    FragmentManager fragmentManager;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //layoutView = inflater.inflate(R.layout.fragment_rutine, container, false);
        String value = this.getArguments().getString("key");
        int data = Integer.parseInt(value);
        switch (data) {
            case 1:
                mTimeLeftInMillis = 120000;
                mCountDownInterval = 1200;
                layoutView = inflater.inflate(R.layout.fragment_flexiones, container, false);
                break;
            case 2:
                mTimeLeftInMillis = 180000;
                mCountDownInterval = 1800;
                layoutView = inflater.inflate(R.layout.fragment_crunch, container, false);
                break;
            case 3:
                mTimeLeftInMillis = 150000;
                mCountDownInterval = 1500;
                layoutView = inflater.inflate(R.layout.fragment_sentadillas, container, false);
                break;
            case 4:
                mTimeLeftInMillis = 90000;
                mCountDownInterval = 900;
                layoutView = inflater.inflate(R.layout.fragment_plank, container, false);
                break;
            case 5:
                mTimeLeftInMillis = 60000;
                mCountDownInterval = 600;
                layoutView = inflater.inflate(R.layout.fragment_lunges, container, false);
                break;
        }

        mTextViewCountDown = layoutView.findViewById(R.id.TextViewCountDown);
        mButtonStartPause  = layoutView.findViewById(R.id.ButtonStartPause);
        mProgressBar       = layoutView.findViewById(R.id.circularProgressIndicator);
        mTextViewEndRutine = layoutView.findViewById(R.id.TextViewEndRutine);

        mTextViewEndRutine.setVisibility(View.INVISIBLE);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });
        updateCountDownText();
        return layoutView;
    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                int current = (int) (mTimeLeftInMillis / mCountDownInterval);
                if (current > mProgressBar.getMax()) current = 0;
                mProgressBar.setProgress(current);
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Iniciar");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mTextViewEndRutine.setText("??Buen trabajo!");
                mTextViewEndRutine.setVisibility(View.VISIBLE);
                currentDate();
            }
        }.start();
        mTimerRunning = true;
        mButtonStartPause.setText("Pausar");
       //mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void stopTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Iniciar");
        //mButtonReset.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void currentDate(){
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
    }

}