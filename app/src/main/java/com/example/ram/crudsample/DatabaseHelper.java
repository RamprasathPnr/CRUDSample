package com.example.ram.crudsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "StudentDatabase";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE students " +
                "(Id INTEGER, " +
                "Name TEXT, " +
                "Email TEXT ) ";

        Log.e("DatabaseHelper", "Query " + sql);

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String Id, String name, String email) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id",Id);
        contentValues.put("Name",name);
        contentValues.put("Email",email);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("students", null, contentValues);
        db.close();
    }

    public void update(String Id, String name, String email) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id",Id);
        contentValues.put("Name",name);
        contentValues.put("Email",email);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update("students", contentValues, "id="+Id,null);
        db.close();
    }


    public void delete(String Id, String name, String email) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id",Id);
        contentValues.put("Name",name);
        contentValues.put("Email",email);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("students", "Id=?", new String[]{Id});
        db.close();
    }

    public List<StudentModel> selectAll(){
        List<StudentModel> studentModelList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM students", null);
        if (c.moveToFirst()){
            do {
                StudentModel studentModel = new StudentModel();
                studentModel.setId(c.getString(0));
                studentModel.setName(c.getString(1));
                studentModel.setEmail(c.getString(2));
                studentModelList.add(studentModel);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return studentModelList;
    }
    public  List<StudentModel> select(String id,String name,String email){
        List<StudentModel> studentModelList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM students  where Id =?", new String[]{id});
        if (c.moveToFirst()){
            do {
                StudentModel studentModel = new StudentModel();
                studentModel.setId(c.getString(0));
                studentModel.setName(c.getString(1));
                studentModel.setEmail(c.getString(2));
                studentModelList.add(studentModel);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return studentModelList;
    }
}
