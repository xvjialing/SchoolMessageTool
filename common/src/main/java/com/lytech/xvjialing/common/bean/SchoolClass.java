package com.lytech.xvjialing.common.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by xvjialing on 2018/1/9.
 */

public class SchoolClass implements Serializable {

    private Integer id;

    private String classNumber;

    private boolean checked=false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", classNumber='" + classNumber + '\'' +
                ", checked=" + checked +
                '}';
    }

}
