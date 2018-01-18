package com.lytech.xvjialing.schoolmessagetool.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.lytech.xvjialing.common.bean.Message;
import com.lytech.xvjialing.common.bean.Result;
import com.lytech.xvjialing.common.bean.SchoolClass;
import com.lytech.xvjialing.common.conf.MessageType;
import com.lytech.xvjialing.common.utils.SPDataUtils;
import com.lytech.xvjialing.common.utils.TimeUtils;
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

public class PublishNoticeActivity extends AppCompatActivity {

    private static final String TAG = PublishNoticeActivity.class.getSimpleName();
    @BindView(R.id.et_notice)
    EditText etNotice;
    @BindView(R.id.rg_classes)
    RadioGroup rgClasses;
    @BindView(R.id.btn_publish)
    Button btnPublish;

    private List<SchoolClass> classList=new ArrayList<SchoolClass>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_notice);
        ButterKnife.bind(this);

        getClassList();

        Log.d(TAG, "onCreate: "+SPDataUtils.getTeacher(this));
    }

    private void showMsg(String msg){
        ToastUtils.showMessage(msg,this);
    }

    @SuppressLint("ResourceType")
    public SchoolClass getSchoolClass(){
//        Log.d(TAG, "getSchoolClass: "+radioGroup.getCheckedRadioButtonId());
//        int index=rgClasses.getCheckedRadioButtonId()-1;
        int index=rgClasses.indexOfChild(rgClasses.findViewById(rgClasses.getCheckedRadioButtonId()));
        SchoolClass schoolClass = classList.get(index);
        return schoolClass;
    }

    public void getClassList(){
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
                        if (listResult.isStatus()){
                            classList= listResult.getData();
                            addClassView();
                        }else {
                            showMsg("获取班级列表失败");
                        }
                    }
                });
    }

    public void addClassView(){
        int i=0;
        for (SchoolClass item:classList){
            RadioButton radioButton=new RadioButton(this);
            radioButton.setPadding(80,0,0,0);
            radioButton.setText(item.getClassNumber());
            rgClasses.addView(radioButton, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i==0){
                rgClasses.check(radioButton.getId());
            }
            i++;
        }
    }

    @OnClick(R.id.btn_publish)
    public void onViewClicked() {
        publish();
    }

    private void publish() {
        NetUtils.getInstance()
                .getNetService()
                .addMessage(TimeUtils.getTime(),getNotice(), SPDataUtils.getTeacher(this), JSON.toJSONString(getSchoolClass()), MessageType.NOTICE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<Message>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Result<Message> messageResult) {
                        if (messageResult.isStatus()){
                            showMsg("通知发送成功");
                            finish();
                        }else {
                            showMsg("通知发布失败");
                        }
                    }
                });
    }


    public String getNotice(){
        return etNotice.getText().toString().trim();
    }


}
