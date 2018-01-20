package com.lytech.xvjialing.schoolmessagetool.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.lytech.xvjialing.schoolmessagetool.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeacherMainActivity extends AppCompatActivity {

    @BindView(R.id.cv_notice)
    CardView cvNotice;
    @BindView(R.id.cv_work)
    CardView cvWork;
    @BindView(R.id.cv_account)
    CardView cvAccount;
    @BindView(R.id.cv_about)
    CardView cvAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.cv_notice, R.id.cv_work, R.id.cv_account, R.id.cv_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_notice:
                startActivity(new Intent(this, PublishNoticeActivity.class));
                break;
            case R.id.cv_work:
                startActivity(new Intent(this, PublishWorkActivity.class));
                break;
            case R.id.cv_account:
                startActivity(new Intent(this,TeacherAccountActivity.class));
                break;
            case R.id.cv_about:
                startActivity(new Intent(this,AboutActivity.class));
                break;
        }
    }
}
