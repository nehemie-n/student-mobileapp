package com.example.formativeone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;

public class Cohort2Db extends SQLiteOpenHelper {

    public static final String DBNAME = "cohort2.db";
    public static final int VERSION= 1;

    public static final String TABLENAME = "students";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";

    private static final String[] COLUMNS = { ID, NAME, PASSWORD, EMAIL};

    public Cohort2Db(@Nullable Context context){
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLENAME +"( id integer primary key autoincrement, name String, password String, email String)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ TABLENAME);
        onCreate(db);
    }

    public long addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values  = new ContentValues();

        values.put(NAME, student.getName());
        values.put(PASSWORD, student.getPassword());
        values.put(EMAIL, student.getEmail());

        // insert
        long id  = db.insert(TABLENAME,null, values );

        return id;
    }

    public int deleteOne(String d) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        int status  = db.delete(TABLENAME, "id = ?", new String[] { d });
        db.close();
        return status;
    }

    public Student getStudent(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLENAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[] { id }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Student student = new Student();
        student.setId(cursor.getString(0));
        student.setName(cursor.getString(1));
        student.setPassword(cursor.getString(2));
        student.setEmail(cursor.getString(3));

        return student;
    }

    public List<Student> allStudentsTest() {
        List<Student> students = new LinkedList<Student>();
        Student student = new Student();
        student.setName("Nesh");
        student.setPassword("Pass Nesh");
        student.setEmail("Nesh@gmail");
        students.add(student);
        return students;
    }

    public List<Student> allStudents() {

        List<Student> students = new LinkedList<Student>();
        //
        String query = "SELECT  * FROM " + TABLENAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = null;

        if (cursor.moveToFirst()) {
            do {
                student = new Student();
                student.setId(cursor.getString(0));
                student.setName(cursor.getString(1));
                student.setPassword(cursor.getString(2));
                student.setEmail(cursor.getString(3));
                students.add(student);
            } while (cursor.moveToNext());
        }

        return students;
    }

    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, student.getName());
        values.put(PASSWORD, student.getPassword());
        values.put(EMAIL, student.getEmail());

        int i = db.update(TABLENAME, // table
                values, // column/value
                "id = ?", // selections
                new String[] { String.valueOf(student.getId()) });

        db.close();

        return i;
    }
}
