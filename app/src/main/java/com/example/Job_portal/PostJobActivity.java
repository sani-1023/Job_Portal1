package com.example.Job_portal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Job_portal.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PostJobActivity extends AppCompatActivity {

    private FloatingActionButton fabBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);

        fabBtn=findViewById(R.id.fab_add);

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 startActivity(new Intent(getApplicationContext(),InsertJobPostActivity.class));
            }
        });

    }
}
