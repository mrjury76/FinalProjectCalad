package com.example.project.StartupPkg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.MainPkg.MainActivity;
import com.example.project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    TextView link;
    EditText email, password;
    FirebaseAuth sAuth;
    FirebaseUser sUser;
    Button signin;
    SharedPreferences sharedpreferences;
    public static final String FILE_NAME = "loginPref_File";// mypref_File--- name of the file
    public static final String PASSWORD = "passwordKey";
    public static final String EMAIL = "emailKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        link = findViewById(R.id.link);
        signin = findViewById((R.id.sign_in));
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        sAuth = FirebaseAuth.getInstance();
        sUser = sAuth.getCurrentUser();
        loaddata();

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailT = email.getText().toString();
                String passwordT = password.getText().toString();
                sAuth.signInWithEmailAndPassword(emailT, passwordT);

                Intent intent = new Intent(Login.this, MainActivity.class);
                //intent.putExtra("username", sUser.getDisplayName());
                sharedpreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                //putString---to edit the file in key value pairs
                editor.putString(PASSWORD, passwordT);
                editor.putString(EMAIL, emailT);
                editor.apply();
                intent.putExtra("Username", emailT.substring(0,1).toUpperCase()+emailT.substring(1,emailT.indexOf('@')));
                startActivity(intent);
            }
        });

    }
    public void loaddata() {
        sharedpreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        if (sharedpreferences.contains(PASSWORD) && (sharedpreferences.contains(EMAIL))) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            email.setText(sharedpreferences.getString(EMAIL, ""));
            String emailT = email.getText().toString();
            intent.putExtra("Username", emailT.substring(0,1).toUpperCase()+emailT.substring(1,emailT.indexOf('@')));
            password.setText(sharedpreferences.getString(PASSWORD, ""));
            startActivity(intent);
        } else {
            Toast.makeText(this, "First time logging in?", Toast.LENGTH_LONG).show();
        }
    }

}

