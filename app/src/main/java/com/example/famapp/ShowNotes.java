package com.example.famapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;


public class ShowNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);

        configureBackButton2();
    }

    private void configureBackButton2() {
        Button goBack2 = (Button) findViewById(R.id.back2);
        goBack2.setOnClickListener(v -> finish());
    }
}