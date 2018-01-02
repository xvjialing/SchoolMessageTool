package com.lytech.xvjialing.schoolmessagetool.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.tiet_username)
    TextInputEditText tietUsername;
    @BindView(R.id.tiet_password)
    TextInputEditText tietPassword;
    @BindView(R.id.sp_role)
    Spinner spRole;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_register:
                register();
                break;
        }
    }

    private void register() {
        startActivity(new Intent(this,RegisterActivity.class));
    }

    private void login() {
        if (getRole()==UserType.USER_TYPE_STUDENT){
            studentLogin();
        }else {
            teacherLogin();
        }
    }

    private void teacherLogin() {
        NetUtils.getInstance()
                .getNetService()
                .teacher_login(getUserName(),getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<Teacher>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e);
                    }

                    @Override
                    public void onNext(Result<Teacher> teacherResult) {
                        if (!teacherResult.isStatus()){
                            showMsg(teacherResult.getMessage());
                        }else {
                            Log.d(TAG, "onNext: "+teacherResult.toString());
                            gotoTeacherMainAc();
                        }

                    }
                });
    }


    private void studentLogin() {
        NetUtils.getInstance()
                .getNetService()
                .student_login(getUserName(),getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<Student>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Result<Student> studentResult) {
                        if (!studentResult.isStatus()){
                            showMsg(studentResult.getMessage());
                        }else {
                            Log.d(TAG, "onNext: "+studentResult.toString());
                            gotoStudentMainAc();
                        }
                    }
                });
    }

    private void showMsg(String message) {
        ToastUtils.showMessage(message,this);
    }

    private void gotoStudentMainAc(){
        startActivity(new Intent(this,StudentMainActivity.class));
    }

    private void gotoTeacherMainAc(){
        startActivity(new Intent(this,TeacherMainActivity.class));
    }

    public String getUserName(){
        return tietUsername.getText().toString();
    }

    public String getPassword(){
        return tietPassword.getText().toString();
    }

    public int getRole(){
        String rolestr=spRole.getSelectedItem().toString();
//        Log.d(TAG, "getRole: "+rolestr);
        if (TextUtils.equals("学生",rolestr)){
            return UserType.USER_TYPE_STUDENT;
        }else{
            return UserType.USER_TYPE_TEACHER;
        }
    }

}
