package com.uet.trungthao.studentmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.uet.trungthao.studentmanager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiH on 04/10/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DATABASE STUDENT";
    private static final String TABLE_NAME = "student";
    private static final int DATABASE_VERSION = 4;
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String EMAIL_COLUMN = "email";
    private static final String SEX_COLUMN = "sex";
    private static final String FACE_COLUMN = "face";
    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + "("
                + ID_COLUMN + " TEXT PRIMARY KEY, "
                + FACE_COLUMN + " INTEGER NOT NULL, "
                + NAME_COLUMN + " TEXT NOT NULL, "
                + EMAIL_COLUMN + " TEXT NOT NULL, "
                + SEX_COLUMN + " TEXT NOT NULL "
                + ")";

        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addStudent(Student std) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_COLUMN, std.getId());
        values.put(FACE_COLUMN, std.getFace());
        values.put(NAME_COLUMN, std.getName());
        values.put(EMAIL_COLUMN, std.getEmail());
        values.put(SEX_COLUMN, std.getSex());
        long rowID = db.insert(TABLE_NAME, null, values);
        if (rowID != -1) {
            return true;
        }
        db.close();
        return false;
    }

    public Student getStudent(String id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[] {ID_COLUMN, FACE_COLUMN, NAME_COLUMN, EMAIL_COLUMN, SEX_COLUMN},
                ID_COLUMN + "=?",
                new String[]{id},
                null, null, null, null);

        Student student = null;
        if (cursor != null && cursor.moveToFirst()) {
            student = new Student(cursor.getString(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        }
        cursor.close();
        db.close();
        return student;
    }

    public ArrayList<Student> getAllStudent() {
        ArrayList<Student> list = null;
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            list = new ArrayList<>();
            do {
                list.add(new Student(cursor.getString(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public int update(Student std) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_COLUMN,std.getId());
        values.put(FACE_COLUMN, std.getFace());
        values.put(NAME_COLUMN,std.getName());
        values.put(EMAIL_COLUMN,std.getEmail());
        values.put(SEX_COLUMN,std.getSex());
        int rowID = db.update(TABLE_NAME, values, ID_COLUMN + "=?", new String[] {std.getId()});
        db.close();
        return rowID;
    }

    public int delete(Student std) {
        SQLiteDatabase db = getWritableDatabase();
        int rowID = db.delete(TABLE_NAME, ID_COLUMN + "=?", new String[] {std.getId()});
        return rowID;
    }

    public void createDatabase() {
        if (instance != null) {
//            instance.addStudent(new Student("14020425", R.mipmap.thao, "Duong Trung Thao", "thao.duongtrung@gmail.com", "Men"));
        }
    }
}
