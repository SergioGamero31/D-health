package Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diabetes.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;


public class PedometerFragment extends Fragment implements SensorEventListener {

    private TextView txstepCounter;
    private TextView txtCal;
    private TextView txtTime;
    private TextView txtDist;

    private CircularProgressBar cProgressBar;
    private Button btnReset;

    private SensorManager sensorManager;
    private Sensor mstepCounter;

    private boolean sensorPresent;
    int stepCount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }

        View view = inflater.inflate(R.layout.fragment_podometer, container, false);

        txstepCounter = view.findViewById(R.id.txstepCounter);
        cProgressBar = view.findViewById(R.id.cProgressBar);
        btnReset = view.findViewById(R.id.btnReset);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            mstepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            sensorPresent = true;
        }else {
            Toast.makeText(getActivity(), "No se encontró el sensor", Toast.LENGTH_LONG).show();
            sensorPresent = false;
        }

        resetSteps();
        return view;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor == mstepCounter){
            stepCount = (int) event.values[0];
            txstepCounter.setText(String.valueOf(stepCount));
            cProgressBar.setProgressWithAnimation((float) stepCount);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.registerListener(this, mstepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null)
            sensorManager.unregisterListener(this, mstepCounter);
    }

    public void resetSteps(){
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepCount = 0;
                txstepCounter.setText(String.valueOf(stepCount));
            }
        });
    }
}