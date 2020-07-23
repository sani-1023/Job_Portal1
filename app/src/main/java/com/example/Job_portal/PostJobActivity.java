package com.example.Job_portal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.Job_portal.R;
import com.example.Job_portal.model.data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostJobActivity extends AppCompatActivity {

    private FloatingActionButton fabBtn;

    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private DatabaseReference JobPostDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);

        fabBtn=findViewById(R.id.fab_add);

        mAuth=FirebaseAuth.getInstance();
        FirebaseUser mUser=mAuth.getCurrentUser();
        String uId = mUser.getUid();

        JobPostDatabase=FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);

        recyclerView=findViewById(R.id.recycler_job_post_id);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 startActivity(new Intent(getApplicationContext(),InsertJobPostActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<data,MyviewHolder>adapter=new FirebaseRecyclerAdapter<data, MyviewHolder>(
                data.class,
                R.layout.job_post_item,
                MyviewHolder.class,
                JobPostDatabase
        ) {
            @Override
            protected void populateViewHolder(MyviewHolder myviewHolder, data data, int i) {

                myviewHolder.setjobtitle(data.getTitle());
                myviewHolder.setjobdate(data.getDate());
                myviewHolder.setjobdescription(data.getDescription());
                myviewHolder.setskills(data.getSkills());
                myviewHolder.setsalary(data.getSalary());
            }
        };
        recyclerView.setAdapter(adapter);


    }
    public static class MyviewHolder extends ViewHolder{

        View myview;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            myview=itemView;
        }
        public void setjobtitle(String title)
        {
            TextView mtitle=myview.findViewById(R.id.job_title);
            mtitle.setText(title);
        }
        public void setjobdate(String date)
        {
            TextView mdate=myview.findViewById(R.id.jod_date);
            mdate.setText(date);
        }
        public void setjobdescription(String description)
        {
            TextView mdescription=myview.findViewById(R.id.job_description);
            mdescription.setText(description);
        }
        public void setskills(String skills)
        {
            TextView mskills=myview.findViewById(R.id.job_skills);
            mskills.setText(skills);
        }
        public void setsalary(String salary)
        {
            TextView msalary=myview.findViewById(R.id.job_salary);
            msalary.setText(salary);
        }


    }
}
