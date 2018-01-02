package com.lytech.xvjialing.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xvjialing on 2018/1/2.
 */

public class Message implements Serializable{

    private Integer id;

    /**
     * 小米推送的消息id
     */
    private String messageId;

    /**
     * 小米推送的消息追踪id
     */
    private String trace_id;

    /**
     * 小米推送的消息失败的errorCode
     */
    private String errorCode;

    /**
     * 小米推送的消息失败的原因
     */
    private String reason;

    private Long timeStamp;

    private String message;

    private Teacher publisher;

    private List<Student> studentList;

    private Integer recieverRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTrace_id() {
        return trace_id;
    }

    public void setTrace_id(String trace_id) {
        this.trace_id = trace_id;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Teacher getPublisher() {
        return publisher;
    }

    public void setPublisher(Teacher publisher) {
        this.publisher = publisher;
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
}
