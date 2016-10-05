package com.uet.trungthao.studentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.uet.trungthao.studentmanager.model.DatabaseHelper;
import com.uet.trungthao.studentmanager.model.Student;

/**
 * Created by JiH on 05/10/2016.
 */
public class EditInfoStudent extends AppCompatActivity {
    private EditText edtEmail, edtName, edtSex;
    TextView tvID;
    private Button btnOk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info_student);

        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtName = (EditText) findViewById(R.id.edt_name);
        edtSex = (EditText) findViewById(R.id.edt_sex);
        btnOk = (Button) findViewById(R.id.btn_add);
        tvID = (TextView) findViewById(R.id.tv_id);

        Intent mIntent = getIntent();
        Bundle bundle = mIntent.getExtras();
        Student std = (Student) bundle.getSerializable("student");
        edtEmail.setText(std.getEmail());
        edtName.setText(std.getName());
        tvID.setText(std.getId());
        edtSex.setText(std.getSex());
        final int imageID = std.getFace();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student std = new Student(tvID.getText().toString(),
                        imageID,
                        edtName.getText().toString(),
                        edtEmail.getText().toString(),
                        edtSex.getText().toString());
                DatabaseHelper.getInstance(getApplicationContext()).update(std);
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}
