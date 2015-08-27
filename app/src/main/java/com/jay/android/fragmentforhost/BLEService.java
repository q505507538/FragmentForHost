package com.jay.android.fragmentforhost;

import android.app.Service;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.jay.android.fragmentforhost.Utils.UIUtils;
import com.mt.ble.mtble.MTBLEMBLE;
import com.mt.help.LogText;
import com.sdk.ble.MTBLEManager;
import com.sdk.help.Helpful;

public class BLEService extends Service {
    private MTBLEManager mMTBLEManager;
    private MTBLEMBLE mBle;
    private Handler handl = new Handler();
    private Context context;

    public BLEService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // 前台Activity调用startService时，该方法自动执行
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        context = getApplicationContext();
        initBLE();
        return super.onStartCommand(intent, flags, startId);
    }

    //    初始化BLE
//    private static final String mac = "F4:B8:5E:E6:98:AC"; // 冯明敏
//    private static final String mac = "F4:B8:5E:E6:8C:1F"; // 吴海滨
    private static final String mac = "78:A5:04:8D:18:2A";

    private void initBLE() {
        if (android.os.Build.VERSION.SDK_INT < 18) {
            Toast.makeText(context, "你out了，系统尽然还没有到android 4.3", Toast.LENGTH_LONG).show();
            return;
        }
        mMTBLEManager = MTBLEManager.getInstance();
        mMTBLEManager.init(context);
        mBle = new MTBLEMBLE(context, mMTBLEManager.mBluetoothManager, mMTBLEManager.mBluetoothAdapter);

        mBle.setCallback(blecallback);
        new connectThread().start();
    }

    // 显示接收数据和命令
    private boolean disDatas(final BluetoothGattCharacteristic data_char) {

        handl.post(new Runnable() {
            @Override
            public void run() {
                switch (1) {
                    case 0: // String
                        UIUtils.showToastSafe(data_char.getStringValue(0));
                        break;
                    case 1: // 16进制
                        UIUtils.showToastSafe(Helpful.MYBytearrayToString(data_char.getValue()));
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
                    default:
                        break;
                }
            }
        });
        return true;
    }

    // 获取发送数据
    public byte[]  getSendDatas(String tmp_str, int type, boolean dis_flag) {
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
            UIUtils.showToastSafe("发送" + tmp_str + "成功");
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
        public void onDisconnect() {}
    };


    // 建立连接线程
    private class connectThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                // 开始连接
                if (!mBle.connect(mac, 10000, 2)) {
                    mBle.disConnect();
                    handl.post(new Runnable() {

                        @Override
                        public void run() {
                            UIUtils.showToastSafe("连接失败");
                        }

                    });
                }else{
                    handl.post(new Runnable() {

                        @Override
                        public void run() {
                            UIUtils.showToastSafe("连接成功");
                        }

                    });
                }

            } catch (Exception e) {
                LogText.writeStr("MTBeacon1Set AsyDataThread->" + e.toString());
            }

        }
    }

    // 发送数据
    public boolean sendDatas(byte[] value) {
        if (value == null) {
            return false;
        }
        mBle.sendData(value);
        return true;
    }
}
