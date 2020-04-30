package com.example.formativeone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Cohort2Db db;


    private Button buttonSave;

    EditText studentId; EditText studentName; EditText studentPass; EditText studentEmail;

    private FragmentEntry fragmentEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Cohort2Db(this);


        buttonSave = findViewById(R.id.save_btn);

        studentId = findViewById(R.id.student_id);
        studentPass = findViewById(R.id.student_pass);
        studentEmail = findViewById(R.id.student_email);
        studentName = findViewById(R.id.student_name);
    }

    public void save(View v){
        Boolean d = validateForm();
        if(d){
//                     If valid save and go to view page
            Student student = new Student();
            // set fields
            student.setEmail(studentEmail.getText().toString());
            student.setName(studentName.getText().toString());
            student.setPassword(studentPass.getText().toString());

            long s = db.addStudent(student);

            studentId.setText(String.valueOf(s));

            this.readEntries(v);
        }
    }



    public boolean validateForm(){
        return(validateEditText(studentId, "Enter id") & validateEditText(studentName, "Enter Name")
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

    public void readEntries(View v){
        Intent intent = new Intent(this, ActivityShow.class);
        startActivity(intent);
    }
}
