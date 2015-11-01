package com.jay.android.fragmentforhost;

import android.app.Activity;
import android.app.Fragment;
import android.bluetooth.BluetoothGattCharacteristic;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EFragment(R.layout.fragment_4)
public class Fragment4 extends Fragment {
    private byte[] sendbytes = null;
    private Activity activity;
    private static Map param = new HashMap() {{
        put("duanliansicang", new HashMap(){{
            put("value",10);
            put("min",0);
            put("max",240);
            put("step",5);
        }});
        put("jiaosudu", new HashMap(){{
            put("value",1);
            put("min",0);
            put("max",10);
            put("step",1);
        }});
        put("qisijiaodu", new HashMap(){{
            put("value",0);
            put("min",0);
            put("max",120);
            put("step",1);
        }});
        put("jiesujiaodu", new HashMap(){{
            put("value",20);
            put("min",0);
            put("max",120);
            put("step",1);
        }});
        put("huancongjiaodu", new HashMap(){{
            put("value",5);
            put("min",0);
            put("max",120);
            put("step",1);
        }});
        put("tingliusicang", new HashMap(){{
            put("value",5);
            put("min",0);
            put("max",60);
            put("step",1);
        }});
    }};
    private static List datas = new ArrayList<byte []>() {{
        add(new byte[]{(byte) 0xb1, (byte) 0x17, (byte) 0x08, (byte) 0x11, (byte) 0x00, (byte) 0x1b, (byte) 0x00, (byte) 0x00, (byte) 0x0d, (byte) 0x0a});//锻炼时长<=60分，没有高八位,每按下一次加减号加减5分
        add(new byte[]{(byte) 0xb1, (byte) 0x18, (byte) 0x08, (byte) 0x11, (byte) 0x00, (byte) 0x1b, (byte) 0x00, (byte) 0x00, (byte) 0x0d, (byte) 0x0a});//角速度，没有高八位，范围1-10,每按下一次加减号加减1度
        add(new byte[]{(byte) 0xb1, (byte) 0x19, (byte) 0x08, (byte) 0x11, (byte) 0x00, (byte) 0x1b, (byte) 0x00, (byte) 0x00, (byte) 0x0d, (byte) 0x0a});//起始角度<120度，没有高八位，范围0<起始角度<结束角度,每按下一次加减号加减1度
        add(new byte[]{(byte) 0xb1, (byte) 0x1a, (byte) 0x08, (byte) 0x11, (byte) 0x00, (byte) 0x1b, (byte) 0x00, (byte) 0x00, (byte) 0x0d, (byte) 0x0a});//结束角度<120度，没有高八位,范围结束角度<120度,每按下一次加减号加减5度
        add(new byte[]{(byte) 0xb1, (byte) 0x1b, (byte) 0x08, (byte) 0x11, (byte) 0x00, (byte) 0x1b, (byte) 0x00, (byte) 0x00, (byte) 0x0d, (byte) 0x0a});//缓冲角度<120度，没有高八位,范围起始角度<缓冲角度<结束角度，每按下一次加减号加减1度
        add(new byte[]{(byte) 0xb1, (byte) 0x1c, (byte) 0x08, (byte) 0x11, (byte) 0x00, (byte) 0x1b, (byte) 0x00, (byte) 0x00, (byte) 0x0d, (byte) 0x0a});//停留时长<60分，没有高八位,范围0<停留时长<锻炼时长，每按下一次加减号加减1分
    }};


    @ViewsById({R.id.edt_xiazikangfu_duanliansicang,
                R.id.edt_xiazikangfu_jiaosudu,
                R.id.edt_xiazikangfu_qisijiaodu,
                R.id.edt_xiazikangfu_jiesujiaodu,
                R.id.edt_xiazikangfu_huancongjiaodu,
                R.id.edt_xiazikangfu_tingliusicang})
    List<EditText> edts_xiazikangfu;

    @ViewById
    Button btn_xiazikangfu_jiqizunbei;
    @ViewById
    Button btn_xiazikangfu_kaisiduanlian;
    @ViewById
    TextView tv_xiazikangfu_sisijiaodu;
    @ViewById
    TextView tv_xiazikangfu_duanliansicang;
    @ViewById
    TextView tv_xiazikangfu_duanliancisu;

    Boolean buttonFlag[] = {true,false};

    BLEHelp bleHelp = null;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        bleHelp = new BLEHelp(activity, blecallback, DataHelp.mac[3]);
    }

    @Click({R.id.btn_xiazikangfu_jiqizunbei,
            R.id.btn_xiazikangfu_kaisiduanlian})
    void xiazikangfuButtonClicked(View view) {
        switch (view.getId()){
            case R.id.btn_xiazikangfu_jiqizunbei:
                if(buttonFlag[0]) {
                    bleHelp.sendDatas(DataHelp.XIAZIKANGFU_JIQIZUNBEI_STR, DataHelp.XIAZIKANGFU_JIQIZUNBEI);
                }
                break;
            case R.id.btn_xiazikangfu_kaisiduanlian:
                if(buttonFlag[1]) {
                    bleHelp.sendDatas(DataHelp.XIAZIKANGFU_DUANLIAN_START_STR, DataHelp.XIAZIKANGFU_DUANLIAN_START);
                }
                break;
        }
    }

    @Click({R.id.btn_xiazikangfu_duanliansicang_sub,
            R.id.btn_xiazikangfu_duanliansicang_add,
            R.id.btn_xiazikangfu_jiaosudu_sub,
            R.id.btn_xiazikangfu_jiaosudu_add,
            R.id.btn_xiazikangfu_qisijiaodu_sub,
            R.id.btn_xiazikangfu_qisijiaodu_add,
            R.id.btn_xiazikangfu_jiesujiaodu_sub,
            R.id.btn_xiazikangfu_jiesujiaodu_add,
            R.id.btn_xiazikangfu_huancongjiaodu_sub,
            R.id.btn_xiazikangfu_huancongjiaodu_add,
            R.id.btn_xiazikangfu_tingliusicang_sub,
            R.id.btn_xiazikangfu_tingliusicang_add})
    void parametersSettingsButtons(View view) {
        switch (view.getId()){
            case R.id.btn_xiazikangfu_duanliansicang_sub:
                parametersAddSub(0,"sub");
                break;
            case R.id.btn_xiazikangfu_duanliansicang_add:
                parametersAddSub(0, "add");
                break;
            case R.id.btn_xiazikangfu_jiaosudu_sub:
                parametersAddSub(1,"sub");
                break;
            case R.id.btn_xiazikangfu_jiaosudu_add:
                parametersAddSub(1, "add");
                break;
            case R.id.btn_xiazikangfu_qisijiaodu_sub:
                parametersAddSub(2,"sub");
                break;
            case R.id.btn_xiazikangfu_qisijiaodu_add:
                parametersAddSub(2, "add");
                break;
            case R.id.btn_xiazikangfu_jiesujiaodu_sub:
                parametersAddSub(3,"sub");
                break;
            case R.id.btn_xiazikangfu_jiesujiaodu_add:
                parametersAddSub(3, "add");
                break;
            case R.id.btn_xiazikangfu_huancongjiaodu_sub:
                parametersAddSub(4,"sub");
                break;
            case R.id.btn_xiazikangfu_huancongjiaodu_add:
                parametersAddSub(4, "add");
                break;
            case R.id.btn_xiazikangfu_tingliusicang_sub:
                parametersAddSub(5,"sub");
                break;
            case R.id.btn_xiazikangfu_tingliusicang_add:
                parametersAddSub(5, "add");
                break;
        }
    }

    void parametersAddSub(int i, String type){
        String[] name = new String[]{"duanliansicang", "jiaosudu", "qisijiaodu", "jiesujiaodu", "huancongjiaodu", "tingliusicang"};
        String[] title = new String[]{"锻炼时长", "角速度", "起始角度", "结束角度", "缓冲角度", "停留时长"};
        byte[] data = null;
        if(i == 2){
            if(type == "sub"){
                if((int)((HashMap)param.get(name[2])).get("value") <= (int)((HashMap)param.get(name[2])).get("step")) ((HashMap)param.get(name[2])).put("value", 0);
                else ((HashMap)param.get(name[2])).put("value", (int)((HashMap)param.get(name[2])).get("value") - (int)((HashMap)param.get(name[2])).get("step"));
                edts_xiazikangfu.get(2).setText("" + (int)((HashMap)param.get(name[2])).get("value"));
                data = (byte[]) datas.get(2);
                data[3] = (byte) (int)((HashMap)param.get(name[2])).get("value");
                sendbytes = CRCHelp.CRC16(data, 6);
                bleHelp.sendDatas(title[2], sendbytes);
            }else if(type == "add"){
                if((int)((HashMap)param.get(name[2])).get("value") >= ((int)((HashMap)param.get(name[3])).get("value")-(int)((HashMap)param.get(name[2])).get("step"))) ((HashMap)param.get(name[2])).put("value", (int)((HashMap)param.get(name[3])).get("value"));
                else ((HashMap)param.get(name[2])).put("value", (int)((HashMap)param.get(name[2])).get("value") + (int)((HashMap)param.get(name[2])).get("step"));
                edts_xiazikangfu.get(2).setText("" + (int) ((HashMap) param.get(name[2])).get("value"));
                data = (byte[]) datas.get(2);
                data[3] = (byte) (int)((HashMap)param.get(name[2])).get("value");
                sendbytes = CRCHelp.CRC16(data, 6);
                bleHelp.sendDatas(title[2], sendbytes);
            }
        }else if(i == 3){
            if(type == "sub"){
                if((int)((HashMap)param.get(name[3])).get("value") <= ((int)((HashMap)param.get(name[2])).get("value")+(int)((HashMap)param.get(name[3])).get("step"))) ((HashMap)param.get(name[3])).put("value", (int)((HashMap)param.get(name[2])).get("value"));
                else ((HashMap)param.get(name[3])).put("value", (int)((HashMap)param.get(name[3])).get("value") - (int)((HashMap)param.get(name[3])).get("step"));
                edts_xiazikangfu.get(3).setText("" + (int)((HashMap)param.get(name[3])).get("value"));
                data = (byte[]) datas.get(3);
                data[3] = (byte) (int)((HashMap)param.get(name[3])).get("value");
                sendbytes = CRCHelp.CRC16(data, 6);
                bleHelp.sendDatas(title[3], sendbytes);
            }else if(type == "add"){
                if((int)((HashMap)param.get(name[3])).get("value") >= ((int)((HashMap)param.get(name[3])).get("max")-(int)((HashMap)param.get(name[3])).get("step"))) ((HashMap)param.get(name[3])).put("value", (int)((HashMap)param.get(name[3])).get("max"));
                else ((HashMap)param.get(name[3])).put("value", (int)((HashMap)param.get(name[3])).get("value") + (int)((HashMap)param.get(name[3])).get("step"));
                edts_xiazikangfu.get(3).setText("" + (int) ((HashMap) param.get(name[3])).get("value"));
                data = (byte[]) datas.get(3);
                data[3] = (byte) (int)((HashMap)param.get(name[3])).get("value");
                sendbytes = CRCHelp.CRC16(data, 6);
                bleHelp.sendDatas(title[3], sendbytes);
            }
        }else if(i == 4){
            if(type == "sub"){
                if((int)((HashMap)param.get(name[4])).get("value") <= (int)((HashMap)param.get(name[4])).get("step")) ((HashMap)param.get(name[4])).put("value", 0);
                else ((HashMap)param.get(name[4])).put("value", (int)((HashMap)param.get(name[4])).get("value") - (int)((HashMap)param.get(name[4])).get("step"));
                edts_xiazikangfu.get(4).setText("" + (int)((HashMap)param.get(name[4])).get("value"));
                data = (byte[]) datas.get(4);
                data[3] = (byte) (int)((HashMap)param.get(name[4])).get("value");
                sendbytes = CRCHelp.CRC16(data, 6);
                bleHelp.sendDatas(title[4], sendbytes);
            }else if(type == "add"){
                if((int)((HashMap)param.get(name[4])).get("value") >= ((int)((HashMap)param.get(name[3])).get("value")-(int)((HashMap)param.get(name[4])).get("step"))) ((HashMap)param.get(name[4])).put("value", (int)((HashMap)param.get(name[4])).get("value"));
                else ((HashMap)param.get(name[4])).put("value", (int)((HashMap)param.get(name[4])).get("value") + (int)((HashMap)param.get(name[4])).get("step"));
                edts_xiazikangfu.get(4).setText("" + (int) ((HashMap) param.get(name[4])).get("value"));
                data = (byte[]) datas.get(4);
                data[3] = (byte) (int)((HashMap)param.get(name[4])).get("value");
                sendbytes = CRCHelp.CRC16(data, 6);
                bleHelp.sendDatas(title[4], sendbytes);
            }
        }else{
            if(type == "sub"){
                if((int)((HashMap)param.get(name[i])).get("value") <= ((int)((HashMap)param.get(name[i])).get("min")+(int)((HashMap)param.get(name[i])).get("step"))) ((HashMap)param.get(name[i])).put("value", 0);
                else ((HashMap)param.get(name[i])).put("value", (int)((HashMap)param.get(name[i])).get("value") - (int)((HashMap)param.get(name[i])).get("step"));
                edts_xiazikangfu.get(i).setText("" + (int)((HashMap)param.get(name[i])).get("value"));
                data = (byte[]) datas.get(i);
                data[3] = (byte) (int)((HashMap)param.get(name[i])).get("value");
                sendbytes = CRCHelp.CRC16(data, 6);
                bleHelp.sendDatas(title[i], sendbytes);
            }else if(type == "add"){
                if((int)((HashMap)param.get(name[i])).get("value") >= ((int)((HashMap)param.get(name[i])).get("max")-(int)((HashMap)param.get(name[i])).get("step"))) ((HashMap)param.get(name[i])).put("value", (int)((HashMap)param.get(name[0])).get("max"));
                else ((HashMap)param.get(name[i])).put("value", (int)((HashMap)param.get(name[i])).get("value") + (int)((HashMap)param.get(name[i])).get("step"));
                edts_xiazikangfu.get(i).setText("" + (int) ((HashMap) param.get(name[i])).get("value"));
                data = (byte[]) datas.get(i);
                data[3] = (byte) (int)((HashMap)param.get(name[i])).get("value");
                sendbytes = CRCHelp.CRC16(data, 6);
                bleHelp.sendDatas(title[i], sendbytes);
            }
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
        UIUtils.showToastSafe(HexUtils.Bytes2HexString(datas));
        if(datas.length == 8) {
            if(datas[0] == (byte)0xB2 && datas[5] == (byte)0x2B){
                checkType(datas, data_char);
            }else{
                UIUtils.showToastSafe("数据报头或报尾校验错误 ");
                UIUtils.showToastSafe("第0位是:" + datas[0] + "\n第5位是:" + datas[5]);
            }
        }
    }

    @Background
    public void checkType(byte[] datas, final BluetoothGattCharacteristic data_char){
        String str_1 = "第2位错误";
        int btn_id = -1;
        switch(datas[1]){
            case (byte) 0x12:
                str_1 = "机器";
                btn_id = 1;
                checkButtonStatus(datas, str_1, btn_id);
                break;
            case (byte) 0x13:
                str_1 = "锻炼";
                btn_id = 2;
                checkButtonStatus(datas, str_1, btn_id);
                break;
            case (byte) 0x14:
                str_1 = "实时角度";
                showData(datas, str_1);
                break;
            case (byte) 0x15:
                str_1 = "锻炼时长";
                showData(datas, str_1);
                break;
            case (byte) 0x16:
                str_1 = "锻炼次数";
                showData(datas, str_1);
                break;
        }
    }

    @Background
    public void checkButtonStatus(byte[] datas, String str_1, int btn_id){
        String str_2 = "第3位错误";
        switch(datas[3]){
            case (byte) 0x02:
                if(btn_id == 1) {str_2 = "未准备";syncButton(datas);}
                else {str_2 = "执行中";}
                break;
            case (byte) 0x03:
                str_2 = "已准备";
                syncButton(datas);
                break;
            case (byte) 0x04:
                str_2 = "暂停";
                break;
            case (byte) 0x08:
                str_2 = "完成";
                break;
        }
        UIUtils.showToastSafe(str_1 + str_2);
    }

    @UiThread
    public void syncButton(byte[] datas){
        if(datas[1] == (byte)0x12 && datas[3] == (byte)0x02){
            btn_xiazikangfu_jiqizunbei.setBackgroundResource(R.drawable.btn_jiqizunbei_normal);
            buttonFlag[0] = true;
            btn_xiazikangfu_kaisiduanlian.setBackgroundResource(R.drawable.btn_kaisiduanlian_disable);
            buttonFlag[1] = false;
        }else if(datas[1] == (byte)0x12 && datas[3] == (byte)0x03){
            btn_xiazikangfu_jiqizunbei.setBackgroundResource(R.drawable.btn_jiqizunbei_disable);
            buttonFlag[0] = false;
            btn_xiazikangfu_kaisiduanlian.setBackgroundResource(R.drawable.btn_kaisiduanlian_normal);
            buttonFlag[1] = true;
        }else if(datas[1] == (byte)0x13 && datas[3] == (byte)0x02){
            btn_xiazikangfu_jiqizunbei.setBackgroundResource(R.drawable.btn_jiqizunbei_disable);
            buttonFlag[0] = false;
        }else if(datas[1] == (byte)0x13 && datas[3] == (byte)0x04){
            btn_xiazikangfu_jiqizunbei.setBackgroundResource(R.drawable.btn_jiqizunbei_disable);
            buttonFlag[0] = false;
        }
    }

    @UiThread
    public void showData(byte[] datas, String str_1){
        UIUtils.showToastSafe(str_1 + datas[3]);
        int duanliancisu = 0;
        switch(datas[1]){
            case (byte) 0x14:
                tv_xiazikangfu_sisijiaodu.setText(datas[3] + "°");
                break;
            case (byte) 0x15:
                tv_xiazikangfu_duanliansicang.setText(datas[3] + "分");
                break;
            case (byte) 0x16:
                duanliancisu = datas[4] * 256 + datas[3];
                break;
        }
        tv_xiazikangfu_duanliancisu.setText("今天               锻炼时长:" + datas[3] + "分          锻炼次数:" + duanliancisu + "                                                                  ");
    }
}
