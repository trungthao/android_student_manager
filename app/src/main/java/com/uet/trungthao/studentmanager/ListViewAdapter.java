package com.uet.trungthao.studentmanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.uet.trungthao.studentmanager.model.DatabaseHelper;
import com.uet.trungthao.studentmanager.model.Student;

import java.util.ArrayList;

/**
 * Created by JiH on 04/10/2016.
 */

public class ListViewAdapter extends BaseAdapter {
    private IListener listener;
    private TextView tvEmail, tvID, tvName, tvSex;
    private ImageView imgFace;
    private ImageView imgEdit, imgDelete;
    private Context mContext;
    private ArrayList<Student> itemArr;
    private LayoutInflater lf;

    public ListViewAdapter(Context mContext, ArrayList<Student> itemArr) {
        this.mContext = mContext;
        this.itemArr = itemArr;
        lf = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if (itemArr != null) return itemArr.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return itemArr.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = lf.inflate(R.layout.item_student, viewGroup, false);
        }

        imgFace = (ImageView) view.findViewById(R.id.img_face);
        tvEmail = (TextView) view.findViewById(R.id.tv_email);
        tvID = (TextView) view.findViewById(R.id.tv_id);
        tvSex = (TextView) view.findViewById(R.id.tv_sex);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        imgDelete = (ImageView) view.findViewById(R.id.img_delete);
        imgEdit = (ImageView) view.findViewById(R.id.img_edit);

//        imgDelete.setVisibility(View.INVISIBLE);
//        imgEdit.setVisibility(View.INVISIBLE);

        final int j = i;

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = DatabaseHelper.getInstance(mContext);
                db.delete(itemArr.get(j));
                listener.update();
            }
        });

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.showEditActivity(itemArr.get(j));
            }
        });

        Student student = itemArr.get(i);
        imgFace.setImageResource(student.getFace());
        tvID.setText(student.getId());
        tvName.setText(student.getName());
        tvEmail.setText(student.getEmail());
        tvSex.setText(student.getSex());
        return view;
    }

    public Student getStudent(int i) {
        return itemArr.get(i);
    }

    public void setListener(IListener listener) {
        this.listener = listener;
    }
}
