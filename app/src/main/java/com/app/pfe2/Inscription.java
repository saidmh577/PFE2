package com.app.pfe2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Inscription extends AppCompatActivity {

    EditText fname, lname, email, user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
    }

    public void login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void signup(View view){
        DBHelper dbHelper = new DBHelper(Inscription.this);
        dbHelper.insertUser(
            fname.getText().toString(),
            lname.getText().toString(),
            email.getText().toString(),
            user.getText().toString(),
            pass.getText().toString()
        );
        Toast.makeText(this, "Opération réeussir !", Toast.LENGTH_SHORT).show();
    }
}