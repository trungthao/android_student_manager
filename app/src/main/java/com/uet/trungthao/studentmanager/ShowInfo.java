package com.uet.trungthao.studentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JiH on 04/10/2016.
 */
public class ShowInfo extends AppCompatActivity {

    private TextView tvEmail, tvName, tvID, tvSex;
    private ImageView imgFace;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);

        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvID = (TextView) findViewById(R.id.tv_id);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        imgFace = (ImageView) findViewById(R.id.img_face);

        Intent mIntent = getIntent();
        imgFace.setImageResource(mIntent.getIntExtra("face",-1));
        tvID.setText(mIntent.getStringExtra("id"));
        tvName.setText(mIntent.getStringExtra("name"));
        tvEmail.setText(mIntent.getStringExtra("email"));
        tvSex.setText(mIntent.getStringExtra("sex"));
    }
}
