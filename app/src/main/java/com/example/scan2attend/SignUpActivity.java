package com.example.scan2attend;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    Button Teacher,Student;
    String email,password,confirm_passowrd,name,rollNo,branch,year,sem,group;
    TextView Name, RollNo;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("email");
        password = bundle.getString("pass");
        confirm_passowrd = bundle.getString("cnfrm_pass");
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();
        Teacher = findViewById(R.id.teacherBtn);
        Student = findViewById(R.id.studentBtn);
        Name = findViewById(R.id.name);
        RollNo = findViewById(R.id.rollNo);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseStudents= FirebaseDatabase.getInstance().getReference("Students");
        progressDialog=new ProgressDialog(this);
        Teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.yearLL).setVisibility(View.GONE);
                findViewById(R.id.courseLL).setVisibility(View.GONE);
                findViewById(R.id.groupLL).setVisibility(View.GONE);
                Student.setBackgroundResource(R.drawable.edittext);
                Teacher.setBackgroundResource(R.drawable.stroked_transparent_white_bg);
            }
        });
        Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.yearLL).setVisibility(View.VISIBLE);
                findViewById(R.id.courseLL).setVisibility(View.VISIBLE);
                findViewById(R.id.groupLL).setVisibility(View.VISIBLE);
                Teacher.setBackgroundResource(R.drawable.edittext);
                Student.setBackgroundResource(R.drawable.stroked_transparent_white_bg);
            }
        });
    }
        public void registerUser(View view){

            //getting email and password from edit texts
            email=email.trim();
            password=password.trim();
            confirm_passowrd = confirm_passowrd.trim();
            name = Name.getText().toString();
            rollNo = RollNo.getText().toString();

            progressDialog.setMessage("Registering, Please Wait...");
            progressDialog.show();

            //creating a new user
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if(task.isSuccessful()){
                                //display some message here
                                Toast.makeText(SignUpActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                                String id = databaseStudents.push().getKey();
                                StudentInformation student = new StudentInformation(id, name, rollNo, branch, year, sem, group);
                                databaseStudents.child(id).setValue(student);
                            }else{
                                //display some message here
                                FirebaseAuthException e = (FirebaseAuthException)task.getException();
                                Toast.makeText(SignUpActivity.this,"Registration Error"+e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });

        }

    public void setYear(View v)
    {
        year = ((Button)v).getText().toString();
        ((Button)v).setBackgroundResource(R.drawable.edittext);
    }
    public void setBranch(View v)
    {
        branch = ((Button)v).getText().toString();
        ((Button)v).setBackgroundResource(R.drawable.edittext);
    }
    public void setSem(View v)
    {
        sem = ((Button)v).getText().toString();
        ((Button)v).setBackgroundResource(R.drawable.edittext);
    }
    public void setGroup(View v)
    {
        group = ((Button)v).getText().toString();
        ((Button)v).setBackgroundResource(R.drawable.edittext);
    }

}
