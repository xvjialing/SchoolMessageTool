package com.lytech.xvjialing.schoolmessagetool.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.lytech.xvjialing.common.bean.Message;
import com.lytech.xvjialing.common.bean.Result;
import com.lytech.xvjialing.common.bean.Student;
import com.lytech.xvjialing.common.conf.CommonStr;
import com.lytech.xvjialing.common.conf.MessageType;
import com.lytech.xvjialing.common.utils.SPDataUtils;
import com.lytech.xvjialing.common.utils.TimeUtils;
import com.lytech.xvjialing.common.utils.ToastUtils;
import com.lytech.xvjialing.network.NetUtils;
import com.lytech.xvjialing.schoolmessagetool.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WorkActivity extends AppCompatActivity {

    @BindView(R.id.rv_workList)
    RecyclerView rvWorkList;

    private List<Message> messageList = new ArrayList<>();

    private CommonAdapter<Message> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        ButterKnife.bind(this);

        initView();

        getWorkList();
    }

    private void getWorkList() {
        Student student = JSON.parseObject(SPDataUtils.getStudent(this), Student.class);

        int classId = student.getSchoolClassId();

        NetUtils.getInstance()
                .getNetService()
                .findMessageByClass(classId, MessageType.WORK)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<List<Message>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Result<List<Message>> listResult) {
                        if (listResult.isStatus()) {
                            messageList=listResult.getData();
                            loadMessageList(messageList);
                        } else {
                            showMsg("获取消息失败");
                        }
                    }
                });
    }

    private void initView() {
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rvWorkList.setLayoutManager(manager);
    }

    private void showMsg(String message) {
        ToastUtils.showMessage(message, this);
    }

    public void loadMessageList(List<Message> messages) {
        adapter = new CommonAdapter<Message>(this, R.layout.item_message, messages) {
            @Override
            protected void convert(ViewHolder holder, Message message, int position) {
                holder.setText(R.id.tv_msg, message.getMessage());
                holder.setText(R.id.tv_msgTime, TimeUtils.stampToDate(message.getTime()));
            }
        };
        rvWorkList.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Message message = messageList.get(position);
                toWorkDetailActivity(message);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void toWorkDetailActivity(Message message) {
        Intent intent=new Intent(this,WorkDetailActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable(CommonStr.MESSAGE,message);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
