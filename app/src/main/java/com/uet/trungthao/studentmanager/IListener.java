package com.uet.trungthao.studentmanager;

import com.uet.trungthao.studentmanager.model.Student;

/**
 * Created by JiH on 05/10/2016.
 */

public interface IListener {
    void update();

    void showEditActivity(Student std);
}
