package com.lytech.xvjialing.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xvjialing on 2018/1/2.
 */

public class Message implements Serializable{

    private Integer id;

    private Long time;

    private String message;

    private Integer teacher_id;

    private List<Student> studentList;

    private Integer recieverRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Integer getRecieverRole() {
        return recieverRole;
    }

    public void setRecieverRole(Integer recieverRole) {
        this.recieverRole = recieverRole;
    }


    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", time=" + time +
                ", message='" + message + '\'' +
                ", teacher_id=" + teacher_id +
                ", studentList=" + studentList +
                ", recieverRole=" + recieverRole +
                '}';
    }
}
