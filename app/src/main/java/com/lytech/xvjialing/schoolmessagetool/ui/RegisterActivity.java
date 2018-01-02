package com.lytech.xvjialing.schoolmessagetool.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;

import com.lytech.xvjialing.common.bean.Result;
import com.lytech.xvjialing.common.bean.Student;
import com.lytech.xvjialing.common.bean.Teacher;
import com.lytech.xvjialing.common.conf.UserType;
import com.lytech.xvjialing.common.utils.ToastUtils;
import com.lytech.xvjialing.network.NetUtils;
import com.lytech.xvjialing.schoolmessagetool.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();
    @BindView(R.id.tiet_username)
    TextInputEditText tietUsername;
    @BindView(R.id.tiet_password)
    TextInputEditText tietPassword;
    @BindView(R.id.tiet_checkpassword)
    TextInputEditText tietCheckpassword;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        register();
    }

    private void register() {
        if (getRole()==UserType.USER_TYPE_STUDENT){
            student_register();
        }else {
            teacher_register();
        }
    }

    private void teacher_register() {
        NetUtils.getInstance()
                .getNetService()
                .teacher_register(getUsername(),getPassword(),UserType.USER_TYPE_TEACHER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<Teacher>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Result<Teacher> teacherResult) {
                        if (!teacherResult.isStatus()){
                            showMsg(teacherResult.getMessage());
                        }else {
                            showMsg("注册成功");
                            finish();
                        }
                    }
                });
    }

    private void student_register() {
        NetUtils.getInstance()
                .getNetService()
                .student_register(getUsername(),getPassword(),UserType.USER_TYPE_STUDENT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<Student>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e);
                    }

                    @Override
                    public void onNext(Result<Student> studentResult) {
                        if (!studentResult.isStatus()){
                            showMsg(studentResult.getMessage());
                        }else {
                            showMsg("注册成功");
                            finish();
                        }
                    }
                });
    }


    private void showMsg(String msg){
        ToastUtils.showMessage(msg,this);
    }

    private String getUsername(){
        return tietUsername.getText().toString();
    }

    private String getPassword(){
        return tietPassword.getText().toString();
    }

    private String getCheckPassword(){
        return tietCheckpassword.getText().toString();
    }

    private int getRole(){
        String roleStr=spinner.getSelectedItem().toString();
        if (TextUtils.equals("学生",roleStr)){
            return UserType.USER_TYPE_STUDENT;
        }else {
            return UserType.USER_TYPE_TEACHER;
        }
    }
}
