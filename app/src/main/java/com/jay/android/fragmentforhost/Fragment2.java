package com.jay.android.fragmentforhost;

import android.app.Activity;
import android.app.Fragment;
import android.bluetooth.BluetoothGattCharacteristic;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jay.android.fragmentforhost.Help.BLEHelp;
import com.jay.android.fragmentforhost.Help.DataHelp;
import com.jay.android.fragmentforhost.Utils.SharedPreferencesUtils;
import com.jay.android.fragmentforhost.Utils.UIUtils;
import com.mt.ble.mtble.MTBLEMBLE;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;
import org.androidannotations.api.BackgroundExecutor;

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

    @ViewById
    Button btn_daxiaobian_dabianculi;
    @ViewById
    Button btn_daxiaobian_xiaobianculi;
    @ViewById
    Button btn_daxiaobian_qingxitunbu;
    @ViewById
    Button btn_daxiaobian_honggantunbu;
    @ViewById
    Button btn_daxiaobian_congxibiandian;
    @ViewById
    Button btn_daxiaobian_xiaodusajun;

    @ViewsById({R.id.btn_daxiaobian_dabianculi,
                R.id.btn_daxiaobian_xiaobianculi,
                R.id.btn_daxiaobian_qingxitunbu,
                R.id.btn_daxiaobian_honggantunbu,
                R.id.btn_daxiaobian_congxibiandian,
                R.id.btn_daxiaobian_xiaodusajun})
    List<Button> btns_daxiaobian;

    @ViewById
    Button btn_daxiaobian_paisui;
    @ViewById
    Button btn_daxiaobian_zidongculidaxiaobian;
    @ViewById
    Button btn_daxiaobian_zidonghuanqicucou;
    @ViewById
    Button btn_daxiaobian_niaosijinmosi;
    @ViewById
    Button btn_daxiaobian_nvxingmosi;
    @ViewById
    Button btn_daxiaobian_wusuitongsuiding;
    @ViewById
    Button btn_daxiaobian_paisuikaiguan;

    Boolean flag = true;
    Boolean flagPaisui = false;

    @ViewById
    ImageView img_daxiaobian_qingsuitong;
    @ViewById
    ImageView img_daxiaobian_yaosuitong;
    @ViewById
    ImageView img_daxiaobian_wusuitong;
    @ViewsById({R.id.img_daxiaobian_suiwen,
            R.id.img_daxiaobian_guandao,
            R.id.img_daxiaobian_biandian,
            R.id.img_daxiaobian_dianjiwendu,
            R.id.img_daxiaobian_wusuitong,
            R.id.img_daxiaobian_yaosuitong,
            R.id.img_daxiaobian_qingsuitong,
            R.id.img_daxiaobian_qingsuitong})
    List<ImageView> imgs_daxiaobian;

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
        bleHelp = new BLEHelp(activity, blecallback, DataHelp.mac[1]);
    }

    BLEHelp bleHelp = null;
    @Override
    public void onStart() {
        super.onStart();
        initSettings();
    }

    private static Map mosi = new HashMap() {{
        put("daxiaobianculi_auto", false);
        put("huanqicucou_auto", false);
        put("niaosijinmosi", false);
        put("nvxingmosi", false);
        put("wusuitongsuiding", false);
        put("paisuikaiguan", false);
    }};
    private void initSettings() {
        checkButton("niaosijinmosi", btn_daxiaobian_niaosijinmosi);
        checkButton("nvxingmosi", btn_daxiaobian_nvxingmosi);
        checkButton("wusuitongsuiding", btn_daxiaobian_wusuitongsuiding);
        checkButton("paisuikaiguan", btn_daxiaobian_paisuikaiguan);
        if((Boolean)mosi.get("paisuikaiguan")){
            btn_daxiaobian_paisui.setBackgroundResource(R.drawable.btn_daxiaobian_paisui);
            flagPaisui = true;
        }else{
            btn_daxiaobian_paisui.setBackgroundResource(R.drawable.btn_daxiaobian_paisui_disable);
            flagPaisui = false;
        }
    }

    private void checkButton(String key, Button btn) {
        mosi.put(key, (Boolean) SharedPreferencesUtils.getParam(getActivity().getApplicationContext(), key, false));
        if (mosi.get(key) != null) {
            if ((Boolean)mosi.get(key)) btn.setBackgroundResource(R.drawable.btn_on);
            else btn.setBackgroundResource(R.drawable.btn_off);
        } else {
            SharedPreferencesUtils.setParam(getActivity().getApplicationContext(), key, false);
        }
    }

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

    @Click(R.id.btn_daxiaobian_dabianculi)
    void dabianculiButtonClicked() {
        if(flag){
            bleHelp.sendDatas(DataHelp.DAXIAOBIAN_DABIANMOSI_STR, DataHelp.DAXIAOBIAN_DABIANMOSI);
        }
    }

    @Click(R.id.btn_daxiaobian_xiaobianculi)
    void xiaobianculiButtonClicked() {
        if(flag){
            if((Boolean)mosi.get("niaosijinmosi")){
                bleHelp.sendDatas(DataHelp.DAXIAOBIAN_NIAOSIJINMOSI_STR, DataHelp.DAXIAOBIAN_NIAOSIJINMOSI);
            }else if((Boolean)mosi.get("nvxingmosi")){
                bleHelp.sendDatas(DataHelp.DAXIAOBIAN_NVXINGXIAOBIANMOSI_STR, DataHelp.DAXIAOBIAN_NVXINGXIAOBIANMOSI);
            }else{
                bleHelp.sendDatas(DataHelp.DAXIAOBIAN_NANXINGXIAOBIANMOSI_STR, DataHelp.DAXIAOBIAN_NANXINGXIAOBIANMOSI);
            }
        }
    }

    @Click(R.id.btn_daxiaobian_qingxitunbu)
    void qingxitunbuButtonClicked() {
        if(flag){
            bleHelp.sendDatas(DataHelp.DAXIAOBIAN_CONGXITUNBU_STR, DataHelp.DAXIAOBIAN_CONGXITUNBU);
        }
    }

    @Click(R.id.btn_daxiaobian_honggantunbu)
    void honggantunbuButtonClicked() {
        if(flag){
            bleHelp.sendDatas(DataHelp.DAXIAOBIAN_TONGFENGGANZAO_STR, DataHelp.DAXIAOBIAN_TONGFENGGANZAO);
        }
    }

    @Click(R.id.btn_daxiaobian_congxibiandian)
    void congxibiandianButtonClicked() {
        if(flag){
            bleHelp.sendDatas(DataHelp.DAXIAOBIAN_CONGXIBIANDIAN_STR, DataHelp.DAXIAOBIAN_CONGXIBIANDIAN);
        }
    }

    @Click(R.id.btn_daxiaobian_xiaodusajun)
    void xiaodusajunButtonClicked() {
        if(flag){
            bleHelp.sendDatas(DataHelp.DAXIAOBIAN_QINGJIESAJUNMOSI_STR, DataHelp.DAXIAOBIAN_QINGJIESAJUNMOSI);
        }
    }

    @Click(R.id.btn_daxiaobian_paisui)
    void huanqicucouButtonClicked() {
        if(flagPaisui){
            bleHelp.sendDatas(DataHelp.DAXIAOBIAN_PAISUIMOSI_STR, DataHelp.DAXIAOBIAN_PAISUIMOSI);
        }
    }

    @Click(R.id.btn_daxiaobian_zidongculidaxiaobian)
    void zidongculidaxiaobianButtonClicked() {
        setButton("daxiaobianculi_auto", btn_daxiaobian_zidongculidaxiaobian);
    }

    @Click(R.id.btn_daxiaobian_zidonghuanqicucou)
    void zidonghuanqicucouButtonClicked() {
        if((Boolean)mosi.get("huanqicucou_auto")){
            bleHelp.sendDatas(DataHelp.DAXIAOBIAN_ZIDONGHUANQIMOSI_OFF_STR, DataHelp.DAXIAOBIAN_ZIDONGHUANQIMOSI_OFF);
        }else{
            bleHelp.sendDatas(DataHelp.DAXIAOBIAN_ZIDONGHUANQIMOSI_ON_STR, DataHelp.DAXIAOBIAN_ZIDONGHUANQIMOSI_ON);
        }
        setButton("huanqicucou_auto", btn_daxiaobian_zidonghuanqicucou);
    }

    @Click(R.id.btn_daxiaobian_niaosijinmosi)
    void niaosijinmosiButtonClicked() {
        if((Boolean)mosi.get("niaosijinmosi")){
            if((Boolean)mosi.get("nvxingmosi")){
                bleHelp.sendDatas(DataHelp.DAXIAOBIAN_NVXING_SETTINGS_STR, DataHelp.DAXIAOBIAN_NVXING_SETTINGS);
            }else{
                bleHelp.sendDatas(DataHelp.DAXIAOBIAN_NANXING_SETTINGS_STR, DataHelp.DAXIAOBIAN_NANXING_SETTINGS);
            }
        }else{
            bleHelp.sendDatas(DataHelp.DAXIAOBIAN_NIAOSIJIN_SETTINGS_STR, DataHelp.DAXIAOBIAN_NIAOSIJIN_SETTINGS);
        }
        setButton("niaosijinmosi", btn_daxiaobian_niaosijinmosi);
    }

    @Click(R.id.btn_daxiaobian_nvxingmosi)
    void nvxingmosiButtonClicked() {
        if((Boolean)mosi.get("niaosijinmosi")){
            bleHelp.sendDatas(DataHelp.DAXIAOBIAN_NIAOSIJIN_SETTINGS_STR, DataHelp.DAXIAOBIAN_NIAOSIJIN_SETTINGS);
        }else{
            if((Boolean)mosi.get("nvxingmosi")){
                bleHelp.sendDatas(DataHelp.DAXIAOBIAN_NANXING_SETTINGS_STR, DataHelp.DAXIAOBIAN_NANXING_SETTINGS);
            }else{
                bleHelp.sendDatas(DataHelp.DAXIAOBIAN_NVXING_SETTINGS_STR, DataHelp.DAXIAOBIAN_NVXING_SETTINGS);
            }
        }
        setButton("nvxingmosi", btn_daxiaobian_nvxingmosi);
    }

    @Click(R.id.btn_daxiaobian_wusuitongsuiding)
    void wusuitongsuidingButtonClicked() {
        if((Boolean)mosi.get("wusuitongsuiding")){
            bleHelp.sendDatas(DataHelp.DAXIAOBIAN_RUTONG_STR, DataHelp.DAXIAOBIAN_RUTONG);
            setButton("wusuitongsuiding", btn_daxiaobian_wusuitongsuiding);
            flag=false;
            syncButton(-1);
        }else{
            bleHelp.sendDatas(DataHelp.DAXIAOBIAN_CUTONG_STR, DataHelp.DAXIAOBIAN_CUTONG);
            setButton("wusuitongsuiding", btn_daxiaobian_wusuitongsuiding);
            flag=true;
            syncButton(0);
        }
    }

    @Click(R.id.btn_daxiaobian_paisuikaiguan)
    void paisuikaiguanButtonClicked() {
        setButton("paisuikaiguan", btn_daxiaobian_paisuikaiguan);
        if((Boolean)mosi.get("paisuikaiguan")){
            btn_daxiaobian_paisui.setBackgroundResource(R.drawable.btn_daxiaobian_paisui);
            flagPaisui = true;
        }else{
            btn_daxiaobian_paisui.setBackgroundResource(R.drawable.btn_daxiaobian_paisui_disable);
            flagPaisui = false;
        }
    }

    private void setButton(String key, Button btn) {
        if ((Boolean)mosi.get(key)) btn.setBackgroundResource(R.drawable.btn_off);
        else btn.setBackgroundResource(R.drawable.btn_on);
        mosi.put(key, !(Boolean) mosi.get(key));
        SharedPreferencesUtils.setParam(getActivity().getApplicationContext(), key, mosi.get(key));
    }

    @UiThread
    public void syncButton(int btn_id){
        String[] name = new String[] { "dabianculi", "xiaobianculi", "qingxitunbu", "honggantunbu", "congxibiandian", "xiaodusajun" };
        try {
            if(!flag) {
                for (int i = 0; i < 6; i++) {
                    if(i != btn_id) {
                        btns_daxiaobian.get(i).setBackgroundResource((int) R.drawable.class.getDeclaredField("btn_daxiaobian_" + name[i] + "_disable").get(R.drawable.class));
                    }else{
                        btns_daxiaobian.get(btn_id).setBackgroundResource(R.drawable.btn_ing);
                    }
                }
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

    @Background
    public void ReviceDatas(final BluetoothGattCharacteristic data_char){
        byte[] datas = data_char.getValue();
        if(datas.length == 7) {
            if(datas[0] == (byte)0xB2 && datas[1] == (byte)0x4E){
                checkType(datas, data_char);
            }else{
                UIUtils.showToastSafe("数据头部校验错误 ");
                UIUtils.showToastSafe("第0位是:" + datas[0] + "\n第1位是:" + datas[1]);
            }
        }
    }

    public void checkType(byte[] datas, final BluetoothGattCharacteristic data_char){
        String str_1 = "第2位错误";
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
        String str_2 = "第4位错误";
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
        String[] status = new String[] { "水温过高", "管道堵塞", "便垫未连接", "电机温度过高", "污水桶满", "缺消毒水", "水位低于50%", "水位低于25%" };
        Integer data = data_char.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 4);
        for(int i = 0; i < 8; i ++) {
            if((data & (0x80 >> i)) == (0x80 >> i)){
                setStatus(i);
            }
        }
        BackgroundExecutor.cancelAll("clearStatus_task", true);
        flag=false;
        syncButton(-1);
        clearStatus_task();
    }

    @UiThread
    public void setStatus(int status) {
        String[] name = new String[] { "suiwen", "guandao", "biandian", "dianjiwendu", "wusuitong", "yaosuitong", "qingsuitong", "qingsuitong" };
        try {
            BackgroundExecutor.cancelAll("Status_task_"+status, true);
            imgs_daxiaobian.get(status).setImageResource((int) R.drawable.class.getDeclaredField("img_daxiaobian_" + name[status] + "_danger").get(R.drawable.class));
            switch(status){
                case 0: Status_task_0();break;
                case 1: Status_task_1();break;
                case 2: Status_task_2();break;
                case 3: Status_task_3();break;
                case 4: Status_task_4();break;
                case 5: Status_task_5();break;
                case 6: Status_task_6();break;
                case 7: Status_task_7();break;
            }
        } catch (Exception e) {
            // TODO: handle exception
            Log.i("Fragment2", e.toString());
            return;
        }
    }

    @Background(id="Status_task_0", delay=2100)
    public void Status_task_0() {
        resetStatus(0);
    }
    @Background(id="Status_task_1", delay=2100)
    public void Status_task_1() {
        resetStatus(1);
    }
    @Background(id="Status_task_2", delay=2100)
    public void Status_task_2() {
        resetStatus(2);
    }
    @Background(id="Status_task_3", delay=2100)
    public void Status_task_3() {
        resetStatus(3);
    }
    @Background(id="Status_task_4", delay=2100)
    public void Status_task_4() {
        resetStatus(4);
    }
    @Background(id="Status_task_5", delay=2100)
    public void Status_task_5() {
        resetStatus(5);
    }
    @Background(id="Status_task_6", delay=2100)
    public void Status_task_6() {
        resetStatus(6);
    }
    @Background(id="Status_task_0", delay=2100)
    public void Status_task_7() {
        resetStatus(7);
    }
    @Background(id="clearStatus_task", delay=2300)
    public void clearStatus_task() {
        flag=true;
        syncButton(-1);
    }

    @UiThread
    public void resetStatus(int status){
        String[] name = new String[] { "suiwen", "guandao", "biandian", "dianjiwendu", "wusuitong", "yaosuitong", "qingsuitong", "qingsuitong" };
        try {
            imgs_daxiaobian.get(status).setImageResource((int) R.drawable.class.getDeclaredField("img_daxiaobian_" + name[status] + "_normal").get(R.drawable.class));
        } catch (Exception e) {
            // TODO: handle exception
            Log.i("Fragment2", e.toString());
            return;
        }
    }
}
