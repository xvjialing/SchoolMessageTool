package com.lytech.xvjialing.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xvjialing on 2018/1/2.
 */

public class Student implements Serializable{
    private Integer id;

    private User user;

    private List<Message> messageList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", user=" + user +
                ", messageList=" + messageList +
                '}';
    }
}
