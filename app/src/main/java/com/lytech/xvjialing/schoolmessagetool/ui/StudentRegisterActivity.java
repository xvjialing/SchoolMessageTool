package com.lytech.xvjialing.schoolmessagetool.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.lytech.xvjialing.common.bean.Result;
import com.lytech.xvjialing.common.bean.SchoolClass;
import com.lytech.xvjialing.common.bean.Student;
import com.lytech.xvjialing.common.conf.UserType;
import com.lytech.xvjialing.common.utils.ToastUtils;
import com.lytech.xvjialing.network.NetUtils;
import com.lytech.xvjialing.schoolmessagetool.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StudentRegisterActivity extends AppCompatActivity {

    private static final String TAG = StudentRegisterActivity.class.getSimpleName();
    @BindView(R.id.tiet_username)
    TextInputEditText tietUsername;
    @BindView(R.id.tiet_password)
    TextInputEditText tietPassword;
    @BindView(R.id.tiet_checkpassword)
    TextInputEditText tietCheckpassword;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.tiet_name)
    TextInputEditText tietName;

    private List<SchoolClass> classList = new ArrayList<SchoolClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        ButterKnife.bind(this);

        getClassList();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        register();
    }

    private void register() {
        if (TextUtils.isEmpty(getUsername())) {
            showMsg("用户名为空");
            return;
        }
        if (TextUtils.isEmpty(getName())) {
            showMsg("姓名为空");
            return;
        }
        if (TextUtils.isEmpty(getPassword())) {
            showMsg("密码为空");
            return;
        }
        if (TextUtils.isEmpty(getCheckPassword())) {
            showMsg("确认密码为空");
            return;
        }
        if (!TextUtils.equals(getPassword(), getCheckPassword())) {
            showMsg("两次密码不相同");
            return;
        }
//        PostStudent postStudent=new PostStudent();
//        postStudent.setUsername(getUsername());
//        postStudent.setPassword(getPassword());
//        postStudent.setRole(UserType.USER_TYPE_STUDENT);
//        postStudent.setSchoolClass(getSchoolClass());
//        Log.d(TAG, "register: "+JSON.toJSONString(postStudent));
        NetUtils.getInstance()
                .getNetService()
                .student_register(getUsername(), getPassword(), UserType.USER_TYPE_STUDENT, JSON.toJSONString(getSchoolClass()),getName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<Student>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onNext(Result<Student> studentResult) {
                        if (studentResult.isStatus()) {
                            showMsg("注册成功");
                            finish();
                        } else {
                            showMsg(studentResult.getMessage());
                        }
                    }
                });

    }

    @SuppressLint("ResourceType")
    public SchoolClass getSchoolClass() {
//        Log.d(TAG, "getSchoolClass: "+radioGroup.getCheckedRadioButtonId());
//        SchoolClass schoolClass = classList.get(radioGroup.getCheckedRadioButtonId() - 1);
//        return schoolClass;
        int index = radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()));
        SchoolClass schoolClass = classList.get(index);
        return schoolClass;
    }

    public String getUsername() {
        return tietUsername.getText().toString().trim();
    }

    public String getPassword() {
        return tietPassword.getText().toString().trim();
    }

    public String getCheckPassword() {
        return tietCheckpassword.getText().toString().trim();
    }

    public String getName() {
        return tietName.getText().toString().trim();
    }

    private void showMsg(String msg) {
        ToastUtils.showMessage(msg, this);
    }


    public void getClassList() {
        NetUtils.getInstance()
                .getNetService()
                .getClassList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<SchoolClass>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Result<List<SchoolClass>> listResult) {
                        if (listResult.isStatus()) {
                            classList = listResult.getData();
                            addClassView();
                        } else {
                            showMsg("获取班级列表失败");
                        }
                    }
                });
    }

    public void addClassView() {
        int i = 0;
        for (SchoolClass item : classList) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setPadding(80, 0, 0, 0);
            radioButton.setText(item.getClassNumber());
            radioGroup.addView(radioButton, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i == 0) {
                radioGroup.check(radioButton.getId());
            }
            i++;
        }
    }

}
