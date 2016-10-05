package com.uet.trungthao.studentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.uet.trungthao.studentmanager.model.DatabaseHelper;
import com.uet.trungthao.studentmanager.model.Student;

import java.util.ArrayList;

/**
 * Created by JiH on 03/10/2016.
 */

public class ManagerActivity extends AppCompatActivity implements IListener{
    private static final int REQUEST_CODE_MANAGER_ACTIVITY = 1112;
    private SesstionManager manager;
    private ImageView imgAdd, imgEdit, imgDelete, imgLogout;
    private ListView lvStudent;
    private ListViewAdapter adapter;
    private DatabaseHelper db;
    private ArrayList<Student> itemArr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        manager = new SesstionManager(getApplicationContext());

        imgAdd = (ImageView) findViewById(R.id.img_add);
//        imgDelete = (ImageView) findViewById(R.id.img_delete);
//        imgEdit = (ImageView) findViewById(R.id.img_edit);
        imgLogout = (ImageView) findViewById(R.id.img_logout);

        db = DatabaseHelper.getInstance(getApplicationContext());
        db.createDatabase();
        lvStudent = (ListView) findViewById(R.id.lv_student);
        itemArr = db.getAllStudent();
        adapter = new ListViewAdapter(getApplicationContext(), itemArr);
        adapter.setListener(this);
        lvStudent.setAdapter(adapter);

        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = adapter.getStudent(i);
                Intent mIntent = new Intent(ManagerActivity.this, ShowInfo.class);
                mIntent.putExtra("id", student.getId());
                mIntent.putExtra("face", student.getFace());
                mIntent.putExtra("name", student.getName());
                mIntent.putExtra("email", student.getEmail());
                mIntent.putExtra("sex", student.getSex());
                startActivity(mIntent);
            }
        });

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(ManagerActivity.this, AddStutentActivity.class);
                startActivityForResult(mIntent, REQUEST_CODE_MANAGER_ACTIVITY);
            }
        });

        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.clearSession();
                startActivity(new Intent(ManagerActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_MANAGER_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                itemArr = db.getAllStudent();
                adapter = new ListViewAdapter(getApplicationContext(), itemArr);
                adapter.setListener(this);
                lvStudent.setAdapter(adapter);
            }
        }
    }

    @Override
    public void update() {
        itemArr = db.getAllStudent();
        adapter = new ListViewAdapter(getApplicationContext(), itemArr);
        adapter.setListener(this);
        lvStudent.setAdapter(adapter);
    }

    @Override
    public void showEditActivity(Student std) {
        Intent mIntent = new Intent(ManagerActivity.this, EditInfoStudent.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("student", std);
        mIntent.putExtras(bundle);
        startActivityForResult(mIntent, REQUEST_CODE_MANAGER_ACTIVITY);
    }
}
