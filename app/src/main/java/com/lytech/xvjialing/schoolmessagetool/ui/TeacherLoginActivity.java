package com.lytech.xvjialing.schoolmessagetool.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lytech.xvjialing.common.bean.Result;
import com.lytech.xvjialing.common.bean.Teacher;
import com.lytech.xvjialing.common.utils.SPDataUtils;
import com.lytech.xvjialing.common.utils.ToastUtils;
import com.lytech.xvjialing.network.NetUtils;
import com.lytech.xvjialing.schoolmessagetool.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.lytech.xvjialing.schoolmessagetool.ui.StartActivity.startActivityWeakReference;

public class TeacherLoginActivity extends AppCompatActivity {

    @BindView(R.id.tiet_username)
    TextInputEditText tietUsername;
    @BindView(R.id.tiet_password)
    TextInputEditText tietPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_register:
                startActivity(new Intent(this,TeacherRegisterActivity.class));
                break;
        }
    }

    private void login() {
        if (TextUtils.isEmpty(getUserName())){
            showMsg("用户名为空");
            return;
        }
        if (TextUtils.isEmpty(getPassword())){
            showMsg("密码为空");
            return;
        }
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

                    }

                    @Override
                    public void onNext(Result<Teacher> teacherResult) {
                        if (teacherResult.isStatus()){
                            saveTeacher(teacherResult.getData());
                            showMsg("登陆成功");
                            startActivityWeakReference.get().finish();
                            finish();
                            gotoTeacherMainAc();
                        }else {
                            showMsg(teacherResult.getMessage());
                        }
                    }
                });
    }

    private void saveTeacher(Teacher teacher) {
        SPDataUtils.setTeacher(this, JSON.toJSONString(teacher));
    }

    private void showMsg(String message) {
        ToastUtils.showMessage(message,this);
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
}
