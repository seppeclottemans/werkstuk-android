package com.example.werkstuk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.werkstuk.databinding.ActivityMainBinding;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";

    // watched this tutorial for the basic uses of the accelerometer (logging the results on change): https://www.youtube.com/watch?v=Rda_5s4rObQ (19/05/2020)
    private SensorManager sensorManager;
    Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set live data bindings
        PhoneDropsViewModel phoneDropViewModel = new ViewModelProvider(this).get(PhoneDropsViewModel.class);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewmodel(phoneDropViewModel);
        setContentView(binding.getRoot());

        // set accelerometer as sensor listener.
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        // fall detection code from: https://stackoverflow.com/questions/44518729/android-fall-detection (19/05/2020)
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {


            double loX = sensorEvent.values[0];
            double loY = sensorEvent.values[1];
            double loZ = sensorEvent.values[2];

            double loAccelerationReader = Math.sqrt(Math.pow(loX, 2)
                    + Math.pow(loY, 2)
                    + Math.pow(loZ, 2));

            DecimalFormat precision = new DecimalFormat("0.00");
            double ldAccRound = Double.parseDouble(precision.format(loAccelerationReader));

            // for testing in the emulator: loAccelerationReader <= 6.0
            // for fall accuracy: ldAccRound > 0.3d && ldAccRound < 0.5d
            if (loAccelerationReader <= 6.0) {
                Log.d(TAG, "fall detected!");
                Toast.makeText(this, "Fall detected", Toast.LENGTH_LONG).show();
            }
        }
    }
}