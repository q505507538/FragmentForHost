package com.jay.android.fragmentforhost;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mt.ble.mtble.MTBLEMBLE;
import com.mt.help.LogText;
import com.sdk.ble.MTBLEManager;
import com.sdk.help.Helpful;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_1)
public class Fragment1 extends Fragment {

    private MTBLEManager mMTBLEManager;
    private MTBLEMBLE mBle;
    private Handler handl = new Handler();
    private byte[] sendbytes = null;
    private Activity activity;

    @ViewById
    LinearLayout ll_cuanti_soudong;
    @ViewById
    LinearLayout ll_cuanti_zidong;
    @ViewById
    LinearLayout ll_cuanti_soudong_content;
    @ViewById
    LinearLayout ll_cuanti_zidong_content;

    @ViewById
    TextView tv_cuanti_soudong;
    @ViewById
    TextView tv_cuanti_zidong;

    @ViewById
    View soudong_selected;
    @ViewById
    View zidong_selected;

    @ViewById // 起背按钮
            Button btn_cuangti_qibei;
    @ViewById // 躺平按钮
            Button btn_cuangti_tangping;
    @ViewById // 下腿按钮
            Button btn_cuangti_xiatui;
    @ViewById // 抬腿按钮
            Button btn_cuangti_taitui;
    @ViewById // 左翻身
            Button btn_cuangti_zuofansen;
    @ViewById // 右翻身
            Button btn_cuangti_youfansen;
    @ViewById // 自动翻身
            Button btn_cuangti_zidongfansen;

    Boolean buttonFlag[] = {true,true,true,true,true,true,true};

    @Click(R.id.ll_cuanti_soudong)
    void cuantiSoudongLayoutClicked() { setChioceItem(0); }

    @Click(R.id.ll_cuanti_zidong)
    void cuantiZidongLayoutClicked() {
        setChioceItem(1);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        initBLE();
    }


    // 初始化BLE
//    private static final String mac = "F4:B8:5E:E6:98:AC";
    private static final String mac = "78:A5:04:8D:18:2A";

    private void initBLE() {
        if (android.os.Build.VERSION.SDK_INT < 18) {
            Toast.makeText(activity, "你out了，系统尽然还没有到android 4.3", Toast.LENGTH_LONG).show();
            return;
        }
        mMTBLEManager = MTBLEManager.getInstance();
        mMTBLEManager.init(activity);
        mBle = new MTBLEMBLE(activity, mMTBLEManager.mBluetoothManager, mMTBLEManager.mBluetoothAdapter);

        mBle.setCallback(blecallback);

        new connectThread().start();
    }

    // 显示接收数据和命令
    private boolean disDatas(final BluetoothGattCharacteristic data_char) {

        Toast.makeText(activity, "正在接收数据", Toast.LENGTH_SHORT).show();

        handl.post(new Runnable() {
            @Override
            public void run() {
                switch (1) {
                    case 0: // String
                        Toast.makeText(activity, data_char.getStringValue(0), Toast.LENGTH_SHORT).show();
                        break;
                    case 1: // 16进制
                        Toast.makeText(activity, Helpful.MYBytearrayToString(data_char.getValue()), Toast.LENGTH_SHORT).show();
                        break;
                    case 2: // 10进制
                        int count = 0;
                        byte[] tmp_byte = data_char.getValue();
                        for (int i = 0; i < tmp_byte.length; i++) {
                            count *= 256;
                            count += (tmp_byte[tmp_byte.length - 1 - i] & 0xFF);
                        }
                        Toast.makeText(activity, "" + count, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        return true;
    }

    // 获取发送数据
    private byte[] getSendDatas(String tmp_str, int type, boolean dis_flag) {
        byte[] tmp_byte = null;
        byte[] write_msg_byte = null;

        switch (type) {
            case 0: // 字符串类型
                if (0 == tmp_str.length())
                    return null;

                write_msg_byte = tmp_str.getBytes();
                break;

            case 1: // 十六进制类型
                if (0 == tmp_str.length())
                    return null;

                tmp_byte = tmp_str.getBytes();
                write_msg_byte = new byte[tmp_byte.length / 2 + tmp_byte.length % 2];
                for (int i = 0; i < tmp_byte.length; i++) {
                    if ((tmp_byte[i] <= '9') && (tmp_byte[i] >= '0')) {
                        if (0 == i % 2)
                            write_msg_byte[i / 2] = (byte) (((tmp_byte[i] - '0') * 16) & 0xFF);
                        else
                            write_msg_byte[i / 2] |= (byte) ((tmp_byte[i] - '0') & 0xFF);
                    } else {
                        if (0 == i % 2)
                            write_msg_byte[i / 2] = (byte) (((tmp_byte[i] - 'a' + 10) * 16) & 0xFF);
                        else
                            write_msg_byte[i / 2] |= (byte) ((tmp_byte[i] - 'a' + 10) & 0xFF);
                    }
                }
                break;

            case 2: // 十进制类型
                if (0 == tmp_str.length())
                    return null;

                int data_int = Integer.parseInt(tmp_str);
                int byte_size = 0;
                for (byte_size = 0; data_int != 0; byte_size++) { // 计算占用字节数
                    data_int /= 256;
                }
                write_msg_byte = new byte[byte_size];

                data_int = Integer.parseInt(tmp_str);
                for (int i = 0; i < byte_size; i++) { // 转换
                    write_msg_byte[i] = (byte) (0xFF & (data_int % 256));
                    data_int /= 256;
                }

                break;
        }

        if (0 == tmp_str.length())
            return null;
        // 显示
        if (dis_flag) {
            Toast.makeText(activity, "发送" + tmp_str + "成功", Toast.LENGTH_SHORT).show();
        }

        return write_msg_byte;
    }

    // 设置回调方法
    private MTBLEMBLE.CallBack blecallback = new MTBLEMBLE.CallBack() {

        @Override
        public void onReviceDatas(final BluetoothGattCharacteristic data_char) {
            disDatas(data_char);
        }

        @Override
        public void onReviceCMD(BluetoothGattCharacteristic data_char) {
            disDatas(data_char);
        }

        @Override
        public void onDisconnect() {
            handl.post(new Runnable() {

                @Override
                public void run() {
                    if (!activity.isDestroyed()) {
                        Toast.makeText(activity, "断开连接，正在自动重连", Toast.LENGTH_SHORT).show();
                        if (mBle.isConnected()) {
                            return;
                        } else {
                            new connectThread().start();
                        }
                    }
                }
            });
        }
    };


    // 建立连接线程
//    private ProgressDialog pd;
    private class connectThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                // 创建一个ProgressDialog框, 类似于loading作用
//                handl.post(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        pd = ProgressDialog.show(MainActivity.this, "", "正在连接", true, false);
//                    }
//
//                });

                // 开始连接
                if (!mBle.connect(mac, 10000, 2)) {
                    mBle.disConnect();
                    handl.post(new Runnable() {

                        @Override
                        public void run() {
//                            pd.dismiss();
                            Toast.makeText(activity, "连接失败", Toast.LENGTH_LONG).show();
                        }

                    });
                }else{
                    handl.post(new Runnable() {

                        @Override
                        public void run() {
//                            pd.dismiss();
                            Toast.makeText(activity, "连接成功", Toast.LENGTH_LONG).show();
                        }

                    });
                }

            } catch (Exception e) {
                LogText.writeStr("MTBeacon1Set AsyDataThread->" + e.toString());
            }

        }
    }

    // 发送数据
    private boolean sendDatas(byte[] value) {
        if (value == null) {
            return false;
        }
        mBle.sendData(value);
        return true;
    }

    // 滑块选择处理(手动,自动)
    public void setChioceItem(int index) {
        switch (index) {
            case 0:
                tv_cuanti_soudong.setTextColor(0xff2960dc);
                tv_cuanti_zidong.setTextColor(0xff898989);
                soudong_selected.setBackgroundColor(0xff2960dc);
                zidong_selected.setBackgroundColor(0xffffffff);
                ll_cuanti_soudong_content.setVisibility(View.VISIBLE);
                ll_cuanti_zidong_content.setVisibility(View.GONE);
                break;
            case 1:
                tv_cuanti_soudong.setTextColor(0xff898989);
                tv_cuanti_zidong.setTextColor(0xff2960dc);
                soudong_selected.setBackgroundColor(0xffffffff);
                zidong_selected.setBackgroundColor(0xff2960dc);
                ll_cuanti_soudong_content.setVisibility(View.GONE);
                ll_cuanti_zidong_content.setVisibility(View.VISIBLE);
                break;
        }
    }

    // 起背按钮
    @Click(R.id.btn_cuangti_qibei)
    void qibeiButtonClicked() {
        if(buttonFlag[0]) {
            sendbytes = getSendDatas("b1010a010000001b11220d0a", 1, true);
            btn_cuangti_qibei.setBackgroundResource(R.drawable.btn_stop);
        } else {
            sendbytes = getSendDatas("b1010a000000001b11220d0a", 1, true);
            btn_cuangti_qibei.setBackgroundResource(R.drawable.btn_cuangti_qibei);
        }
        buttonFlag[0] = !buttonFlag[0];
        sendDatas(sendbytes);
    }

    // 躺平按钮
    @Click(R.id.btn_cuangti_tangping)
    void tangpingButtonClicked() {
        if(buttonFlag[1]) {
            sendbytes = getSendDatas("b1010a000100001b11220d0a", 1, true);
            btn_cuangti_tangping.setBackgroundResource(R.drawable.btn_stop);
        } else {
            sendbytes = getSendDatas("b1010a000000001b11220d0a", 1, true);
            btn_cuangti_tangping.setBackgroundResource(R.drawable.btn_cuangti_tangping);
        }
        buttonFlag[1] = !buttonFlag[1];
        sendDatas(sendbytes);
    }

    // 下腿按钮
    @Click(R.id.btn_cuangti_xiatui)
    void xiatuiButtonClicked() {
        if(buttonFlag[2]) {
            sendbytes = getSendDatas("b1010a000000011b11220d0a", 1, true);
            btn_cuangti_xiatui.setBackgroundResource(R.drawable.btn_stop);
        } else {
            sendbytes = getSendDatas("b1010a000000001b11220d0a", 1, true);
            btn_cuangti_xiatui.setBackgroundResource(R.drawable.btn_cuangti_xiatui);
        }
        buttonFlag[2] = !buttonFlag[2];
        sendDatas(sendbytes);
    }

    // 抬腿按钮
    @Click(R.id.btn_cuangti_taitui)
    void taituiButtonClicked() {
        if(buttonFlag[3]) {
            sendbytes = getSendDatas("b1010a000001001b11220d0a", 1, true);
            btn_cuangti_taitui.setBackgroundResource(R.drawable.btn_stop);
        } else {
            sendbytes = getSendDatas("b1010a000000001b11220d0a", 1, true);
            btn_cuangti_taitui.setBackgroundResource(R.drawable.btn_cuangti_taitui);
        }
        buttonFlag[3] = !buttonFlag[3];
        sendDatas(sendbytes);
    }

    // 左翻身按钮
    @Click(R.id.btn_cuangti_zuofansen)
    void zuofansenButtonClicked() {
        if(buttonFlag[4]) {
            sendbytes = getSendDatas("b2020a000100002b11220d0a", 1, true);
            btn_cuangti_zuofansen.setBackgroundResource(R.drawable.btn_stop);
        } else {
            sendbytes = getSendDatas("b2020a000000002b11220d0a", 1, true);
            btn_cuangti_zuofansen.setBackgroundResource(R.drawable.btn_cuangti_zuofansen);
        }
        buttonFlag[4] = !buttonFlag[4];
        sendDatas(sendbytes);
    }

    // 右翻身按钮
    @Click(R.id.btn_cuangti_youfansen)
    void youfansenButtonClicked() {
        if(buttonFlag[5]) {
            sendbytes = getSendDatas("b2020a010000002b11220d0a", 1, true);
            btn_cuangti_youfansen.setBackgroundResource(R.drawable.btn_stop);
        } else {
            sendbytes = getSendDatas("b2020a000000002b11220d0a", 1, true);
            btn_cuangti_youfansen.setBackgroundResource(R.drawable.btn_cuangti_youfansen);
        }
        buttonFlag[5] = !buttonFlag[5];
        sendDatas(sendbytes);
    }

    // 自动翻身按钮
    @Click(R.id.btn_cuangti_zidongfansen)
    void zidongfansenButtonClicked() {
        if(buttonFlag[6]) {
            sendbytes = getSendDatas("b2020a000001002b11220d0a", 1, true);
            btn_cuangti_zidongfansen.setBackgroundResource(R.drawable.btn_off);
        } else {
            sendbytes = getSendDatas("b2020a000000002b11220d0a", 1, true);
            btn_cuangti_zidongfansen.setBackgroundResource(R.drawable.btn_on);
        }
        buttonFlag[6] = !buttonFlag[6];
        sendDatas(sendbytes);
    }
}
