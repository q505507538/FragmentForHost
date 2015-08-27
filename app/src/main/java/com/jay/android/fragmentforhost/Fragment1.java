package com.jay.android.fragmentforhost;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jay.android.fragmentforhost.Help.BLEHelp;
import com.jay.android.fragmentforhost.Help.CRCHelp;
import com.jay.android.fragmentforhost.Utils.UIUtils;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_1)
public class Fragment1 extends Fragment {
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
    @ViewById // 复位
    Button btn_cuangti_reset;

    Boolean buttonFlag[] = {true,true,true,true,true,true,true};

    @Click(R.id.ll_cuanti_soudong)
    void cuantiSoudongLayoutClicked() { setChioceItem(0); }

    @Click(R.id.ll_cuanti_zidong)
    void cuantiZidongLayoutClicked() {
        setChioceItem(1);
    }

    BLEHelp bleHelp = null;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        bleHelp = new BLEHelp(activity);
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
//            sendbytes = getSendDatas("b1010a010000001b11220d0a", 1, true);
            sendbytes = CRCHelp.CRC16("b1010a010000001b11220d0a");
            UIUtils.showToastSafe("起背开始b1010a010000001b11220d0a");
//            sendbytes = bleHelp.getSendDatas("b1010a010000001b11220d0a", 1, true);
            btn_cuangti_qibei.setBackgroundResource(R.drawable.btn_stop);
        } else {
//            sendbytes = getSendDatas("b1010a000000001b11220d0a", 1, true);
//            sendbytes = bleHelp.getSendDatas("b1010a000000001b11220d0a", 1, true);
            sendbytes = CRCHelp.CRC16("b1010a000000001b11220d0a");
            UIUtils.showToastSafe("起背暂停b1010a000000001b11220d0a");
            btn_cuangti_qibei.setBackgroundResource(R.drawable.btn_cuangti_qibei);
        }
        buttonFlag[0] = !buttonFlag[0];
        bleHelp.sendDatas(sendbytes);
    }

    // 躺平按钮
    @Click(R.id.btn_cuangti_tangping)
    void tangpingButtonClicked() {
        if(buttonFlag[1]) {
            sendbytes = CRCHelp.CRC16("b1010a000100001b11220d0a");
            UIUtils.showToastSafe("躺平开始b1010a000100001b11220d0a");
//            sendbytes = getSendDatas("b1010a000100001b11220d0a", 1, true);
            btn_cuangti_tangping.setBackgroundResource(R.drawable.btn_stop);
        } else {
            sendbytes = CRCHelp.CRC16("b1010a000000001b11220d0a");
            UIUtils.showToastSafe("躺平暂停b1010a000000001b11220d0a");
//            sendbytes = getSendDatas("b1010a000000001b11220d0a", 1, true);
            btn_cuangti_tangping.setBackgroundResource(R.drawable.btn_cuangti_tangping);
        }
        buttonFlag[1] = !buttonFlag[1];
        bleHelp.sendDatas(sendbytes);
    }

    // 下腿按钮
    @Click(R.id.btn_cuangti_xiatui)
    void xiatuiButtonClicked() {
        if(buttonFlag[2]) {
            sendbytes = CRCHelp.CRC16("b1010a000000011b11220d0a");
            UIUtils.showToastSafe("下腿开始b1010a000000011b11220d0a");
//            sendbytes = getSendDatas("b1010a000000011b11220d0a", 1, true);
            btn_cuangti_xiatui.setBackgroundResource(R.drawable.btn_stop);
        } else {
            sendbytes = CRCHelp.CRC16("b1010a000000001b11220d0a");
            UIUtils.showToastSafe("下腿暂停b1010a000000001b11220d0a");
//            sendbytes = getSendDatas("b1010a000000001b11220d0a", 1, true);
            btn_cuangti_xiatui.setBackgroundResource(R.drawable.btn_cuangti_xiatui);
        }
        buttonFlag[2] = !buttonFlag[2];
        bleHelp.sendDatas(sendbytes);
    }

    // 抬腿按钮
    @Click(R.id.btn_cuangti_taitui)
    void taituiButtonClicked() {
        if(buttonFlag[3]) {
            sendbytes = CRCHelp.CRC16("b1010a000000001b11220d0a");
            UIUtils.showToastSafe("抬腿开始b1010a000000001b11220d0a");
//            sendbytes = getSendDatas("b1010a000001001b11220d0a", 1, true);
            btn_cuangti_taitui.setBackgroundResource(R.drawable.btn_stop);
        } else {
            sendbytes = CRCHelp.CRC16("b1010a000000001b11220d0a");
            UIUtils.showToastSafe("抬腿暂停b1010a000000001b11220d0a");
//            sendbytes = getSendDatas("b1010a000000001b11220d0a", 1, true);
            btn_cuangti_taitui.setBackgroundResource(R.drawable.btn_cuangti_taitui);
        }
        buttonFlag[3] = !buttonFlag[3];
        bleHelp.sendDatas(sendbytes);
    }

    // 左翻身按钮
    @Click(R.id.btn_cuangti_zuofansen)
    void zuofansenButtonClicked() {
        if(buttonFlag[4]) {
            sendbytes = CRCHelp.CRC16("b2020a000100002b11220d0a");
            UIUtils.showToastSafe("左翻身开始b2020a000100002b11220d0a");
//            sendbytes = getSendDatas("b2020a000100002b11220d0a", 1, true);
            btn_cuangti_zuofansen.setBackgroundResource(R.drawable.btn_stop);
        } else {
            sendbytes = CRCHelp.CRC16("b2020a000000002b11220d0a");
            UIUtils.showToastSafe("左翻身暂停b2020a000000002b11220d0a");
//            sendbytes = getSendDatas("b2020a000000002b11220d0a", 1, true);
            btn_cuangti_zuofansen.setBackgroundResource(R.drawable.btn_cuangti_zuofansen);
        }
        buttonFlag[4] = !buttonFlag[4];
        bleHelp.sendDatas(sendbytes);
    }

    // 右翻身按钮
    @Click(R.id.btn_cuangti_youfansen)
    void youfansenButtonClicked() {
        if(buttonFlag[5]) {
            sendbytes = CRCHelp.CRC16("b2020a010000002b11220d0a");
            UIUtils.showToastSafe("右翻身开始b2020a010000002b11220d0a");
//            sendbytes = getSendDatas("b2020a010000002b11220d0a", 1, true);
            btn_cuangti_youfansen.setBackgroundResource(R.drawable.btn_stop);
        } else {
            sendbytes = CRCHelp.CRC16("b2020a000000002b11220d0a");
            UIUtils.showToastSafe("右翻身暂停b2020a000000002b11220d0a");
//            sendbytes = getSendDatas("b2020a000000002b11220d0a", 1, true);
            btn_cuangti_youfansen.setBackgroundResource(R.drawable.btn_cuangti_youfansen);
        }
        buttonFlag[5] = !buttonFlag[5];
        bleHelp.sendDatas(sendbytes);
    }

    // 自动翻身按钮
    @Click(R.id.btn_cuangti_zidongfansen)
    void zidongfansenButtonClicked() {
        if(buttonFlag[6]) {
            sendbytes = CRCHelp.CRC16("b2020a000000002b11220d0a");
            UIUtils.showToastSafe("自动翻身开始b2020a000000002b11220d0a");
//            sendbytes = getSendDatas("b2020a000001002b11220d0a", 1, true);
            btn_cuangti_zidongfansen.setBackgroundResource(R.drawable.btn_off);
        } else {
            sendbytes = CRCHelp.CRC16("b2020a000000002b11220d0a");
            UIUtils.showToastSafe("自动翻身暂停b2020a000000002b11220d0a");
//            sendbytes = getSendDatas("b2020a000000002b11220d0a", 1, true);
            btn_cuangti_zidongfansen.setBackgroundResource(R.drawable.btn_on);
        }
        buttonFlag[6] = !buttonFlag[6];
        bleHelp.sendDatas(sendbytes);
    }

    // 复位按钮
    @Click(R.id.btn_cuangti_reset)
    void resetButtonClicked() {
        sendbytes = CRCHelp.CRC16("b3030a010000003b11220d0a");
        UIUtils.showToastSafe("复位b3030a010000003b11220d0a");
        bleHelp.sendDatas(sendbytes);
    }
}
