package com.example.assessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class Dashboard extends AppCompatActivity {

    EditText title, description;
    MaterialButton post;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerview);
        title = findViewById(R.id.title);
        description = findViewById(R.id.desc);
        post = findViewById(R.id.post);

    }
}