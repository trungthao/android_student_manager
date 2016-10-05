package com.uet.trungthao.studentmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uet.trungthao.studentmanager.model.DatabaseHelper;
import com.uet.trungthao.studentmanager.model.Student;

/**
 * Created by JiH on 05/10/2016.
 */
public class AddStutentActivity extends AppCompatActivity{

    private EditText edtEmail, edtName, edtID, edtSex;
    private Button btnAdd;

    public AddStutentActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtID = (EditText) findViewById(R.id.edt_id);
        edtName = (EditText) findViewById(R.id.edt_name);
        edtSex = (EditText) findViewById(R.id.edt_sex);
        btnAdd = (Button) findViewById(R.id.btn_add);

        final boolean[] isAdd = {true};

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtEmail.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "Email khong duoc de trong", Toast.LENGTH_SHORT).show();
                    isAdd[0] = false;
                }
                if (edtName.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "Name khong duoc de trong", Toast.LENGTH_SHORT).show();
                    isAdd[0] = false;
                }

                if (edtID.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "ID khong duoc de trong", Toast.LENGTH_SHORT).show();
                    isAdd[0] = false;
                }
                if (edtSex.getText().equals("")) {
                    Toast.makeText(getApplicationContext(), "Sex khong duoc de trong", Toast.LENGTH_SHORT).show();
                    isAdd[0] = false;
                }
                if (isAdd[0]) {
                    DatabaseHelper.getInstance(getApplicationContext())
                            .addStudent(new Student(edtID.getText().toString(),
                                    R.mipmap.xau,
                                    edtName.getText().toString(),
                                    edtEmail.getText().toString(),
                                    edtSex.getText().toString()));
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }

}
