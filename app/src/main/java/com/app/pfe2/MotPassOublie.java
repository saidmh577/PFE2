package com.app.pfe2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MotPassOublie extends AppCompatActivity {

    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mot_pass_oublie);

        email = findViewById(R.id.forgetEmail);
    }

    public void forget(View view){

        String mail = email.getText().toString();
        DBHelper dbHelper = new DBHelper(MotPassOublie.this);
        String pass = dbHelper.getUserPass(mail);

        String message = "Bienvenu, votre inofs : email -> " + mail + ", mot de passe -> " + pass;
        String subject = "Votre compte de l'app";

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(MotPassOublie.this,mail,subject,message);

        javaMailAPI.execute();

    }
}