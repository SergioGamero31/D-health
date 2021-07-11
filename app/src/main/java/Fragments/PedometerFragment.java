package Fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diabetes.R;
import com.example.diabetes.SignupActivity;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;


public class PedometerFragment extends Fragment implements SensorEventListener {

    View view;

    private TextView stepsTaken;
    private CircularProgressBar cProgressBar;
    private Button btnReset;

    private SensorManager sensorManager;
    private Sensor stepCounter;

    private boolean cSensorPresent;
    int stepCount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_podometer, container, false);

        cProgressBar = (CircularProgressBar) view.findViewById(R.id.cProgressBar);
        stepsTaken = (TextView) view.findViewById(R.id.stepsTaken);
        btnReset = (Button) view.findViewById(R.id.btnReset);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            cSensorPresent = true;
        }else {
            Toast.makeText(getActivity(), "No se encontró el sensor", Toast.LENGTH_SHORT).show();
            cSensorPresent = false;
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            sensorManager.unregisterListener(this, stepCounter);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor == stepCounter){
            stepCount =(int) event.values[0];
            stepsTaken.setText(String.valueOf(stepCount));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }




}