package com.lytech.xvjialing.schoolmessagetool.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lytech.xvjialing.common.bean.Student;
import com.lytech.xvjialing.common.conf.UserType;
import com.lytech.xvjialing.common.utils.SPDataUtils;
import com.lytech.xvjialing.schoolmessagetool.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentAccountActivity extends AppCompatActivity {

    private static final String TAG = StudentAccountActivity.class.getSimpleName();
    @BindView(R.id.tv_studentId)
    TextView tvStudentId;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_role)
    TextView tvRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_account);
        ButterKnife.bind(this);

        getStudent();
    }

    private void getStudent() {
        Student student = JSON.parseObject(SPDataUtils.getStudent(this), Student.class);
        Log.d(TAG, "getStudent: " + student.toString());

        setStudentrId(student.getId().toString());
        setName(student.getUser().getName());
        setUsername(student.getUser().getUsername());
        setRole(student.getUser().getRole());
    }

    public void setStudentrId(String studentrId){
        tvStudentId.setText(studentrId);
    }

    public void setName(String name){
        tvName.setText(name);
    }

    public void setUsername(String username){
        tvUsername.setText(username);
    }

    public void setRole(int role){
        if (role== UserType.USER_TYPE_STUDENT){
            tvRole.setText("学生");
        }
    }
}
