package com.app.pfe2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.user1);
        pass = findViewById(R.id.pass1);


    }

    public void login(View view) {
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        boolean check = dbHelper.checkUser(
                user.getText().toString(),
                pass.getText().toString()
        );
        if(check){
            Intent intent = new Intent(this, MainHome.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Informations incorrecte !", Toast.LENGTH_SHORT).show();
        }
    }

    public void signup(View view) {
        Intent intent = new Intent(this, Inscription.class);
        startActivity(intent);
    }

    public void forget(View view) {
        Intent intent = new Intent(this, MotPassOublie.class);
        startActivity(intent);
    }
}