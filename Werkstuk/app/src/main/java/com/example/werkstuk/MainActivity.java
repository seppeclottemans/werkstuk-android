package com.example.werkstuk;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private MainViewModel mainViewModel;
    // watched this tutorial for the basic uses of the accelerometer (logging the results on change): https://www.youtube.com/watch?v=Rda_5s4rObQ (19/05/2020)
    private SensorManager sensorManager;
    Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // navigation configuration code
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_charts, R.id.navigation_about_me)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // set accelerometer as sensor listener.
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null)
        {
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }else {
            startActivity(new Intent(MainActivity.this, PopUp.class));
        }
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

            // DecimalFormat precision = new DecimalFormat("0.00");
            // double ldAccRound = Double.parseDouble(precision.format(loAccelerationReader));

            // for testing in the emulator: loAccelerationReader <= 6.0
            // for fall accuracy: ldAccRound > 0.3d && ldAccRound < 0.5d
            if (loAccelerationReader <= 6.0) {
                // a fall has been detected
                Toast.makeText(this, "Fall detected", Toast.LENGTH_LONG).show();

                // new instance of phone drop
                Date currentTime = Calendar.getInstance().getTime();
                PhoneDrop phoneDrop = new PhoneDrop(currentTime);
                mainViewModel.insert(phoneDrop);
            }
        }
    }

}
