package com.jay.android.fragmentforhost;

import android.app.Activity;
import android.app.Fragment;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jay.android.fragmentforhost.Help.BLEHelp;
import com.jay.android.fragmentforhost.Help.CRCHelp;
import com.jay.android.fragmentforhost.Help.DataHelp;
import com.jay.android.fragmentforhost.Utils.HexUtils;
import com.jay.android.fragmentforhost.Utils.UIUtils;
import com.mt.ble.mtble.MTBLEMBLE;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.List;

@EFragment(R.layout.fragment_1)
public class Fragment1 extends Fragment {
    private byte[] sendbytes = null;
    private Activity activity;
    private static int qibeicisu = 0;
    private static int wantuicisu = 0;
    private static int fansencisu = 0;

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
    TextView tv_cuangti_tuibujiaodu;
    @ViewById
    TextView tv_cuangti_beibujiaodu;
    @ViewById
    TextView tv_cuangti_cisu;

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
    @ViewById // 复位
    Button btn_cuangti_reset;
    // 按钮组
    @ViewsById({R.id.btn_cuangti_qibei,
            R.id.btn_cuangti_tangping,
            R.id.btn_cuangti_xiatui,
            R.id.btn_cuangti_taitui,
            R.id.btn_cuangti_youfansen,
            R.id.btn_cuangti_zuofansen,
            R.id.btn_cuangti_zidongfansen})
    List<Button> btns_cuangti;

    private static Boolean buttonFlag[] = {true,true,true,true,true,true,true,true};
    private static Boolean flag = true; // 为按钮控制开始和暂停的状态,true为开始,false为暂停
    private static Boolean autoFlag = false; // 为自动模式互锁控制状态,true为锁,false为不锁
    private static Boolean soudongFlag = false; // 为手动模式互锁控制状态,true为锁,false为不锁

    @Click(R.id.ll_cuanti_soudong)
    void cuantiSoudongLayoutClicked() {
        if(!autoFlag) setChioceItem(0);
        else UIUtils.showToastSafe("请先解除自动模式");
    }

    @Click(R.id.ll_cuanti_zidong)
    void cuantiZidongLayoutClicked() {
        if(!soudongFlag) setChioceItem(1);
        else UIUtils.showToastSafe("请先解除手动模式");
    }

    BLEHelp bleHelp = null;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
//        bleHelp = new BLEHelp(activity, blecallback, "F4:B8:5E:E6:98:AC"); // 生产
        bleHelp = new BLEHelp(activity, blecallback, "78:A5:04:8D:18:2A"); // 开发
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
//        if(buttonFlag[0]) {
////            sendbytes = getSendDatas("b1010a010000001b11220d0a", 1, true);
//            sendbytes = CRCHelp.CRC16("b1010a010000001b11220d0a");
//            UIUtils.showToastSafe("起背开始b1010a010000001b11220d0a");
////            sendbytes = bleHelp.getSendDatas("b1010a010000001b11220d0a", 1, true);
//            btn_cuangti_qibei.setBackgroundResource(R.drawable.btn_stop);
//        } else {
////            sendbytes = getSendDatas("b1010a000000001b11220d0a", 1, true);
////            sendbytes = bleHelp.getSendDatas("b1010a000000001b11220d0a", 1, true);
//            sendbytes = CRCHelp.CRC16("b1010a000000001b11220d0a");
//            UIUtils.showToastSafe("起背暂停b1010a000000001b11220d0a");
//            btn_cuangti_qibei.setBackgroundResource(R.drawable.btn_cuangti_qibei);
//        }
//        buttonFlag[0] = !buttonFlag[0];
//        bleHelp.sendDatas(sendbytes);
        if(buttonFlag[0]){
            if(flag) bleHelp.sendDatas(DataHelp.CUANGTI_QIBEI_START_STR, DataHelp.CUANGTI_QIBEI_START);
            else bleHelp.sendDatas(DataHelp.CUANGTI_QIBEI_PAUSE_STR, DataHelp.CUANGTI_QIBEI_PAUSE);
        }
    }

    // 躺平按钮
    @Click(R.id.btn_cuangti_tangping)
    void tangpingButtonClicked() {
//        if(buttonFlag[1]) {
//            sendbytes = CRCHelp.CRC16("b1010a000100001b11220d0a");
//            UIUtils.showToastSafe("躺平开始b1010a000100001b11220d0a");
////            sendbytes = getSendDatas("b1010a000100001b11220d0a", 1, true);
//            btn_cuangti_tangping.setBackgroundResource(R.drawable.btn_stop);
//        } else {
//            sendbytes = CRCHelp.CRC16("b1010a000000001b11220d0a");
//            UIUtils.showToastSafe("躺平暂停b1010a000000001b11220d0a");
////            sendbytes = getSendDatas("b1010a000000001b11220d0a", 1, true);
//            btn_cuangti_tangping.setBackgroundResource(R.drawable.btn_cuangti_tangping);
//        }
//        buttonFlag[1] = !buttonFlag[1];
//        bleHelp.sendDatas(sendbytes);
        if(buttonFlag[1]){
            if(flag) bleHelp.sendDatas(DataHelp.CUANGTI_PINGTANG_START_STR, DataHelp.CUANGTI_PINGTANG_START);
            else bleHelp.sendDatas(DataHelp.CUANGTI_PINGTANG_PAUSE_STR, DataHelp.CUANGTI_PINGTANG_PAUSE);
        }
    }

    // 下腿按钮
    @Click(R.id.btn_cuangti_xiatui)
    void xiatuiButtonClicked() {
//        if(buttonFlag[2]) {
//            sendbytes = CRCHelp.CRC16("b1010a000000011b11220d0a");
//            UIUtils.showToastSafe("下腿开始b1010a000000011b11220d0a");
////            sendbytes = getSendDatas("b1010a000000011b11220d0a", 1, true);
//            btn_cuangti_xiatui.setBackgroundResource(R.drawable.btn_stop);
//        } else {
//            sendbytes = CRCHelp.CRC16("b1010a000000001b11220d0a");
//            UIUtils.showToastSafe("下腿暂停b1010a000000001b11220d0a");
////            sendbytes = getSendDatas("b1010a000000001b11220d0a", 1, true);
//            btn_cuangti_xiatui.setBackgroundResource(R.drawable.btn_cuangti_xiatui);
//        }
//        buttonFlag[2] = !buttonFlag[2];
//        bleHelp.sendDatas(sendbytes);
        if(buttonFlag[2]){
            if(flag) bleHelp.sendDatas(DataHelp.CUANGTI_ZETUI_START_STR, DataHelp.CUANGTI_ZETUI_START);
            else bleHelp.sendDatas(DataHelp.CUANGTI_ZETUI_PAUSE_STR, DataHelp.CUANGTI_ZETUI_PAUSE);
        }
    }

    // 抬腿按钮
    @Click(R.id.btn_cuangti_taitui)
    void taituiButtonClicked() {
//        if(buttonFlag[3]) {
//            sendbytes = CRCHelp.CRC16("b1010a000000001b11220d0a");
//            UIUtils.showToastSafe("抬腿开始b1010a000000001b11220d0a");
////            sendbytes = getSendDatas("b1010a000001001b11220d0a", 1, true);
//            btn_cuangti_taitui.setBackgroundResource(R.drawable.btn_stop);
//        } else {
//            sendbytes = CRCHelp.CRC16("b1010a000000001b11220d0a");
//            UIUtils.showToastSafe("抬腿暂停b1010a000000001b11220d0a");
////            sendbytes = getSendDatas("b1010a000000001b11220d0a", 1, true);
//            btn_cuangti_taitui.setBackgroundResource(R.drawable.btn_cuangti_taitui);
//        }
//        buttonFlag[3] = !buttonFlag[3];
//        bleHelp.sendDatas(sendbytes);
        if(buttonFlag[3]){
            if(flag) bleHelp.sendDatas(DataHelp.CUANGTI_TAITUI_START_STR, DataHelp.CUANGTI_TAITUI_START);
            else bleHelp.sendDatas(DataHelp.CUANGTI_TAITUI_PAUSE_STR, DataHelp.CUANGTI_TAITUI_PAUSE);
        }
    }

    // 右翻身按钮
    @Click(R.id.btn_cuangti_youfansen)
    void youfansenButtonClicked() {
//        if(buttonFlag[5]) {
//            sendbytes = CRCHelp.CRC16("b2020a010000002b11220d0a");
//            UIUtils.showToastSafe("右翻身开始b2020a010000002b11220d0a");
////            sendbytes = getSendDatas("b2020a010000002b11220d0a", 1, true);
//            btn_cuangti_youfansen.setBackgroundResource(R.drawable.btn_stop);
//        } else {
//            sendbytes = CRCHelp.CRC16("b2020a000000002b11220d0a");
//            UIUtils.showToastSafe("右翻身暂停b2020a000000002b11220d0a");
////            sendbytes = getSendDatas("b2020a000000002b11220d0a", 1, true);
//            btn_cuangti_youfansen.setBackgroundResource(R.drawable.btn_cuangti_youfansen);
//        }
//        buttonFlag[5] = !buttonFlag[5];
//        bleHelp.sendDatas(sendbytes);
        if(buttonFlag[4]){
            if(flag) bleHelp.sendDatas(DataHelp.CUANGTI_YOUFANSEN_START_STR, DataHelp.CUANGTI_YOUFANSEN_START);
            else bleHelp.sendDatas(DataHelp.CUANGTI_YOUFANSEN_PAUSE_STR, DataHelp.CUANGTI_YOUFANSEN_PAUSE);
        }
    }

    // 左翻身按钮
    @Click(R.id.btn_cuangti_zuofansen)
    void zuofansenButtonClicked() {
//        if(buttonFlag[4]) {
//            sendbytes = CRCHelp.CRC16("b2020a000100002b11220d0a");
//            UIUtils.showToastSafe("左翻身开始b2020a000100002b11220d0a");
////            sendbytes = getSendDatas("b2020a000100002b11220d0a", 1, true);
//            btn_cuangti_zuofansen.setBackgroundResource(R.drawable.btn_stop);
//        } else {
//            sendbytes = CRCHelp.CRC16("b2020a000000002b11220d0a");
//            UIUtils.showToastSafe("左翻身暂停b2020a000000002b11220d0a");
////            sendbytes = getSendDatas("b2020a000000002b11220d0a", 1, true);
//            btn_cuangti_zuofansen.setBackgroundResource(R.drawable.btn_cuangti_zuofansen);
//        }
//        buttonFlag[4] = !buttonFlag[4];
//        bleHelp.sendDatas(sendbytes);
        if(buttonFlag[5]){
            if(flag) bleHelp.sendDatas(DataHelp.CUANGTI_ZUOFANSEN_START_STR, DataHelp.CUANGTI_ZUOFANSEN_START);
            else bleHelp.sendDatas(DataHelp.CUANGTI_ZUOFANSEN_PAUSE_STR, DataHelp.CUANGTI_ZUOFANSEN_PAUSE);
        }
    }

    // 自动翻身按钮
    @Click(R.id.btn_cuangti_zidongfansen)
    void zidongfansenButtonClicked() {
//        if(buttonFlag[6]) {
//            sendbytes = CRCHelp.CRC16("b2020a000001002b11220d0a");
//            UIUtils.showToastSafe("自动翻身开始b2020a000001002b11220d0a");
////            sendbytes = getSendDatas("b2020a000001002b11220d0a", 1, true);
//            btn_cuangti_zidongfansen.setBackgroundResource(R.drawable.btn_off);
//        } else {
//            sendbytes = CRCHelp.CRC16("b2020a000000002b11220d0a");
//            UIUtils.showToastSafe("自动翻身暂停b2020a000001002b11220d0a");
////            sendbytes = getSendDatas("b2020a000000002b11220d0a", 1, true);
//            btn_cuangti_zidongfansen.setBackgroundResource(R.drawable.btn_on);
//        }
//        buttonFlag[6] = !buttonFlag[6];
//        bleHelp.sendDatas(sendbytes);
        if(buttonFlag[6]){
            if(flag) bleHelp.sendDatas(DataHelp.CUANGTI_FANSEN_AUTO_START_STR, DataHelp.CUANGTI_FANSEN_AUTO_START);
            else bleHelp.sendDatas(DataHelp.CUANGTI_FANSEN_AUTO_PAUSE_STR, DataHelp.CUANGTI_FANSEN_AUTO_PAUSE);
        }
    }

    // 复位按钮
    @Click(R.id.btn_cuangti_reset)
    void resetButtonClicked() {
//        sendbytes = CRCHelp.CRC16("b3030a010000003b11220d0a");
//        UIUtils.showToastSafe("复位b3030a010000003b11220d0a");
//        bleHelp.sendDatas(sendbytes);
        if(buttonFlag[6]){
            bleHelp.sendDatas(DataHelp.CUANGTI_RESET_START_STR, DataHelp.CUANGTI_RESET_START);
        }else{
            UIUtils.showToastSafe("复位尚未完成");
        }

    }

    // 设置回调方法
    private MTBLEMBLE.CallBack blecallback = new MTBLEMBLE.CallBack() {

        @Override
        public void onReviceDatas(final BluetoothGattCharacteristic data_char) {
            ReviceDatas(data_char);
        }

        @Override
        public void onReviceCMD(BluetoothGattCharacteristic data_char) {}

        @Override
        public void onDisconnect() {}
    };

    /**
     * 数据返回检查函数
     * 先判断长度为7
     * 再判断B24E开头
     * 然后送入状态检查
     * @param data_char
     */
    @Background
    public void ReviceDatas(final BluetoothGattCharacteristic data_char){
        byte[] datas = data_char.getValue();
        UIUtils.showToastSafe(HexUtils.Bytes2HexString(datas));
        if(datas.length == 8) {
            if(datas[0] == (byte)0xB2 && datas[5] == (byte)0x2B){
                checkType(datas, data_char);
            }else{
                UIUtils.showToastSafe("数据报头或报尾校验错误 ");
                UIUtils.showToastSafe("第0位是:" + datas[0] + "\n第5位是:" + datas[5]);
            }
        }else{
            UIUtils.showToastSafe("数据长度校验错误");
            UIUtils.showToastSafe(HexUtils.Bytes2HexString(datas));
        }
    }

    /**
     * 判断返回数据的类型
     * 根据第1位数据判断
     * @param datas
     * @param data_char
     */
    @Background
    public void checkType(byte[] datas, final BluetoothGattCharacteristic data_char){
        String[] str_1 = new String[]{"第1位错误","起背","躺平","折腿","抬腿","右翻身","左翻身","自动翻身","复位","急停","腿部角度","背部角度","起背次数","弯腿次数","翻身次数"};
        int btn_id = -1;
        if(datas[1] > 0 && datas[1] < 10){
            checkButtonStatus(datas, str_1[datas[1]], datas[1] - 1);
        }else if(datas[1] > 9 && datas[1] < 15){
            showData(datas, str_1[datas[1]]);
        }else{
            UIUtils.showToastSafe(str_1[0]);
        }
    }

    /**
     * 判断返回数据的状态
     * 根据第3位数据判断
     * @param datas
     * @param str_1
     * @param btn_id
     */
    @Background
    public void checkButtonStatus(byte[] datas, String str_1, int btn_id){
        String str_2 = "第3位错误";
        switch(datas[3]){
            case (byte) 0x02:
                str_2 = "执行中"; // 锁定按钮
                break;
            case (byte) 0x04:
                str_2 = "暂停";
                btn_id = -1; // 不锁定按钮
                break;
            case (byte)0x08:
                str_2 = "完成";
                btn_id = -1; // 不锁定按钮
                break;
        }
        syncButton(btn_id); // 同步按钮状态
        UIUtils.showToastSafe(str_1 + str_2);
    }

    /**
     * 同步按钮的状态
     * buttonFlag表示6个按钮的状态, false表示锁,true表示不锁
     * @param btn_id 第几个按钮需要设置为disable, -1表示全部设置为disable
     */
    @UiThread
    public void syncButton(int btn_id){
        String[] name = new String[] { "qibei", "tangping", "xiatui", "taitui", "youfansen", "zuofansen", "zidongfansen" };
        try {
            if(btn_id == -1){
                flag = true;
                buttonFlag = new Boolean[]{true,true,true,true,true,true,true,true};
                for (int i = 0; i < 6; i++) {
                    btns_cuangti.get(i).setBackgroundResource((int) R.drawable.class.getDeclaredField("btn_cuangti_" + name[i]).get(R.drawable.class));
                }
                btn_cuangti_zidongfansen.setBackgroundResource(R.drawable.btn_off);
                soudongFlag = false;autoFlag = false;
            }else{
                flag = false;
                buttonFlag = new Boolean[]{false,false,false,false,false,false,false,false};
                buttonFlag[btn_id] = true;
                for (int i = 0; i < 8; i++) {
                    if(i != btn_id && i != 6 && i != 7) {
                        btns_cuangti.get(i).setBackgroundResource((int) R.drawable.class.getDeclaredField("btn_cuangti_" + name[i] + "_disable").get(R.drawable.class));
                    }else if(btn_id == 7){
                        setChioceItem(0);
                        soudongFlag = true;autoFlag = false;
                        btns_cuangti.get(btn_id).setBackgroundResource(R.drawable.btn_ing);
                    }else if(btn_id == 6){
                        setChioceItem(1);
                        btn_cuangti_zidongfansen.setBackgroundResource(R.drawable.btn_on);
                        soudongFlag = false;autoFlag = true;
                    }else{
                        setChioceItem(0);
                        soudongFlag = true;autoFlag = false;
                        btns_cuangti.get(btn_id).setBackgroundResource(R.drawable.btn_ing);
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            Log.i("Fragment1", e.toString());
            return;
        }
    }

    @UiThread
    public void showData(byte[] datas, String str_1){
        UIUtils.showToastSafe(str_1 + datas[3]);
        switch(datas[1]){
            case (byte) 0x0a:
                tv_cuangti_tuibujiaodu.setText(datas[3] + "°");
                break;
            case (byte) 0x0b:
                tv_cuangti_beibujiaodu.setText(datas[3] + "°");
                break;
            case (byte) 0x0c:
                qibeicisu = datas[3];
                break;
            case (byte) 0x0d:
                wantuicisu = datas[3];
                break;
            case (byte) 0x0e:
                fansencisu = datas[3];
                break;
        }
        tv_cuangti_cisu.setText("今天          起背次数:" + qibeicisu + "          弯腿次数:" + wantuicisu + "          翻身次数:" + fansencisu);
    }
}
