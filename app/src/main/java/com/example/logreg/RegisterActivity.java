package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText et_emailCim, et_felhasznalonev, et_jelszo, et_teljesNev;
    Button btn_regisztracio, btn_vissza;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        onClickListeners();
    }
    private void registration(){
        String email = et_emailCim.getText().toString().trim();
        String felhnev = et_felhasznalonev.getText().toString().trim();
        String jelszo = et_jelszo.getText().toString().trim();
        String teljesNev = et_teljesNev.getText().toString().trim();


        if (email.isEmpty()) {
            Toast.makeText(this, "Minden mező megadása kötelező!", Toast.LENGTH_SHORT).show();
            btn_regisztracio.setEnabled(false);
            return;
        }
        if (felhnev.isEmpty()) {
            Toast.makeText(this, "Minden mező megadása kötelező!", Toast.LENGTH_SHORT).show();
            btn_regisztracio.setEnabled(false);
            return;
        }
        if (jelszo.isEmpty()) {
            Toast.makeText(this, "Minden mező megadása kötelező!", Toast.LENGTH_SHORT).show();
            btn_regisztracio.setEnabled(false);
            return;
        }
        if (teljesNev.isEmpty()) {
            Toast.makeText(this, "Minden mező megadása kötelező!", Toast.LENGTH_SHORT).show();
            btn_regisztracio.setEnabled(false);
            return;
        }
        if (!email.contains("@")){
            Toast.makeText(this, "Nem megfelelő E-mail formátum!", Toast.LENGTH_SHORT).show();
            btn_regisztracio.setEnabled(false);
            return;
        }
        boolean successful = database.dataRecording(email, felhnev, jelszo, teljesNev);
        if(successful){
            Toast.makeText(this, "Sikeres regisztráció!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Sikertelen regisztráció!", Toast.LENGTH_SHORT).show();
        }
    }

    private void onClickListeners() {
        btn_regisztracio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration();
            }
        });
        btn_vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(back);
                finish();
            }
        });
    }

    private void init() {
        et_emailCim = findViewById(R.id.et_emailCim);
        et_felhasznalonev = findViewById(R.id.et_felhasznalonev);
        et_jelszo = findViewById(R.id.et_jelszo);
        et_teljesNev = findViewById(R.id.et_teljesNev);
        btn_regisztracio = findViewById(R.id.btn_regisztracio);
        btn_vissza = findViewById(R.id.btn_vissza);
        database = new DBHelper(RegisterActivity.this);
    }
}