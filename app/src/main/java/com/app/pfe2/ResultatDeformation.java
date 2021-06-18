package com.app.pfe2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultatDeformation extends AppCompatActivity {

    TextView delta, msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_deformation);

        delta = findViewById(R.id.delta);
        msg = findViewById(R.id.msg2);

        delta.setText("Delta (δ) = 0");
        msg.setText("Msg : pas de flèche");
    }
}