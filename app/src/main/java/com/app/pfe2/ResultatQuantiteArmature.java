package com.app.pfe2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultatQuantiteArmature extends AppCompatActivity {

    TextView as, asp, msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_quantite_armature);

        as = findViewById(R.id.as);
        asp = findViewById(R.id.asp);
        msg = findViewById(R.id.msg);

        Intent intent = getIntent();

        String s = new DecimalFormat("##.##").format(Double.parseDouble(intent.getStringExtra("as")));
        String s2 = new DecimalFormat("##.##").format(Double.parseDouble(intent.getStringExtra("asp")));

        as.setText("AS = " + s + " cm²");
        asp.setText("AS' = " + s2 + " cm²");
        msg.setText(intent.getStringExtra("msg"));
    }
}