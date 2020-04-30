package com.example.formativeone;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class ActivityShow extends AppCompatActivity {

    Cohort2Db db;
    List<Student> students;
    Button delBtn;
    Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new Cohort2Db(this);

//        this.fetchStudents();
        students = db.allStudents();
        updateAdapter(this.updateList());

    }

    public void updateAdapter(ListAdapter d){
        // display like string instances
        ListView list = findViewById(R.id.list);
        list.setAdapter(d);
    }

    public void updateItem(View v) {
        RelativeLayout r = (RelativeLayout) v.getParent().getParent();
        TextView t =  (TextView)r.getChildAt(0);
        String txt = t.getText().toString();
        String id = txt.trim().split(" ")[0].trim();
        editEntries(id);
    }

    public void deleteItem(View v){
        // get id
        // delete id from db
        RelativeLayout r = (RelativeLayout) v.getParent().getParent();
        TextView t =  (TextView)r.getChildAt(0);
        String txt = t.getText().toString();
        String id = txt.trim().split(" ")[0].trim();
        t.setText(id);
        int status = db.deleteOne(id);
        t.setText(String.valueOf(status));
//         Fetch all students
        students = db.allStudents();
        ListView d = (ListView)v.getParent().getParent().getParent(); // Parent
        d.setAdapter(updateList());
    }

    public ListAdapter updateList(){
        if (students != null) {
            String[] itemsNames = new String[students.size()];
            for (int i = 0; i < students.size(); i++) {
                itemsNames[i] = students.get(i).toString();
            }
            delBtn =  findViewById(R.id.del_btn);
            editBtn =  findViewById(R.id.edit_btn);
            ListAdapter d = new ArrayAdapter<String>(this,
                    R.layout.list_item, android.R.id.text1, itemsNames);
            return d;
        }
        return null;
    }


    public void editEntries(String id){
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("ST_ID", id);
        startActivity(intent);
    }
}
