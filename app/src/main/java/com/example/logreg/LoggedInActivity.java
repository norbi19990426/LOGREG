package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LoggedInActivity extends AppCompatActivity {

    TextView tv_bejelentkezettFel;
    Button btn_kijelentkezes;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        init();
    }

    private void init() {
        tv_bejelentkezettFel = findViewById(R.id.tv_bejelentkezettFel);
        btn_kijelentkezes = findViewById(R.id.btn_kijelentkezes);
        database = new DBHelper(LoggedInActivity.this);
    }
}