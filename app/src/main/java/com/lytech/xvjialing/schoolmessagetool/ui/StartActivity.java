package com.lytech.xvjialing.schoolmessagetool.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.lytech.xvjialing.schoolmessagetool.R;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {

    public static WeakReference<StartActivity>  startActivityWeakReference;

    @BindView(R.id.cv_student)
    CardView cvStudent;
    @BindView(R.id.cv_teacher)
    CardView cvTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        startActivityWeakReference=new WeakReference<StartActivity>(this);
    }

    @OnClick({R.id.cv_student, R.id.cv_teacher})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_student:
                startActivity(new Intent(this,StudentLoginActivity.class));
                break;
            case R.id.cv_teacher:
                startActivity(new Intent(this,TeacherLoginActivity.class));
                break;
        }
    }
}
