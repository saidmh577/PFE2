package com.app.pfe2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.anastr.speedviewlib.TubeSpeedometer;

public class ArmatureActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor magnetometer;

    TubeSpeedometer mags;
    Button button;
    TextView x, y, z;

    boolean activated = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armature);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if(magnetometer == null){
            Toast.makeText(this, "Votre appareil ne support pas ce capteur !", Toast.LENGTH_SHORT).show();
        }

        mags = findViewById(R.id.magsView);
        button = findViewById(R.id.button5);

        x = findViewById(R.id.x);
        y = findViewById(R.id.y);
        z = findViewById(R.id.z);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float magX = event.values[0];
        float magY = event.values[1];
        float magZ = event.values[2];

        double magnitude = Math.sqrt((magX * magX) + (magY * magY) + (magZ * magZ));

        mags.speedTo((float) magnitude);
        x.setText("X = " + magX);
        y.setText("Y = " + magY);
        z.setText("Z = " + magZ);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void stop(View view) {
        if(activated){
            sensorManager.unregisterListener(this);
            activated = false;
            button.setText("Démarrer");
        }else{
            sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
            activated = true;
            button.setText("Arreter");
        }
    }
}