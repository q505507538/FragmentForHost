package com.jay.android.fragmentforhost;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
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

public class MainActivity extends FragmentActivity implements OnClickListener {

    // 定义3个Fragment的对象
    private Fragment1 fg1;
    private Fragment2 fg2;
    private Fragment3 fg3;
    private Fragment4 fg4;
    private Fragment5 fg5;
    // 帧布局对象,就是用来存放Fragment的容器
    private FrameLayout flayout;
    // 定义底部导航栏的三个布局
    private LinearLayout cuangti_layout;
    private LinearLayout daxiaobian_layout;
    private LinearLayout sangzikangfu_layout;
    private LinearLayout xiazikangfu_layout;
    private LinearLayout xitongsezi_layout;
    // 定义底部导航栏中的ImageView与TextView
    private ImageView cuangti_image;
    private ImageView daxiaobian_image;
    private ImageView sangzikangfu_image;
    private ImageView xiazikangfu_image;
    private ImageView xitongsezi_image;
    //	private TextView cuangti_text;
//	private TextView sangzikangfu_text;
//	private TextView daxiaobian_text;
//	private TextView xiazikangfu_text;
//	private TextView xitongsezi_text;
    // 定义要用的颜色值
    private int whirt = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int blue = 0xFF0AB2FB;
    // 定义FragmentManager对象
    FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_main);
        fManager = getSupportFragmentManager();
        initViews();
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

    // 完成组件的初始化
    public void initViews() {
        cuangti_image = (ImageView) findViewById(R.id.cuangti_image);
        daxiaobian_image = (ImageView) findViewById(R.id.daxiaobian_image);
        sangzikangfu_image = (ImageView) findViewById(R.id.sangzikangfu_image);
        xiazikangfu_image = (ImageView) findViewById(R.id.xiazikangfu_image);
        xitongsezi_image = (ImageView) findViewById(R.id.xitongsezi_image);

//		cuangti_text = (TextView) findViewById(R.id.cuangti_text);
//		daxiaobian_text = (TextView) findViewById(R.id.daxiaobian_text);
//		sangzikangfu_text = (TextView) findViewById(R.id.sangzikangfu_text);
//		xiazikangfu_text = (TextView) findViewById(R.id.xiazikangfu_text);
//		xitongsezi_text = (TextView) findViewById(R.id.xitongsezi_text);

        cuangti_layout = (LinearLayout) findViewById(R.id.cuangti_layout);
        daxiaobian_layout = (LinearLayout) findViewById(R.id.daxiaobian_layout);
        sangzikangfu_layout = (LinearLayout) findViewById(R.id.sangzikangfu_layout);
        xiazikangfu_layout = (LinearLayout) findViewById(R.id.xiazikangfu_layout);
        xitongsezi_layout = (LinearLayout) findViewById(R.id.xitongsezi_layout);

        cuangti_layout.setOnClickListener(this);
        daxiaobian_layout.setOnClickListener(this);
        sangzikangfu_layout.setOnClickListener(this);
        xiazikangfu_layout.setOnClickListener(this);
        xitongsezi_layout.setOnClickListener(this);
    }

    // 重写onClick事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cuangti_layout:
                setChioceItem(0);
                break;
            case R.id.daxiaobian_layout:
                setChioceItem(1);
                break;
            case R.id.sangzikangfu_layout:
                setChioceItem(2);
                break;
            case R.id.xiazikangfu_layout:
                setChioceItem(3);
                break;
            case R.id.xitongsezi_layout:
                setChioceItem(4);
                break;
            default:
                break;
        }

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
                    fg1 = new Fragment1();
                    transaction.add(R.id.content, fg1);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fg1);
                }
                break;

            case 1:
                daxiaobian_image.setImageResource(R.drawable.tab_3_p);
                if (fg2 == null) {
                    // 如果fg1为空，则创建一个并添加到界面上
                    fg2 = new Fragment2();
                    transaction.add(R.id.content, fg2);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fg2);
                }
                break;

            case 2:
                sangzikangfu_image.setImageResource(R.drawable.tab_4_p);
                if (fg3 == null) {
                    // 如果fg1为空，则创建一个并添加到界面上
                    fg3 = new Fragment3();
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
                    fg4 = new Fragment4();
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
                    fg5 = new Fragment5();
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
