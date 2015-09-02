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
import com.jay.android.fragmentforhost.Utils.SharedPreferencesUtils;
import com.jay.android.fragmentforhost.Utils.UIUtils;
import com.mt.ble.mtble.MTBLEMBLE;
import com.sdk.help.Helpful;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EFragment(R.layout.fragment_2)
public class Fragment2 extends Fragment {
    private byte[] sendbytes = null;
    private Activity activity;

    @ViewById
    LinearLayout ll_daxiaobian_soudong;
    @ViewById
    LinearLayout ll_daxiaobian_zidong;
    @ViewById
    LinearLayout ll_daxiaobian_sezi;

    @ViewById
    LinearLayout ll_daxiaobian_soudong_content;
    @ViewById
    LinearLayout ll_daxiaobian_zidong_content;
    @ViewById
    LinearLayout ll_daxiaobian_sezi_content;

    @ViewById
    TextView tv_daxiaobian_soudong;
    @ViewById
    TextView tv_daxiaobian_zidong;
    @ViewById
    TextView tv_daxiaobian_sezi;

    @ViewById
    View soudong_selected;
    @ViewById
    View zidong_selected;
    @ViewById
    View sezi_selected;

    @ViewById   // 大便处理按钮
    Button btn_daxiaobian_dabianculi;
    @ViewById   // 小便处理按钮
    Button btn_daxiaobian_xiaobianculi;
    @ViewById   // 清洗臀部按钮
    Button btn_daxiaobian_qingxitunbu;
    @ViewById   // 烘干臀部按钮
    Button btn_daxiaobian_honggantunbu;
    @ViewById   // 冲洗便垫按钮
    Button btn_daxiaobian_congxibiandian;
    @ViewById   // 消毒杀菌按钮
    Button btn_daxiaobian_xiaodusajun;

    @ViewsById({R.id.btn_daxiaobian_dabianculi,
                R.id.btn_daxiaobian_xiaobianculi,
                R.id.btn_daxiaobian_qingxitunbu,
                R.id.btn_daxiaobian_honggantunbu,
                R.id.btn_daxiaobian_congxibiandian,
                R.id.btn_daxiaobian_xiaodusajun})
    List<Button> btns_daxiaobian;

    @ViewById   // 换气除臭按钮
    Button btn_daxiaobian_huanqicucou;
    @ViewById   // 自动处理大小便按钮
    Button btn_daxiaobian_zidongculidaxiaobian;
    @ViewById   // 自动换气除臭按钮
    Button btn_daxiaobian_zidonghuanqicucou;
    @ViewById   // 尿失禁模式按钮
    Button btn_daxiaobian_niaosijinmosi;
    @ViewById   // 女性模式按钮
    Button btn_daxiaobian_nvxingmosi;
    @ViewById   // 污水桶锁定按钮
    Button btn_daxiaobian_wusuitongsuiding;
    @ViewById   // 排水开关按钮
    Button btn_daxiaobian_paisuikaiguan;

    Boolean buttonFlag[] = {true, true, true, true, true, true, true, true, true, true, true, true, true};
    Boolean flag = true;

    @Click(R.id.ll_daxiaobian_soudong)
    void daxiaobianSoudongLayoutClicked() {
        setChioceItem(0);
    }

    @Click(R.id.ll_daxiaobian_zidong)
    void daxiaobianZidongLayoutClicked() {
        setChioceItem(1);
    }

    @Click(R.id.ll_daxiaobian_sezi)
    void daxiaobianSeziLayoutClicked() {
        setChioceItem(2);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        bleHelp = new BLEHelp(activity, blecallback);
    }

    BLEHelp bleHelp = null;
    @Override
    public void onStart() {
        super.onStart();
        initSettings();
    }

    private static Map mosi = new HashMap() {{
        put("daxiaobianculi_auto", false);// 自动处理大小便模式按钮
        put("huanqicucou_auto", false);   // 自动换气除臭按钮
        put("niaosijinmosi", false);      // 尿失禁模式按钮
        put("nvxingmosi", false);         // 女性模式按钮
        put("wusuitongsuiding", false);   // 污水桶锁定按钮
        put("paisuikaiguan", false);      // 排水开关按钮
    }};
    private void initSettings() {
        checkButton("niaosijinmosi", btn_daxiaobian_niaosijinmosi);
        checkButton("nvxingmosi", btn_daxiaobian_nvxingmosi);
        checkButton("wusuitongsuiding", btn_daxiaobian_wusuitongsuiding);
        checkButton("paisuikaiguan", btn_daxiaobian_paisuikaiguan);
    }

    // 从配置文件获取按钮状态
    private void checkButton(String key, Button btn) {
        mosi.put(key, (Boolean) SharedPreferencesUtils.getParam(getActivity().getApplicationContext(), key, false));
        if (mosi.get(key) != null) {
            if ((Boolean)mosi.get(key)) btn.setBackgroundResource(R.drawable.btn_on);
            else btn.setBackgroundResource(R.drawable.btn_off);
        } else {
            SharedPreferencesUtils.setParam(getActivity().getApplicationContext(), key, false);
        }
    }

    // 滑块选择处理(手动,自动,设置)
    public void setChioceItem(int index) {
        switch (index) {
            case 0:
                tv_daxiaobian_soudong.setTextColor(0xff2960dc);
                tv_daxiaobian_zidong.setTextColor(0xff898989);
                tv_daxiaobian_sezi.setTextColor(0xff898989);

                soudong_selected.setBackgroundColor(0xff2960dc);
                zidong_selected.setBackgroundColor(0xffffffff);
                sezi_selected.setBackgroundColor(0xffffffff);

                ll_daxiaobian_soudong_content.setVisibility(View.VISIBLE);
                ll_daxiaobian_zidong_content.setVisibility(View.GONE);
                ll_daxiaobian_sezi_content.setVisibility(View.GONE);
                break;
            case 1:
                tv_daxiaobian_soudong.setTextColor(0xff898989);
                tv_daxiaobian_zidong.setTextColor(0xff2960dc);
                tv_daxiaobian_sezi.setTextColor(0xff898989);

                soudong_selected.setBackgroundColor(0xffffffff);
                zidong_selected.setBackgroundColor(0xff2960dc);
                sezi_selected.setBackgroundColor(0xffffffff);

                ll_daxiaobian_soudong_content.setVisibility(View.GONE);
                ll_daxiaobian_zidong_content.setVisibility(View.VISIBLE);
                ll_daxiaobian_sezi_content.setVisibility(View.GONE);
                break;
            case 2:
                tv_daxiaobian_soudong.setTextColor(0xff898989);
                tv_daxiaobian_zidong.setTextColor(0xff898989);
                tv_daxiaobian_sezi.setTextColor(0xff2960dc);

                soudong_selected.setBackgroundColor(0xffffffff);
                zidong_selected.setBackgroundColor(0xffffffff);
                sezi_selected.setBackgroundColor(0xff2960dc);

                ll_daxiaobian_soudong_content.setVisibility(View.GONE);
                ll_daxiaobian_zidong_content.setVisibility(View.GONE);
                ll_daxiaobian_sezi_content.setVisibility(View.VISIBLE);
                break;
        }
    }

    // 大便处理按钮
    @Click(R.id.btn_daxiaobian_dabianculi)
    void dabianculiButtonClicked() {
        if(flag){
            send(DataHelp.DAXIAOBIAN_DABIANMOSI_STR, DataHelp.DAXIAOBIAN_DABIANMOSI);
        }
    }

    // 小便处理按钮
    @Click(R.id.btn_daxiaobian_xiaobianculi)
    void xiaobianculiButtonClicked() {
        if(flag){
            if((Boolean)mosi.get("niaosijinmosi")){
                send(DataHelp.DAXIAOBIAN_NIAOSIJINMOSI_STR, DataHelp.DAXIAOBIAN_NIAOSIJINMOSI);
            }else if((Boolean)mosi.get("nvxingmosi")){
                send(DataHelp.DAXIAOBIAN_NVXINGXIAOBIANMOSI_STR, DataHelp.DAXIAOBIAN_NVXINGXIAOBIANMOSI);
            }else{
                send(DataHelp.DAXIAOBIAN_NANXINGXIAOBIANMOSI_STR, DataHelp.DAXIAOBIAN_NANXINGXIAOBIANMOSI);
            }
        }
    }

    // 清洗臀部按钮
    @Click(R.id.btn_daxiaobian_qingxitunbu)
    void qingxitunbuButtonClicked() {
        if(flag){
            send(DataHelp.DAXIAOBIAN_CONGXITUNBU_STR, DataHelp.DAXIAOBIAN_CONGXITUNBU);
        }
    }

    // 烘干臀部按钮
    @Click(R.id.btn_daxiaobian_honggantunbu)
    void honggantunbuButtonClicked() {
        if(flag){
            send(DataHelp.DAXIAOBIAN_TONGFENGGANZAO_STR, DataHelp.DAXIAOBIAN_TONGFENGGANZAO);
        }
    }

    // 冲洗便垫按钮
    @Click(R.id.btn_daxiaobian_congxibiandian)
    void congxibiandianButtonClicked() {
        if(flag){
            send(DataHelp.DAXIAOBIAN_CONGXIBIANDIAN_STR, DataHelp.DAXIAOBIAN_CONGXIBIANDIAN);
        }
    }

    // 消毒杀菌按钮
    @Click(R.id.btn_daxiaobian_xiaodusajun)
    void xiaodusajunButtonClicked() {
        if(flag){
            send(DataHelp.DAXIAOBIAN_QINGJIESAJUNMOSI_STR, DataHelp.DAXIAOBIAN_QINGJIESAJUNMOSI);
        }
    }

    // 换气除臭按钮
    @Click(R.id.btn_daxiaobian_huanqicucou)
    void huanqicucouButtonClicked() {
        if (buttonFlag[6]) btn_daxiaobian_huanqicucou.setBackgroundResource(R.drawable.btn_stop);
        else btn_daxiaobian_huanqicucou.setBackgroundResource(R.drawable.btn_daxiaobian_huanqicucou);
        buttonFlag[6] = !buttonFlag[6];
    }

    // 自动处理大小便按钮
    @Click(R.id.btn_daxiaobian_zidongculidaxiaobian)
    void zidongculidaxiaobianButtonClicked() {
        setButton("daxiaobianculi_auto", btn_daxiaobian_zidongculidaxiaobian);
    }

    // 自动换气除臭按钮
    @Click(R.id.btn_daxiaobian_zidonghuanqicucou)
    void zidonghuanqicucouButtonClicked() {
        setButton("huanqicucou_auto", btn_daxiaobian_zidonghuanqicucou);
    }

    // 尿失禁模式按钮
    @Click(R.id.btn_daxiaobian_niaosijinmosi)
    void niaosijinmosiButtonClicked() {
        setButton("niaosijinmosi", btn_daxiaobian_niaosijinmosi);
    }

    // 女性模式按钮
    @Click(R.id.btn_daxiaobian_nvxingmosi)
    void nvxingmosiButtonClicked() {
        setButton("nvxingmosi", btn_daxiaobian_nvxingmosi);
    }

    // 污水桶锁定按钮
    @Click(R.id.btn_daxiaobian_wusuitongsuiding)
    void wusuitongsuidingButtonClicked() {
        setButton("wusuitongsuiding", btn_daxiaobian_wusuitongsuiding);
    }

    // 排水开关按钮
    @Click(R.id.btn_daxiaobian_paisuikaiguan)
    void paisuikaiguanButtonClicked() {
        setButton("paisuikaiguan", btn_daxiaobian_paisuikaiguan);
    }

    // 将按钮状态设置到配置文件中
    private void setButton(String key, Button btn) {
        if ((Boolean)mosi.get(key)) btn.setBackgroundResource(R.drawable.btn_off);
        else btn.setBackgroundResource(R.drawable.btn_on);
        mosi.put(key, !(Boolean)mosi.get(key));
        SharedPreferencesUtils.setParam(getActivity().getApplicationContext(), key, mosi.get(key));
    }

    // 发送命令
    private void send(String hint, byte[] bytes){
        bleHelp.sendDatas(bytes);
        UIUtils.showToastSafe(hint + HexUtils.Bytes2HexString(bytes));
    }

    @UiThread
    public void syncButton(int j){
        String[] name = new String[] { "dabianculi", "xiaobianculi", "qingxitunbu", "honggantunbu", "congxibiandian", "xiaodusajun" };
        try {
            if(!flag) {
                for (int i = 0; i < 6; i++) {
                    if(i != j) {
                        btns_daxiaobian.get(i).setBackgroundResource((int) R.drawable.class.getDeclaredField("btn_daxiaobian_" + name[i] + "_disable").get(R.drawable.class));
                    }
                }
                btns_daxiaobian.get(j).setBackgroundResource(R.drawable.btn_ing);
            }else{
                for (int i = 0; i < 6; i++) {
                    btns_daxiaobian.get(i).setBackgroundResource((int) R.drawable.class.getDeclaredField("btn_daxiaobian_" + name[i]).get(R.drawable.class));
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            Log.i("Fragment2", e.toString());
            return;
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

    @Background
    public void ReviceDatas(final BluetoothGattCharacteristic data_char){
//        String str = HexUtils.Bytes2HexString(data_char.getValue());
//        byte[] datas = HexUtils.HexString2Bytes(str);
        byte[] datas = data_char.getValue();
        if(datas.length == 7) {
//            UIUtils.showToastSafe(datas[0]+","+datas[1]+","+datas[2]+","+datas[3]+","+datas[4]+","+datas[5]+","+datas[6]);
//            UIUtils.showToastSafe("长度等于7");
//            UIUtils.showToastSafe(str);
            if(datas[0] == (byte)0xB2 && datas[1] == (byte)0x4E){
//                UIUtils.showToastSafe("B24E开头");
                checkType(datas, data_char);
            }else{
                UIUtils.showToastSafe("数据头部校验错误 ");
                UIUtils.showToastSafe("第1位是:" + datas[0] + "\n第2位是:" + datas[1]);
            }
        }else{
            UIUtils.showToastSafe("数据长度校验错误");
            UIUtils.showToastSafe(HexUtils.Bytes2HexString(datas));
        }
    }

    @Background
    public void checkType(byte[] datas, final BluetoothGattCharacteristic data_char){
        String str_1 = "第3位错误";
        int btn_id = -1;
        switch(datas[2]){
            case 1:
                str_1 = "冲洗臀部";
                btn_id = 2;
                break;
            case 2:
                str_1 = "冲洗便垫";
                btn_id = 4;
                break;
            case 3:
                str_1 = "大便模式";
                btn_id = 0;
                break;
            case 4:
                str_1 = "尿失禁模式";
                btn_id = 1;
                break;
            case 5:
                str_1 = "女性小便模式";
                btn_id = 1;
                break;
            case 6:
                str_1 = "男性小便模式";
                btn_id = 1;
                break;
            case 7:
                str_1 = "清洁杀菌模式";
                btn_id = 5;
                break;
            case 8:
                str_1 = "排水模式";
                break;
            case 9:
                str_1 = "自动换气模式(开)";
                break;
            case 10:
                str_1 = "自动换气模式(关)";
                break;
            case 11:
                str_1 = "出桶";
                break;
            case 12:
                str_1 = "入桶";
                break;
            case 13:
                str_1 = "通风干燥";
                btn_id = 3;
                break;
            case 17:
                str_1 = "停止模式";
                break;
            case 34:
                str_1 = "停止解除";
                break;
            case 80:
                str_1 = "(自动)大便模式";
                break;
            case 81:
                str_1 = "(自动)尿失禁模式";
                break;
            case 82:
                str_1 = "(自动)女性小便模式";
                break;
            case 83:
                str_1 = "(自动)男性小便模式";
                break;
            case 85:
                checkStatus(data_char);
                return;
        }
        checkButtonStatus(datas, str_1, btn_id);
    }

    @Background
    public void checkButtonStatus(byte[] datas, String str_1, int btn_id){
        String str_2 = "第5位错误";
        switch(datas[4]){
            case 0:
                str_2 = "未完成退出";
                flag = true;
                break;
            case 1:
                str_2 = "执行中";
                flag = false;
                break;
            case (byte)0xff:
                str_2 = "执行完成";
                flag = true;
                break;
        }
        syncButton(btn_id);
        UIUtils.showToastSafe(str_1 + str_2);
    }

    @Background
    public void checkStatus(final BluetoothGattCharacteristic data_char){
//        UIUtils.showToastSafe("正在判断设备状态");
        String[] status = new String[] { "水温过高", "管道堵塞", "便垫未连接", "电机温度过高", "污水桶满", "缺消毒水", "水位低于50%", "水位低于25%" };
        Integer data = data_char.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 4);
        for(int i = 0; i < 8; i ++) {
            if((data & (0x01 << i)) == (0x01 << i)){
                UIUtils.showToastSafe(status[i]);
            }
        }
    }
}
