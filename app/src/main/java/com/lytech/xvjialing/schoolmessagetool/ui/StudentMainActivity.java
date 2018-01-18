package com.lytech.xvjialing.schoolmessagetool.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lytech.xvjialing.schoolmessagetool.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentMainActivity extends AppCompatActivity {

    @BindView(R.id.tv_notice)
    TextView tvNotice;
    @BindView(R.id.tv_work)
    TextView tvWork;
    @BindView(R.id.tv_classschedule)
    TextView tvClassschedule;
    @BindView(R.id.tv_score)
    TextView tvScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.tv_notice, R.id.tv_work, R.id.tv_classschedule, R.id.tv_score})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_notice:
                startActivity(new Intent(this,NoticeActivity.class));
                break;
            case R.id.tv_work:
                startActivity(new Intent(this,WorkActivity.class));
                break;
            case R.id.tv_classschedule:
                startActivity(new Intent(this,ClassScheduleActivity.class));
                break;
            case R.id.tv_score:
                startActivity(new Intent(this,ScoreActivity.class));
                break;
        }
    }
}
