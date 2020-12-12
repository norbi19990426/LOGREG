package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoggedInActivity extends AppCompatActivity {

    TextView tv_bejelentkezettFel;
    Button btn_kijelentkezes;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        init();
        onClickListeners();
        user();
    }

    private void user() {

        Intent intent = getIntent();
        String sessionId = intent.getStringExtra("SESSION_ID");
        Cursor data = database.idRequest(sessionId);
        StringBuilder stringBuffer = new StringBuilder();
        while (data.moveToNext()){
            stringBuffer.append("Üdvözöllek: ").append(data.getString(0));
        }
        tv_bejelentkezettFel.setText(stringBuffer.toString());
    }

    private void onClickListeners() {
        btn_kijelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainTo = new Intent(LoggedInActivity.this, MainActivity.class);
                startActivity(mainTo);
                finish();
            }
        });
    }

    private void init() {
        tv_bejelentkezettFel = findViewById(R.id.tv_bejelentkezettFel);
        btn_kijelentkezes = findViewById(R.id.btn_kijelentkezes);
        database = new DBHelper(LoggedInActivity.this);
    }
}