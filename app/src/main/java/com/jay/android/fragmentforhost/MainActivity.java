package com.jay.android.fragmentforhost;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jay.android.fragmentforhost.Help.BLEHelp;
import com.jay.android.fragmentforhost.Help.CRCHelp;
import com.jay.android.fragmentforhost.Utils.UIUtils;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    private byte[] sendbytes = null;
    Intent intent;
    // 定义五个Fragment的对象
    private Fragment1_ fg1 = new Fragment1_();
    private Fragment2_ fg2 = new Fragment2_();
    private Fragment3_ fg3 = new Fragment3_();
    private Fragment4_ fg4 = new Fragment4_();
    private Fragment5_ fg5 = new Fragment5_();
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
    @ViewById // 急停按钮
    LinearLayout stop_layout;

    BLEHelp bleHelp = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        bleHelp = new BLEHelp(MainActivity.this);
    }

    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Click(R.id.cuangti_layout)
    void cuangtiLayoutClicked() {
        setChioceItem(fg1);
        cuangti_image.setImageResource(R.drawable.tab_2_p);
    }

    @Click(R.id.daxiaobian_layout)
    void daxiaobianLayoutClicked() {
        setChioceItem(fg2);
        daxiaobian_image.setImageResource(R.drawable.tab_3_p);
    }

    @Click(R.id.sangzikangfu_layout)
    void sangzikangfuLayoutClicked() {
        setChioceItem(fg3);
        sangzikangfu_image.setImageResource(R.drawable.tab_4_p);
    }

    @Click(R.id.xiazikangfu_layout)
    void xiazikangfuLayoutClicked() {
        setChioceItem(fg4);
        xiazikangfu_image.setImageResource(R.drawable.tab_5_p);
    }

    @Click(R.id.xitongsezi_layout)
    void xitongseziLayoutClicked() {
        setChioceItem(fg5);
        xitongsezi_image.setImageResource(R.drawable.tab_6_p);
    }

    // 定义一个选中一个item后的处理
    public void setChioceItem(Fragment fragment) {
        clearChioce();
        // 定义FragmentManager对象
        FragmentManager fragmentManager = getFragmentManager();
        // 定义FragmentTransaction对象
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fg_bottom, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // 定义一个重置所有选项的方法
    public void clearChioce() {
        cuangti_image.setImageResource(R.drawable.tab_2_n);
        daxiaobian_image.setImageResource(R.drawable.tab_3_n);
        sangzikangfu_image.setImageResource(R.drawable.tab_4_n);
        xiazikangfu_image.setImageResource(R.drawable.tab_5_n);
        xitongsezi_image.setImageResource(R.drawable.tab_6_n);
    }

    // 急停按钮
    @Click(R.id.stop_layout)
    void stopButtonClicked() {
        sendbytes = CRCHelp.CRC16("b3030a010000003b11220d0a");
        bleHelp.sendDatas(sendbytes);
        UIUtils.showToastSafe("急停b3030a010000003b11220d0a");
    }
}
