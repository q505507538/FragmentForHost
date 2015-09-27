package com.jay.android.fragmentforhost;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jay.android.fragmentforhost.Help.BLEHelp;
import com.jay.android.fragmentforhost.Help.CRCHelp;
import com.jay.android.fragmentforhost.Help.DataHelp;
import com.jay.android.fragmentforhost.Utils.HexUtils;
import com.jay.android.fragmentforhost.Utils.UIUtils;
import com.mt.ble.mtble.MTBLEMBLE;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

@Fullscreen
@EActivity(R.layout.activity_main)
@WindowFeature({ Window.FEATURE_NO_TITLE, Window.FEATURE_INDETERMINATE_PROGRESS })
public class MainActivity extends BaseActivity {
    private byte[] sendbytes = null;

    private static Integer currentFg = 0;
    private static Boolean[] stopFlag = {false,false,false,false,false};
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
    @ViewById // 急停图片
    ImageView stop_image;

    BLEHelp bleHelp = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bleHelp = new BLEHelp(MainActivity.this, blecallback, DataHelp.mac[0]);
//        if(!DataHelp.isConnected) Toast.makeText(this, "连接成功", Toast.LENGTH_SHORT).show();
//        DataHelp.isConnected = true;
    }

    @Override
    protected void initView() {}

    @Override
    protected void initData() {}

    @Click(R.id.cuangti_layout)
    void cuangtiLayoutClicked() {
        bleHelp = new BLEHelp(MainActivity.this, blecallback, DataHelp.mac[0]);
        currentFg = 0;
        setChioceItem(fg1);
        cuangti_image.setImageResource(R.drawable.tab_2_p);
        if(!stopFlag[0]) stop_image.setImageResource(R.drawable.tab_8_stop);
        else stop_image.setImageResource(R.drawable.tab_8_start);
    }

    @Click(R.id.daxiaobian_layout)
    void daxiaobianLayoutClicked() {
        bleHelp = new BLEHelp(MainActivity.this, blecallback, DataHelp.mac[1]);
        currentFg = 1;
        setChioceItem(fg2);
        daxiaobian_image.setImageResource(R.drawable.tab_3_p);
        if(!stopFlag[1]) stop_image.setImageResource(R.drawable.tab_8_stop);
        else stop_image.setImageResource(R.drawable.tab_8_start);
    }

    @Click(R.id.sangzikangfu_layout)
    void sangzikangfuLayoutClicked() {
        bleHelp = new BLEHelp(MainActivity.this, blecallback, DataHelp.mac[2]);
        currentFg = 2;
        setChioceItem(fg3);
        sangzikangfu_image.setImageResource(R.drawable.tab_4_p);
    }

    @Click(R.id.xiazikangfu_layout)
    void xiazikangfuLayoutClicked() {
        bleHelp = new BLEHelp(MainActivity.this, blecallback, DataHelp.mac[3]);
        currentFg = 3;
        setChioceItem(fg4);
        xiazikangfu_image.setImageResource(R.drawable.tab_5_p);
    }

    @Click(R.id.xitongsezi_layout)
    void xitongseziLayoutClicked() {
        bleHelp = new BLEHelp(MainActivity.this, blecallback, DataHelp.mac[4]);
        currentFg = 4;
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
        if(currentFg == 0){
            bleHelp.sendDatas(DataHelp.CUANGTI_STOP_START_STR, DataHelp.CUANGTI_STOP_START);
        }else if(currentFg == 1){
            if(!stopFlag[1]) {
                bleHelp.sendDatas(DataHelp.DAXIAOBIAN_TINGZIMOSI_STR, DataHelp.DAXIAOBIAN_TINGZIMOSI);
                stop_image.setImageResource(R.drawable.tab_8_start);
            }else{
                bleHelp.sendDatas(DataHelp.DAXIAOBIAN_TINGZIJIECU_STR, DataHelp.DAXIAOBIAN_TINGZIJIECU);
                stop_image.setImageResource(R.drawable.tab_8_stop);
            }
            stopFlag[1] = !stopFlag[1];
        }

    }

    // 设置回调方法
    private MTBLEMBLE.CallBack blecallback = new MTBLEMBLE.CallBack() {

        @Override
        public void onReviceDatas(final BluetoothGattCharacteristic data_char) {
//            disDatas(data_char);
        }

        @Override
        public void onReviceCMD(BluetoothGattCharacteristic data_char) {}

        @Override
        public void onDisconnect() {

        }
    };

    // 显示接收数据和命令
    private Handler handl = new Handler();
    private boolean disDatas(final BluetoothGattCharacteristic data_char) {
        handl.post(new Runnable() {
            @Override
            public void run() {
                switch (1) {
                    case 0: // String
                        UIUtils.showToastSafe(data_char.getStringValue(0));
                        break;
                    case 1: // 16进制
                        UIUtils.showToastSafe(HexUtils.Bytes2HexString(data_char.getValue()));
                        break;
                    case 2: // 10进制
                        int count = 0;
                        byte[] tmp_byte = data_char.getValue();
                        for (int i = 0; i < tmp_byte.length; i++) {
                            count *= 256;
                            count += (tmp_byte[tmp_byte.length - 1 - i] & 0xFF);
                        }
                        UIUtils.showToastSafe("" + count);
                        break;
                }
            }
        });
        return true;
    }
}
