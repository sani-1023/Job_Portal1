package com.example.Job_portal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Job_portal.model.data;
import com.example.Job_portal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;


public class InsertJobPostActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private EditText job_title;
    private EditText job_salary;
    private EditText job_skill;
    private EditText job_description;
    private Button btn_postjob;

    //firebase
    private FirebaseAuth mauth;
    private DatabaseReference mJobPost,mpublicjobPost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_job_post);

        toolbar = findViewById(R.id.insert_job_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("post job");

        mauth=FirebaseAuth.getInstance();
        FirebaseUser muser=mauth.getCurrentUser();
        String uId=muser.getUid();

        mJobPost= FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);
        mpublicjobPost=FirebaseDatabase.getInstance().getReference().child("Public Database");

        Insertjob();

    }

    private void Insertjob()
    {
        job_title = findViewById(R.id.job_title);
        job_description = findViewById(R.id.job_description);
        job_skill = findViewById(R.id.job_skill);
        job_salary = findViewById(R.id.job_salary);
        btn_postjob = findViewById(R.id.btn_job_post);

        btn_postjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = job_title.getText().toString().trim();
                String salary = job_salary.getText().toString().trim();
                String skill = job_skill.getText().toString().trim();
                String descripton = job_description.getText().toString().trim();

                if(TextUtils.isEmpty(title)){
                    job_title.setError("Field Required.....");
                    return ;
                }
                if(TextUtils.isEmpty(salary)){
                    job_salary.setError("Field Required.....");
                    return ;
                }
                if(TextUtils.isEmpty(skill)){
                    job_skill.setError("Field Required.....");
                    return ;
                }
                if(TextUtils.isEmpty(descripton)){
                    job_description.setError("Field Required.....");
                    return ;
                }

                String id=mJobPost.push().getKey();
                String date= DateFormat.getDateInstance().format(new Date());
                data data1=new data(title ,descripton,date,salary,skill,id);

                mJobPost.child(id).setValue(data1);
                mpublicjobPost.child(id).setValue(data1);

                Toast.makeText(getApplicationContext(), "Successful....", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),InsertJobPostActivity.class));


            }
        });


    }



}
