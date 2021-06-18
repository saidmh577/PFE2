package com.app.pfe2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VerificationDeformation extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;

    Sensor gyro;
    Sensor accel;

    TextView ax, ay, az;
    TextView gx, gy, gz;

    Button button;

    boolean activated = false;

    ArrayList<Gyro> listGyro;
    ArrayList<Acce> listAcce;
    ArrayList<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_deformation);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        ax = findViewById(R.id.ax);
        ay = findViewById(R.id.ay);
        az = findViewById(R.id.az);

        gx = findViewById(R.id.gx);
        gy = findViewById(R.id.gy);
        gz = findViewById(R.id.gz);

        button = findViewById(R.id.button7);

        listGyro = new ArrayList<>();
        listAcce = new ArrayList<>();
        list = new ArrayList<>();

    }

    public void collectData(View view) {
        if(activated){
            DBHelper dbHelper = new DBHelper(VerificationDeformation.this);

            int min = Math.min(listAcce.size(), listGyro.size());
            for(int x = 0; x < min; x++){
                dbHelper.insertRow(
                        listAcce.get(x).acc_x, listAcce.get(x).acc_y, listAcce.get(x).acc_z,
                        listGyro.get(x).gyro_x, listGyro.get(x).gyro_y, listGyro.get(x).gyro_z,
                        ""
                );
            }

            //dbHelper.getAllRecords();

            activated = false;
            button.setText("Démarrer la Collection");
        }else{
            activated = true;
            button.setText("Arreter");
        }
    }

    public void showGraph(View view) {
        Intent intent = new Intent(this, ResultatDeformation.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float values[] = sensorEvent.values;
        if (sensorEvent.sensor.getName().equals(gyro.getName())) {
            gx.setText("x = " + values[0]);
            gy.setText("y = " + values[1]);
            gz.setText("z = " + values[2]);
            listGyro.add(new Gyro(values[0], values[1], values[2]));
        }

        if (sensorEvent.sensor.getName().equals(accel.getName())) {
            ax.setText("x = " + values[0]);
            ay.setText("y = " + values[1]);
            az.setText("z = " + values[2]);

            listAcce.add(new Acce(values[0], values[1], values[2]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}