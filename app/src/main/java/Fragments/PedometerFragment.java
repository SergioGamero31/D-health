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
import android.widget.TextView;
import android.widget.Toast;

import com.example.diabetes.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;


public class PedometerFragment extends Fragment implements SensorEventListener {

    View view;

    private boolean running = false;
    private float totalSteps = 0f;
    private float preTotalSteps = 0f;

    private TextView stepsTaken;
    private CircularProgressBar cProgressBar;

    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_podometer, container, false);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        stepsTaken = (TextView) view.findViewById(R.id.stepsTaken);
        cProgressBar = (CircularProgressBar) view.findViewById(R.id.cProgressBar);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        running = true;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if(sensor ==null){
            Toast.makeText(getActivity(), "No se detectó el sensor",Toast.LENGTH_SHORT).show();
        }
        else {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running){
            totalSteps = event.values[0];
            int currentSteps = (int) totalSteps - (int) preTotalSteps;
            stepsTaken.setText(currentSteps);

            cProgressBar.setProgressWithAnimation((float)currentSteps);
        }
    }
    public void resetSteps(){

    }

}