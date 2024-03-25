package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Launch extends AppCompatActivity {

    Button create, sign;

    SharedPreferences sharedpreferences;
    public static final String FILE_NAME = "loginPref_File";
    public static final String PASSWORD = "passwordKey";
    public static final String EMAIL = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        create=findViewById(R.id.createA);
        sign=findViewById(R.id.sign_in);
        loaddata();

        //create button brings user to the create account page
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Launch.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        //sign button brings user to the login page
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Launch.this, Login.class);
                startActivity(intent);
            }
        });
    }
    //load data method checks to see if the shared preferences of the user are saved.
    public void loaddata() {
        sharedpreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        if (sharedpreferences.contains(PASSWORD) && (sharedpreferences.contains(EMAIL))) {
            Intent intent = new Intent(Launch.this, Login.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "First time logging in?", Toast.LENGTH_LONG).show();
        }
    }

}