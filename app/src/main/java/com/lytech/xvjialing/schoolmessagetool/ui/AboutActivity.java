package com.lytech.xvjialing.schoolmessagetool.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lytech.xvjialing.common.utils.ApkVersionCodeUtils;
import com.lytech.xvjialing.schoolmessagetool.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        tvVersion.setText("版本号 "+ApkVersionCodeUtils.getVerName(this));
    }
}
