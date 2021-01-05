package com.example.famapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureGoGetButton();
        configureGoAddButton();
    }

    private void configureGoGetButton() {
        Button goGet = (Button) findViewById(R.id.goGet);
        goGet.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ShowNotes.class)));
    }

    private void configureGoAddButton() {
        Button goAdd = (Button) findViewById(R.id.goAdd);
        goAdd.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddNote.class)));
    }
}