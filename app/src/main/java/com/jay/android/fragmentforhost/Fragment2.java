package com.jay.android.fragmentforhost;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jay.android.fragmentforhost.Help.BLEHelp;
import com.jay.android.fragmentforhost.Utils.SharedPreferencesUtils;
import com.jay.android.fragmentforhost.Utils.UIUtils;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
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
        bleHelp = new BLEHelp(activity);
    }

    BLEHelp bleHelp = null;
    @Override
    public void onStart() {
        super.onStart();
        initSettings();
    }

    private static Map mosi = new HashMap() {{
        put("niaosijinmosi", false);   // 尿失禁模式按钮
        put("nvxingmosi", false);      // 女性模式按钮
        put("wusuitongsuiding", false);// 污水桶锁定按钮
        put("paisuikaiguan", false);   // 排水开关按钮
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
        sendbytes = new byte[]{(byte) 0xeb, (byte) 0x90, 0x03, 0x00, 0x35, 0x1D};
        bleHelp.sendDatas(sendbytes);
        UIUtils.showToastSafe("大便处理EB900300351D");
    }

    // 小便处理按钮
    @Click(R.id.btn_daxiaobian_xiaobianculi)
    void xiaobianculiButtonClicked() {
        if((Boolean)mosi.get("niaosijinmosi")){
            sendbytes = new byte[]{(byte) 0xeb, (byte) 0x90, 0x04, 0x00, 0x37, 0x2D};
            bleHelp.sendDatas(sendbytes);
            UIUtils.showToastSafe("尿失禁模式EB900400372D");
        }else if((Boolean)mosi.get("nvxingmosi")){
            sendbytes = new byte[]{(byte) 0xeb, (byte) 0x90, 0x05, 0x00, 0x36, (byte) 0xBD};
            bleHelp.sendDatas(sendbytes);
            UIUtils.showToastSafe("女性模式EB90050036BD");
        }else{
            sendbytes = new byte[]{(byte) 0xeb, (byte) 0x90, 0x06, 0x00, 0x36, 0x4D};
            bleHelp.sendDatas(sendbytes);
            UIUtils.showToastSafe("男性模式EB900600364D");
        }
    }

    // 清洗臀部按钮
    @Click(R.id.btn_daxiaobian_qingxitunbu)
    void qingxitunbuButtonClicked() {
        sendbytes = new byte[]{(byte) 0xeb, (byte) 0x90, 0x01, 0x00, 0x34, 0x7D};
        bleHelp.sendDatas(sendbytes);
        UIUtils.showToastSafe("清洗臀部EB900100347D");
    }

    // 烘干臀部按钮
    @Click(R.id.btn_daxiaobian_honggantunbu)
    void honggantunbuButtonClicked() {
        sendbytes = new byte[]{(byte) 0xeb, (byte) 0x90, 0x0D, 0x00, 0x31, 0x7D};
        bleHelp.sendDatas(sendbytes);
        UIUtils.showToastSafe("烘干臀部EB900D00317D");
    }

    // 冲洗便垫按钮
    @Click(R.id.btn_daxiaobian_congxibiandian)
    void congxibiandianButtonClicked() {
        sendbytes = new byte[]{(byte) 0xeb, (byte) 0x90, 0x02, 0x00, 0x34, (byte) 0x8D};
        bleHelp.sendDatas(sendbytes);
        UIUtils.showToastSafe("冲洗便垫EB900200348D");
    }

    // 消毒杀菌按钮
    @Click(R.id.btn_daxiaobian_xiaodusajun)
    void xiaodusajunButtonClicked() {
        sendbytes = new byte[]{(byte) 0xeb, (byte) 0x90, 0x07, 0x00, 0x37, (byte) 0xDD};
        bleHelp.sendDatas(sendbytes);
        UIUtils.showToastSafe("消毒杀菌EB90070037DD");
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
        if (buttonFlag[7]) btn_daxiaobian_zidongculidaxiaobian.setBackgroundResource(R.drawable.btn_off);
        else btn_daxiaobian_zidongculidaxiaobian.setBackgroundResource(R.drawable.btn_on);
        buttonFlag[7] = !buttonFlag[7];
    }

    // 自动换气除臭按钮
    @Click(R.id.btn_daxiaobian_zidonghuanqicucou)
    void zidonghuanqicucouButtonClicked() {
        if (buttonFlag[8]) btn_daxiaobian_zidonghuanqicucou.setBackgroundResource(R.drawable.btn_off);
        else btn_daxiaobian_zidonghuanqicucou.setBackgroundResource(R.drawable.btn_on);
        buttonFlag[8] = !buttonFlag[8];
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
}
