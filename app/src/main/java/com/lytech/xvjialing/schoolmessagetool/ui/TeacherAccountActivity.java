package com.lytech.xvjialing.schoolmessagetool.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lytech.xvjialing.common.bean.Teacher;
import com.lytech.xvjialing.common.conf.UserType;
import com.lytech.xvjialing.common.utils.SPDataUtils;
import com.lytech.xvjialing.common.utils.ToastUtils;
import com.lytech.xvjialing.schoolmessagetool.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeacherAccountActivity extends AppCompatActivity {

    private static final String TAG = TeacherAccountActivity.class.getSimpleName();
    @BindView(R.id.tv_teacherId)
    TextView tvTeacherId;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_role)
    TextView tvRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_account);
        ButterKnife.bind(this);

        getTeacher();
    }

    public void getTeacher() {
        Teacher teacher = JSON.parseObject(SPDataUtils.getTeacher(this), Teacher.class);
        Log.d(TAG, "getTeacher: " + teacher.toString());
        setTeacherId(teacher.getId().toString());
        setName(teacher.getUser().getName());
        setUsername(teacher.getUser().getUsername());
        setRole(teacher.getUser().getRole());

    }

    private void showMsg(String message) {
        ToastUtils.showMessage(message, this);
    }

    public void setTeacherId(String teacherId){
        tvTeacherId.setText(teacherId);
    }

    public void setName(String name){
        tvName.setText(name);
    }

    public void setUsername(String username){
        tvUsername.setText(username);
    }

    public void setRole(int role){
        if (role== UserType.USER_TYPE_TEACHER){
            tvRole.setText("教师");
        }
    }
}
