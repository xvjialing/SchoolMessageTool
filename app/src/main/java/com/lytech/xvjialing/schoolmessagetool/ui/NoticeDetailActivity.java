package com.lytech.xvjialing.schoolmessagetool.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.lytech.xvjialing.common.bean.Message;
import com.lytech.xvjialing.common.bean.Result;
import com.lytech.xvjialing.common.bean.Teacher;
import com.lytech.xvjialing.common.conf.CommonStr;
import com.lytech.xvjialing.common.utils.TimeUtils;
import com.lytech.xvjialing.common.utils.ToastUtils;
import com.lytech.xvjialing.network.NetUtils;
import com.lytech.xvjialing.schoolmessagetool.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NoticeDetailActivity extends AppCompatActivity {

    private static final String TAG = NoticeDetailActivity.class.getSimpleName();
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_teacherName)
    TextView tvTeacherName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        ButterKnife.bind(this);
        try {
            Message message = (Message) getIntent().getSerializableExtra(CommonStr.MESSAGE);
            setContent(message.getMessage());
            setTvTime("时间："+TimeUtils.stampToDate(message.getTime()));
            Log.d(TAG, "onCreate: " + message.toString());
            getTeacher(message.getTeacher_id());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getTeacher(int id) {
        NetUtils.getInstance()
                .getNetService()
                .getTeacher(id)
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
                        if (teacherResult.isStatus()) {
                            Log.d(TAG, "onNext: " + teacherResult.getData().toString());
                            setTvTeacherName("教师："+teacherResult.getData().getUser().getName());
                        } else {
                            showMsg(teacherResult.getMessage());
                        }
                    }
                });

    }


    private void showMsg(String message) {
        ToastUtils.showMessage(message, this);
    }

    public void setContent(String content){
        tvContent.setText(content);
    }

    public void setTvTime(String time){
        tvTime.setText(time);
    }

    public void setTvTeacherName(String teacherName){
        tvTeacherName.setText(teacherName);
    }
}
