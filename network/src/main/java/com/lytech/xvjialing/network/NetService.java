package com.lytech.xvjialing.network;

import com.lytech.xvjialing.common.bean.Message;
import com.lytech.xvjialing.common.bean.PostStudent;
import com.lytech.xvjialing.common.bean.Result;
import com.lytech.xvjialing.common.bean.SchoolClass;
import com.lytech.xvjialing.common.bean.Student;
import com.lytech.xvjialing.common.bean.Subject;
import com.lytech.xvjialing.common.bean.Teacher;

import java.util.Date;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by xvjialing on 2018/1/2.
 */

public interface NetService {

    @FormUrlEncoded
    @POST("/api/v1/student")
//    Observable<Result<Student>> student_register(@Body PostStudent postStudent);
    Observable<Result<Student>> student_register(@Field("username") String username,
                                                 @Field("password") String password,
                                                 @Field("role") int role,
                                                 @Field("schoolClass") String schoolClass,
                                                    @Field("name") String name);

    @FormUrlEncoded
    @POST("/api/v1/student/login")
    Observable<Result<Student>> student_login(@Field("username") String username,
                                              @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/v1/teacher")
    Observable<Result<Teacher>> teacher_register(@Field("username") String username,
                                                 @Field("password") String password,
                                                 @Field("role") int role,
                                                 @Field("subject") String subject,
                                                 @Field("classList") String classList,
                                                 @Field("name") String name);

    @FormUrlEncoded
    @POST("/api/v1/teacher/login")
    Observable<Result<Teacher>> teacher_login(@Field("username") String username,
                                              @Field("password") String password);

    @GET("/api/v1/teacher/{id}")
    Observable<Result<Teacher>> getTeacher(@Path("id") int id);

    @GET("/api/v1/class")
    Observable<Result<List<SchoolClass>>> getClassList();

    @GET("/api/v1/subject")
    Observable<Result<List<Subject>>> getSubjectList();

    @FormUrlEncoded
    @POST("/api/v1/message")
    Observable<Result<Message>> addMessage(@Field("time") String time,
                                           @Field("message") String message,
                                           @Field("publisher") String publisher,
                                           @Field("schoolClass") String schoolClass,
                                           @Field("messageType") int messageType);

    @GET("/api/v1/message/{classid}/{messagetype}")
    Observable<Result<List<Message>>> findMessageByClass(@Path("classid") int classid,
                                                         @Path("messagetype") int messagetype);
}
