package com.uet.trungthao.studentmanager.model;

import java.io.Serializable;

/**
 * Created by JiH on 04/10/2016.
 */

public class Student implements Serializable {
    private String name, email, sex, id;
    private int face;

    public Student(String id, int face, String name, String email, String sex) {
        this.name = name;
        this.email = email;
        this.sex = sex;
        this.id = id;
        this.face = face;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }
}
