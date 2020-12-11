package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_felhasznalonev, et_jelszo;
    Button btn_regisztracio, btn_bejelentkezes;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        onClickListeners();
    }
    private void login(){
        String felhnev = et_felhasznalonev.getText().toString().trim();
        String jelszo = et_jelszo.getText().toString().trim();

        if (felhnev.isEmpty()) {
            Toast.makeText(this, "E-mail cím megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (jelszo.isEmpty()) {
            Toast.makeText(this, "Felhasználónév megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!database.loginCheck(felhnev, jelszo)){
            Toast.makeText(this, "Sikertelen bejelentkezés!", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            Intent loggedTo = new Intent(MainActivity.this, LoggedInActivity.class);
            startActivity(loggedTo);
            finish();
        }

    }
    private void onClickListeners() {
        btn_regisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerTo = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerTo);
                finish();
            }
        });
        btn_bejelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }



    private void init(){
        et_felhasznalonev = findViewById(R.id.et_felhasznalonev);
        et_jelszo = findViewById(R.id.et_jelszo);
        btn_bejelentkezes = findViewById(R.id.btn_bejelentkezes);
        btn_regisztracio = findViewById(R.id.btn_regisztracio);
        database = new DBHelper(MainActivity.this);
    }
}