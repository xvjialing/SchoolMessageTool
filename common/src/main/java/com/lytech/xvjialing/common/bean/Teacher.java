package com.lytech.xvjialing.common.bean;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by xvjialing on 2018/1/2.
 */

public class Teacher implements Serializable{

    private Integer id;

    private User user;

    private Set<Message> sendMessageSet;

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

    public Set<Message> getSendMessageSet() {
        return sendMessageSet;
    }

    public void setSendMessageSet(Set<Message> sendMessageSet) {
        this.sendMessageSet = sendMessageSet;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", user=" + user +
                ", sendMessageSet=" + sendMessageSet +
                '}';
    }
}
