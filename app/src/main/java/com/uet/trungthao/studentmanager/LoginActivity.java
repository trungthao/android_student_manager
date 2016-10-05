package com.uet.trungthao.studentmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUserName, edtPassword;
    private Button btnLogin;
    private SesstionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manager = new SesstionManager(getApplicationContext());

        boolean isLogin = manager.getStatusLogin();

        final Intent mIntent = new Intent(this, ManagerActivity.class);

        if (isLogin) {
            startActivity(mIntent);
            finish();
        }
        else {
            setContentView(R.layout.activity_login);
            btnLogin = (Button) findViewById(R.id.btn_login);
            edtUserName = (EditText) findViewById(R.id.edt_user_name);
            edtPassword = (EditText) findViewById(R.id.edt_password);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    manager.setStatusLogin(edtUserName.getText().toString(), edtPassword.getText().toString());
                    startActivity(mIntent);
                    finish();
                }
            });
        }
    }
}
