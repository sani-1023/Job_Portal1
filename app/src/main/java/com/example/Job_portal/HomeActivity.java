package com.example.Job_portal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.Job_portal.R;

public class HomeActivity extends AppCompatActivity {


    private Button btnalljob;
    private Button btnpostob;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnalljob = findViewById(R.id.btn_alljob) ;
        btnpostob = findViewById(R.id.btn_postjob);

        toolbar = findViewById(R.id.toolbar_home);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Job Portal");



        btnalljob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(getApplicationContext(),AllJobActivity.class));
            }
        });

        btnpostob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),PostJobActivity.class));

            }
        });
    }
}
