package com.lytech.xvjialing.schoolmessagetool.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lytech.xvjialing.schoolmessagetool.R;
import com.lytech.xvjialing.schoolmessagetool.model.CourseModel;
import com.lytech.xvjialing.schoolmessagetool.view.CourseTableTestLayout;
import com.lytech.xvjialing.schoolmessagetool.view.courseTableTest2Layout;

import java.util.ArrayList;
import java.util.List;

public class ClassScheduleActivity extends AppCompatActivity {

    private CourseTableTestLayout mCourseTableTestLayout;

    List<CourseModel> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        initView();

        mCourseTableTestLayout.setCourseTimeLabels(getTimeLabels());

        mList = getData();
        mCourseTableTestLayout.setData(mList);
    }

    private void initView() {
        mCourseTableTestLayout = (CourseTableTestLayout) findViewById(R.id.layout_course);
    }


    private List<CourseModel> getData() {
        mList = new ArrayList<>();

        mList.add(getCourseModel(1, "数学", 1, 1));
        mList.add(getCourseModel(1, "语文", 4, 2));

        mList.add(getCourseModel(2, "生物", 2, 2));
        mList.add(getCourseModel(2, "地理", 5, 3));

        mList.add(getCourseModel(3, "化学", 3, 1));
        mList.add(getCourseModel(3, "物理", 5, 1));

        mList.add(getCourseModel(4, "数学", 4, 1));
        mList.add(getCourseModel(4, "数学", 6, 2));

        mList.add(getCourseModel(5, "数学", 1, 2));
        mList.add(getCourseModel(5, "体育", 3, 1));
        mList.add(getCourseModel(5, "英语", 5, 1));

        mList.add(getCourseModel(6, "英语", 7, 2));

        mList.add(getCourseModel(7, "政治", 1, 1));
        mList.add(getCourseModel(7, "语文", 2, 1));
        mList.add(getCourseModel(7, "地理", 6, 2));

        return mList;
    }



    private CourseModel getCourseModel(int week, String name, int start, int step) {
        CourseModel model = new CourseModel();
        model.week = week;
        model.name = name;
        model.start = start;
        model.step = step;
        return model;
    }

    private String[] getTimeLabels() {
        String[] timeLabels = new String[10];
        for (int i = 0; i < timeLabels.length; i++) {
            timeLabels[i] = (i + 1) + ":00";
        }

        return timeLabels;
    }
}
