package com.example.formativeone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.EventListener;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    String id;
    Cohort2Db db;
    Student student;

    Button saveBtn;

    EditText studentId; EditText studentName; EditText studentPass; EditText studentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        id = getIntent().getStringExtra("ST_ID");
        db = new Cohort2Db(this);
//        this.fetchStudents();
        student = db.getStudent(id);

        studentPass = findViewById(R.id.student_pass);
        studentPass.setText(student.getPassword());

        studentEmail = findViewById(R.id.student_email);
        studentEmail.setText(student.getEmail());

        studentName = findViewById(R.id.student_name);
        studentName.setText(student.getName());

        saveBtn = findViewById(R.id.savedits_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEdits();
            }
        });
    }

    public void saveEdits(){
        Boolean d = validateForm();
        if(d){
//                     If valid save and go to view page
            Student student = new Student();
            // set fields
            student.setEmail(studentEmail.getText().toString());
            student.setName(studentName.getText().toString());
            student.setPassword(studentPass.getText().toString());
            student.setId(id);
            db.updateStudent(student);
            this.readEntries();
        }
    }

    public boolean validateForm(){
        return(validateEditText(studentName, "Enter Name")
                & validateEditText(studentEmail, "Enter valid email")
                & validateEditText(studentPass, "Enter password "));
    }

    public boolean validateEditText(EditText view, String message){
        if (view.getText().toString().isEmpty()){
            view.setError(message);
            return false;
        }
        return true;
    }

    public void readEntries(){
        Intent intent = new Intent(this, ActivityShow.class);
        startActivity(intent);
    }
}
