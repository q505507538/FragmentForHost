package com.jay.android.fragmentforhost;

import android.app.Activity;
import android.app.Fragment;
import android.bluetooth.BluetoothGattCharacteristic;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private static int fansenzouqi = 1;
    private static int fansenjiaodu = 5;
    private static int zongsicang = 10;

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
    EditText edt_cuangti_fansenzouqi;
    @ViewById
    EditText edt_cuangti_fansenjiaodu;
    @ViewById
    EditText edt_cuangti_zongsicang;

    @ViewById
    View soudong_selected;
    @ViewById
    View zidong_selected;

    // 按钮组
    @ViewsById({R.id.btn_cuangti_qibei,
            R.id.btn_cuangti_tangping,
            R.id.btn_cuangti_taitui,
            R.id.btn_cuangti_zetui,
            R.id.btn_cuangti_youfansen,
            R.id.btn_cuangti_zuofansen,
            R.id.btn_cuangti_zidongfansen})
    List<Button> btns_cuangti;

    private static Boolean buttonFlag[] = {true, true, true, true, true, true, true, true};
    private static Boolean flag = true;
    private static Boolean autoFlag = false;
    private static Boolean soudongFlag = false;

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
        bleHelp = new BLEHelp(activity, blecallback, DataHelp.mac[0]);
    }

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


    @Click({R.id.btn_cuangti_qibei,
            R.id.btn_cuangti_tangping,
            R.id.btn_cuangti_taitui,
            R.id.btn_cuangti_zetui,
            R.id.btn_cuangti_youfansen,
            R.id.btn_cuangti_zuofansen,
            R.id.btn_cuangti_zidongfansen,
            R.id.btn_cuangti_reset_soudong,
            R.id.btn_cuangti_reset_auto})
    void cuangtiButtonClicked(View view) {
        switch (view.getId()){
            case R.id.btn_cuangti_qibei:
                if(buttonFlag[0]){
                    if(flag) bleHelp.sendDatas(DataHelp.CUANGTI_QIBEI_START_STR, DataHelp.CUANGTI_QIBEI_START);
                    else bleHelp.sendDatas(DataHelp.CUANGTI_QIBEI_PAUSE_STR, DataHelp.CUANGTI_QIBEI_PAUSE);
                }
                break;
            case R.id.btn_cuangti_tangping:
                if(buttonFlag[1]){
                    if(flag) bleHelp.sendDatas(DataHelp.CUANGTI_PINGTANG_START_STR, DataHelp.CUANGTI_PINGTANG_START);
                    else bleHelp.sendDatas(DataHelp.CUANGTI_PINGTANG_PAUSE_STR, DataHelp.CUANGTI_PINGTANG_PAUSE);
                }
                break;
            case R.id.btn_cuangti_taitui:
                if(buttonFlag[2]){
                    if(flag) bleHelp.sendDatas(DataHelp.CUANGTI_TAITUI_START_STR, DataHelp.CUANGTI_TAITUI_START);
                    else bleHelp.sendDatas(DataHelp.CUANGTI_TAITUI_PAUSE_STR, DataHelp.CUANGTI_TAITUI_PAUSE);
                }
                break;
            case R.id.btn_cuangti_zetui:
                if(buttonFlag[3]){
                    if(flag) bleHelp.sendDatas(DataHelp.CUANGTI_ZETUI_START_STR, DataHelp.CUANGTI_ZETUI_START);
                    else bleHelp.sendDatas(DataHelp.CUANGTI_ZETUI_PAUSE_STR, DataHelp.CUANGTI_ZETUI_PAUSE);
                }
                break;
            case R.id.btn_cuangti_youfansen:
                if(buttonFlag[4]){
                    if(flag) bleHelp.sendDatas(DataHelp.CUANGTI_YOUFANSEN_START_STR, DataHelp.CUANGTI_YOUFANSEN_START);
                    else bleHelp.sendDatas(DataHelp.CUANGTI_YOUFANSEN_PAUSE_STR, DataHelp.CUANGTI_YOUFANSEN_PAUSE);
                }
                break;
            case R.id.btn_cuangti_zuofansen:
                if(buttonFlag[5]){
                    if(flag) bleHelp.sendDatas(DataHelp.CUANGTI_ZUOFANSEN_START_STR, DataHelp.CUANGTI_ZUOFANSEN_START);
                    else bleHelp.sendDatas(DataHelp.CUANGTI_ZUOFANSEN_PAUSE_STR, DataHelp.CUANGTI_ZUOFANSEN_PAUSE);
                }
                break;
            case R.id.btn_cuangti_zidongfansen:
                if(buttonFlag[6]){
                    if(flag) {
                        sendParameters();
                        bleHelp.sendDatas(DataHelp.CUANGTI_FANSEN_AUTO_START_STR, DataHelp.CUANGTI_FANSEN_AUTO_START);
                    }else bleHelp.sendDatas(DataHelp.CUANGTI_FANSEN_AUTO_PAUSE_STR, DataHelp.CUANGTI_FANSEN_AUTO_PAUSE);
                }
                break;
            case R.id.btn_cuangti_reset_soudong:
                if(buttonFlag[7]) bleHelp.sendDatas(DataHelp.CUANGTI_RESET_START_STR, DataHelp.CUANGTI_RESET_START);
                else UIUtils.showToastSafe("复位尚未完成");
                break;
            case R.id.btn_cuangti_reset_auto:
                if(buttonFlag[7]) bleHelp.sendDatas(DataHelp.CUANGTI_RESET_START_STR, DataHelp.CUANGTI_RESET_START);
                else UIUtils.showToastSafe("复位尚未完成");
                break;
        }
    }

    private void sendParameters(){
        if(fansenzouqi < 0 || fansenzouqi > 60) {
            UIUtils.showToastSafe("翻身周期" + fansenzouqi + "分钟超过范围");
            return;
        }
        if(fansenjiaodu < 0|| fansenjiaodu > 30) {
            UIUtils.showToastSafe("翻身角度" + fansenjiaodu + "度超过范围");
            return;
        }
        if(zongsicang < 0|| zongsicang > 3600) {
            UIUtils.showToastSafe("总时长" + zongsicang + "分钟超过范围");
            return;
        }
        byte[] fansenzouqi_data = new byte[]{(byte) 0xb1,(byte) 0x0f,(byte) 0x08,(byte) 0x0a,(byte) 0x00,(byte) 0x1b,(byte) 0x00,(byte) 0x00,(byte) 0x0d,(byte) 0x0a};
        byte[] fansenjiaodu_data = new byte[]{(byte) 0xb1,(byte) 0x10,(byte) 0x08,(byte) 0x05,(byte) 0x00,(byte) 0x1b,(byte) 0x00,(byte) 0x00,(byte) 0x0d,(byte) 0x0a};
        byte[] zongsicang_data = new byte[]{(byte) 0xb1,(byte) 0x11,(byte) 0x08,(byte) 0x0a,(byte) 0x00,(byte) 0x1b,(byte) 0x00,(byte) 0x00,(byte) 0x0d,(byte) 0x0a};
        fansenzouqi_data[3] = (byte) fansenzouqi;
        sendbytes = CRCHelp.CRC16(fansenzouqi_data, 6);
        bleHelp.sendDatas("翻身周期", sendbytes);
        try {
            Thread.currentThread().sleep(100);
        }catch(Exception e){}
        fansenjiaodu_data[3] = (byte) fansenjiaodu;
        sendbytes = CRCHelp.CRC16(fansenjiaodu_data, 6);
        bleHelp.sendDatas("翻身角度", sendbytes);
        try {
            Thread.currentThread().sleep(100);
        }catch(Exception e){}
        zongsicang_data[4] = (byte) (zongsicang / 255);
        zongsicang_data[3] = (byte) (zongsicang % 255);
        sendbytes = CRCHelp.CRC16(zongsicang_data, 6);
        bleHelp.sendDatas("总时长", sendbytes);
        try {
            Thread.currentThread().sleep(100);
        }catch(Exception e){}
    }

    @Click({R.id.btn_cuangti_fansenzouqi_sub,
            R.id.btn_cuangti_fansenzouqi_add,
            R.id.btn_cuangti_fansenjiaodu_sub,
            R.id.btn_cuangti_fansenjiaodu_add,
            R.id.btn_cuangti_zongsicang_sub,
            R.id.btn_cuangti_zongsicang_add})
    void parametersSettingsButtons(View view) {
        byte[] fansenzouqi_data = new byte[]{(byte) 0xb1,(byte) 0x0f,(byte) 0x08,(byte) 0x0a,(byte) 0x00,(byte) 0x1b,(byte) 0x00,(byte) 0x00,(byte) 0x0d,(byte) 0x0a};
        byte[] fansenjiaodu_data = new byte[]{(byte) 0xb1,(byte) 0x10,(byte) 0x08,(byte) 0x05,(byte) 0x00,(byte) 0x1b, (byte) 0x00,(byte) 0x00,(byte) 0x0d,(byte) 0x0a};
        byte[] zongsicang_data = new byte[]{(byte) 0xb1,(byte) 0x11,(byte) 0x08,(byte) 0x0a,(byte) 0x00,(byte) 0x1b,(byte) 0x00,(byte) 0x00,(byte) 0x0d,(byte) 0x0a};
        switch (view.getId()){
            case R.id.btn_cuangti_fansenzouqi_sub:
                if(fansenzouqi <= 1) fansenzouqi = 0;
                else fansenzouqi -= 1;
                edt_cuangti_fansenzouqi.setText("" + fansenzouqi);
                fansenzouqi_data[3] = (byte) fansenzouqi;
                sendbytes = CRCHelp.CRC16(fansenzouqi_data, 6);
                bleHelp.sendDatas("翻身周期", sendbytes);
                break;
            case R.id.btn_cuangti_fansenzouqi_add:
                if(fansenzouqi >= (zongsicang - 1)) fansenzouqi = zongsicang;
                else fansenzouqi += 1;
                edt_cuangti_fansenzouqi.setText("" + fansenzouqi);
                fansenzouqi_data[3] = (byte) fansenzouqi;
                sendbytes = CRCHelp.CRC16(fansenzouqi_data, 6);
                bleHelp.sendDatas("翻身周期", sendbytes);
                break;
            case R.id.btn_cuangti_fansenjiaodu_sub:
                if(fansenjiaodu <= 1) fansenjiaodu = 0;
                else fansenjiaodu -= 1;
                edt_cuangti_fansenjiaodu.setText("" + fansenjiaodu);
                fansenjiaodu_data[3] = (byte) fansenjiaodu;
                sendbytes = CRCHelp.CRC16(fansenjiaodu_data, 6);
                bleHelp.sendDatas("翻身角度", sendbytes);
                break;
            case R.id.btn_cuangti_fansenjiaodu_add:
                if(fansenjiaodu >= 29) fansenjiaodu = 30;
                else fansenjiaodu += 1;
                edt_cuangti_fansenjiaodu.setText("" + fansenjiaodu);
                fansenjiaodu_data[3] = (byte) fansenjiaodu;
                sendbytes = CRCHelp.CRC16(fansenjiaodu_data, 6);
                bleHelp.sendDatas("翻身角度", sendbytes);
                break;
            case R.id.btn_cuangti_zongsicang_sub:
                if(zongsicang <= 5) zongsicang = 0;
                else zongsicang -= 5;
                edt_cuangti_zongsicang.setText("" + zongsicang);
                zongsicang_data[4] = (byte) (zongsicang / 255);
                zongsicang_data[3] = (byte) (zongsicang % 255);
                sendbytes = CRCHelp.CRC16(zongsicang_data, 6);
                bleHelp.sendDatas("总时长", sendbytes);
                break;
            case R.id.btn_cuangti_zongsicang_add:
                if(zongsicang >= (3600 - 5)) zongsicang = 3600;
                else zongsicang += 5;
                edt_cuangti_zongsicang.setText("" + zongsicang);
                zongsicang_data[4] = (byte) (zongsicang / 255);
                zongsicang_data[3] = (byte) (zongsicang % 255);
                sendbytes = CRCHelp.CRC16(zongsicang_data, 6);
                bleHelp.sendDatas("总时长", sendbytes);
                break;
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
        String[] str_1 = new String[]{"第1位错误","起背","躺平","抬腿","折腿","右翻身","左翻身","自动翻身","复位","急停","腿部角度","背部角度","起背次数","弯腿次数","翻身次数"};
        int btn_id = -1;
        if(datas[1] > 0 && datas[1] < 10){
            checkButtonStatus(datas, str_1[datas[1]], datas[1] - 1);
        }else if(datas[1] > 9 && datas[1] < 15){
            showData(datas, str_1[datas[1]]);
        }else{
            UIUtils.showToastSafe(str_1[0]);
        }
    }

    @Background
    public void checkButtonStatus(byte[] datas, String str_1, int btn_id){
        String str_2 = "第3位错误";
        switch(datas[3]){
            case (byte) 0x02:
                str_2 = "执行中";
                break;
            case (byte) 0x04:
                str_2 = "暂停";
                if(datas[1] == 7) btn_id = -2;
                else btn_id = -1;
                break;
            case (byte)0x08:
                str_2 = "完成";
                if(datas[1] == 7) btn_id = -2;
                else btn_id = -1;
                break;
        }
        syncButton(btn_id);
        UIUtils.showToastSafe(str_1 + str_2);
    }

    @UiThread
    public void syncButton(int btn_id){
        String[] name = new String[] { "qibei", "tangping", "taitui", "zetui", "youfansen", "zuofansen", "zidongfansen" };
        try {
            if (btn_id == -1) {
                flag = true;
                buttonFlag = new Boolean[]{true, true, true, true, true, true, true, true};
                for (int i = 0; i < 6; i++)  btns_cuangti.get(i).setBackgroundResource((int) R.drawable.class.getDeclaredField("btn_cuangti_" + name[i]).get(R.drawable.class));
                btns_cuangti.get(6).setBackgroundResource(R.drawable.btn_off);
                soudongFlag = false;autoFlag = false;
            }else if(btn_id == -2){
                setChioceItem(1);
                flag = true;
                buttonFlag = new Boolean[]{true, true, true, true, true, true, false, true};
                for (int i = 0; i < 6; i++)  btns_cuangti.get(i).setBackgroundResource((int) R.drawable.class.getDeclaredField("btn_cuangti_" + name[i]).get(R.drawable.class));
                btns_cuangti.get(6).setBackgroundResource(R.drawable.btn_off);
                soudongFlag = false;autoFlag = true;
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
                        btns_cuangti.get(6).setBackgroundResource(R.drawable.btn_on);
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
