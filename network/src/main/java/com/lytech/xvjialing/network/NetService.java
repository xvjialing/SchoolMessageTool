package com.lytech.xvjialing.network;

import com.lytech.xvjialing.common.bean.Result;
import com.lytech.xvjialing.common.bean.Student;
import com.lytech.xvjialing.common.bean.Teacher;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xvjialing on 2018/1/2.
 */

public interface NetService {

    @FormUrlEncoded
    @POST("/api/v1/student")
    Observable<Result<Student>> student_register(@Field("username") String username,@Field("password") String password,@Field("role") int role);

    @FormUrlEncoded
    @POST("/api/v1/student/login")
    Observable<Result<Student>> student_login(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("/api/v1/teacher")
    Observable<Result<Teacher>> teacher_register(@Field("username") String username,@Field("password") String password,@Field("role") int role);

    @FormUrlEncoded
    @POST("/api/v1/teacher/login")
    Observable<Result<Teacher>> teacher_login(@Field("username") String username,@Field("password") String password);
}
