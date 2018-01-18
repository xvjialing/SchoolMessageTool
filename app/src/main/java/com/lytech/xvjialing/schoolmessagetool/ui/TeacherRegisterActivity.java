package com.lytech.xvjialing.schoolmessagetool.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.alibaba.fastjson.JSON;
import com.lytech.xvjialing.common.bean.Result;
import com.lytech.xvjialing.common.bean.SchoolClass;
import com.lytech.xvjialing.common.bean.Subject;
import com.lytech.xvjialing.common.bean.Teacher;
import com.lytech.xvjialing.common.conf.UserType;
import com.lytech.xvjialing.common.utils.ToastUtils;
import com.lytech.xvjialing.network.NetUtils;
import com.lytech.xvjialing.schoolmessagetool.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TeacherRegisterActivity extends AppCompatActivity {

    private static final String TAG = TeacherRegisterActivity.class.getSimpleName();
    @BindView(R.id.tiet_username)
    TextInputEditText tietUsername;
    @BindView(R.id.tiet_password)
    TextInputEditText tietPassword;
    @BindView(R.id.tiet_checkpassword)
    TextInputEditText tietCheckpassword;
    @BindView(R.id.rv_classlist)
    RecyclerView rvClasslist;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.sp_subject)
    Spinner spSubject;
    @BindView(R.id.tiet_name)
    TextInputEditText tietName;

    private List<SchoolClass> classList = new ArrayList<SchoolClass>();
    private List<Subject> subjectList = new ArrayList<>();
    private CommonAdapter<SchoolClass> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);
        ButterKnife.bind(this);

        initView();
        getClassList();
    }

    private void initView() {
//        LinearLayoutManager manager=new LinearLayoutManager(this);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        rvClasslist.setLayoutManager(manager);

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
        boolean tag = false;
        for (SchoolClass item : classList) {
            if (item.isChecked()) {
                tag = true;
            }
        }
        if (!tag) {
            showMsg("至少选择一个班级");
            return;
        }
        List<SchoolClass> result = new ArrayList<>();
//        Iterator iterator=result.iterator();
//        while (iterator.hasNext()){
//            SchoolClass schoolClass = (SchoolClass) iterator.next();
//            if (!schoolClass.isChecked()){
//                result.remove(schoolClass);
//            }
//        }
        for (SchoolClass item : classList) {
            if (item.isChecked()) {
                result.add(item);
            }
        }
        String schoolClassList = JSON.toJSONString(result);
        NetUtils.getInstance()
                .getNetService()
                .teacher_register(getUsername(), getPassword(), UserType.USER_TYPE_TEACHER, getSubject(), schoolClassList,getName())
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
                            showMsg("注册成功");
                            finish();
                        } else {
                            showMsg(teacherResult.getMessage());
                        }
                    }
                });
    }

    public String getUsername() {
        return tietUsername.getText().toString().trim();
    }

    public String getName() {
        return tietName.getText().toString().trim();
    }

    public String getPassword() {
        return tietPassword.getText().toString().trim();
    }

    public String getCheckPassword() {
        return tietCheckpassword.getText().toString().trim();
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
                            Log.d(TAG, "onNext: " + classList.size());

                            loadClassList(classList);
                        } else {
                            showMsg("获取班级列表失败");
                        }
                    }
                });
    }

    public String getSubject() {
        return spSubject.getSelectedItem().toString();
    }

    private void loadClassList(List<SchoolClass> classList) {
        adapter = new CommonAdapter<SchoolClass>(this, R.layout.item_class, classList) {
            @Override
            protected void convert(ViewHolder holder, final SchoolClass schoolClass, int position) {
                CheckBox checkBox = holder.<CheckBox>getView(R.id.checkBox);
                checkBox.setText(schoolClass.getClassNumber());
                checkBox.setChecked(schoolClass.isChecked());
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        schoolClass.setChecked(b);
                    }
                });
            }
        };
        rvClasslist.setAdapter(adapter);
    }


}
