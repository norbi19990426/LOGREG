package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et_felhasznalonev, et_jelszo;
    Button btn_regisztracio, btn_bejelentkezes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        onClickListeners();
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
                Intent loggedTo = new Intent(MainActivity.this, LoggedInActivity.class);
                startActivity(loggedTo);
                finish();
            }
        });
    }



    private void init(){
        et_felhasznalonev = findViewById(R.id.et_felhasznalonev);
        et_jelszo = findViewById(R.id.et_jelszo);
        btn_bejelentkezes = findViewById(R.id.btn_bejelentkezes);
        btn_regisztracio = findViewById(R.id.btn_regisztracio);
    }
}