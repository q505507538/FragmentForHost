package com.jay.android.fragmentforhost;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

    // 定义五个Fragment的对象
    private Fragment1 fg1;
    private Fragment2 fg2;
    private Fragment3 fg3;
    private Fragment4 fg4;
    private Fragment5 fg5;
    // 帧布局对象,就是用来存放Fragment的容器
    private FrameLayout flayout;
    // 定义FragmentManager对象
    FragmentManager fManager;
    // 定义顶部导航栏的五个布局
    @ViewById
    LinearLayout cuangti_layout;
    @ViewById
    LinearLayout daxiaobian_layout;
    @ViewById
    LinearLayout sangzikangfu_layout;
    @ViewById
    LinearLayout xiazikangfu_layout;
    @ViewById
    LinearLayout xitongsezi_layout;
    // 定义底部导航栏中的ImageView
    @ViewById
    ImageView cuangti_image;
    @ViewById
    ImageView daxiaobian_image;
    @ViewById
    ImageView sangzikangfu_image;
    @ViewById
    ImageView xiazikangfu_image;
    @ViewById
    ImageView xitongsezi_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
//        setContentView(R.layout.activity_main);
        fManager = getSupportFragmentManager();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        /**
         * 设置为横屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    @Click(R.id.cuangti_layout)
    void cuangtiLayoutClicked() {
        setChioceItem(0);
    }

    @Click(R.id.daxiaobian_layout)
    void daxiaobianLayoutClicked() {
        setChioceItem(1);
    }

    @Click(R.id.sangzikangfu_layout)
    void sangzikangfuLayoutClicked() {
        setChioceItem(2);
    }

    @Click(R.id.xiazikangfu_layout)
    void xiazikangfuLayoutClicked() {
        setChioceItem(3);
    }

    @Click(R.id.xitongsezi_layout)
    void xitongseziLayoutClicked() {
        setChioceItem(4);
    }

    // 定义一个选中一个item后的处理
    public void setChioceItem(int index) {
        // 重置选项+隐藏所有Fragment
        FragmentTransaction transaction = fManager.beginTransaction();
        clearChioce();
        hideFragments(transaction);
        switch (index) {
            case 0:
                cuangti_image.setImageResource(R.drawable.tab_2_p);
                if (fg1 == null) {
                    // 如果fg1为空，则创建一个并添加到界面上
                    fg1 = new Fragment1_();
                    transaction.add(R.id.content, fg1);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fg1);
                }
                break;
            case 1:
                daxiaobian_image.setImageResource(R.drawable.tab_3_p);
                if (fg2 == null) {
                    // 如果fg2为空，则创建一个并添加到界面上
                    fg2 = new Fragment2_();
                    transaction.add(R.id.content, fg2);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fg2);
                }
                break;
            case 2:
                sangzikangfu_image.setImageResource(R.drawable.tab_4_p);
                if (fg3 == null) {
                    // 如果fg3为空，则创建一个并添加到界面上
                    fg3 = new Fragment3_();
                    transaction.add(R.id.content, fg3);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fg3);
                }
                break;
            case 3:
                xiazikangfu_image.setImageResource(R.drawable.tab_5_p);
                if (fg4 == null) {
                    // 如果fg4为空，则创建一个并添加到界面上
                    fg4 = new Fragment4_();
                    transaction.add(R.id.content, fg4);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fg4);
                }
                break;
            case 4:
                xitongsezi_image.setImageResource(R.drawable.tab_6_p);
                if (fg5 == null) {
                    // 如果fg5为空，则创建一个并添加到界面上
                    fg5 = new Fragment5_();
                    transaction.add(R.id.content, fg5);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fg5);
                }
                break;
        }
        transaction.commit();
    }

    // 隐藏所有的Fragment,避免fragment混乱
    private void hideFragments(FragmentTransaction transaction) {
        if (fg1 != null) transaction.hide(fg1);
        if (fg2 != null) transaction.hide(fg2);
        if (fg3 != null) transaction.hide(fg3);
        if (fg4 != null) transaction.hide(fg4);
        if (fg5 != null) transaction.hide(fg5);
    }

    // 定义一个重置所有选项的方法
    public void clearChioce() {
        cuangti_image.setImageResource(R.drawable.tab_2_n);
        daxiaobian_image.setImageResource(R.drawable.tab_3_n);
        sangzikangfu_image.setImageResource(R.drawable.tab_4_n);
        xiazikangfu_image.setImageResource(R.drawable.tab_5_n);
        xitongsezi_image.setImageResource(R.drawable.tab_6_n);
    }
}
