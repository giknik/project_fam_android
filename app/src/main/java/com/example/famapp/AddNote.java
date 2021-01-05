package com.example.famapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;


public class AddNote extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        configureBackButton1();

    }

    private void configureBackButton1() {
        Button goBack1 = (Button) findViewById(R.id.back1);
        goBack1.setOnClickListener(v -> finish());
    }
}
