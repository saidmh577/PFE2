package com.app.pfe2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
    }

    public void detectionArmature(View view) {
        Intent intent = new Intent(this, ArmatureActivity.class);
        startActivity(intent);
    }

    public void quantite(View view) {
        Intent intent = new Intent(this, QuantiteArmature.class);
        startActivity(intent);
    }

    public void deformation(View view) {
        Intent intent = new Intent(this, VerificationDeformation.class);
        startActivity(intent);
    }
}