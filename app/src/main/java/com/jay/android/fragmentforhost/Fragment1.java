package com.jay.android.fragmentforhost;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fg1)
public class Fragment1 extends Fragment {
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
        if(buttonFlag[0]) btn_cuangti_qibei.setBackgroundResource(R.drawable.btn_stop);
        else btn_cuangti_qibei.setBackgroundResource(R.drawable.btn_cuangti_qibei);
        buttonFlag[0] = !buttonFlag[0];
    }

    // 躺平按钮
    @Click(R.id.btn_cuangti_tangping)
    void tangpingButtonClicked() {
        if(buttonFlag[1]) btn_cuangti_tangping.setBackgroundResource(R.drawable.btn_stop);
        else btn_cuangti_tangping.setBackgroundResource(R.drawable.btn_cuangti_tangping);
        buttonFlag[1] = !buttonFlag[1];
    }

    // 下腿按钮
    @Click(R.id.btn_cuangti_xiatui)
    void xiatuiButtonClicked() {
        if(buttonFlag[2]) btn_cuangti_xiatui.setBackgroundResource(R.drawable.btn_stop);
        else btn_cuangti_xiatui.setBackgroundResource(R.drawable.btn_cuangti_xiatui);
        buttonFlag[2] = !buttonFlag[2];
    }

    // 抬腿按钮
    @Click(R.id.btn_cuangti_taitui)
    void taituiButtonClicked() {
        if(buttonFlag[3]) btn_cuangti_taitui.setBackgroundResource(R.drawable.btn_stop);
        else btn_cuangti_taitui.setBackgroundResource(R.drawable.btn_cuangti_taitui);
        buttonFlag[3] = !buttonFlag[3];
    }

    // 左翻身按钮
    @Click(R.id.btn_cuangti_zuofansen)
    void zuofansenButtonClicked() {
        if(buttonFlag[4]) btn_cuangti_zuofansen.setBackgroundResource(R.drawable.btn_stop);
        else btn_cuangti_zuofansen.setBackgroundResource(R.drawable.btn_cuangti_zuofansen);
        buttonFlag[4] = !buttonFlag[4];
    }

    // 右翻身按钮
    @Click(R.id.btn_cuangti_youfansen)
    void youfansenButtonClicked() {
        if(buttonFlag[5]) btn_cuangti_youfansen.setBackgroundResource(R.drawable.btn_stop);
        else btn_cuangti_youfansen.setBackgroundResource(R.drawable.btn_cuangti_youfansen);
        buttonFlag[5] = !buttonFlag[5];
    }

    // 自动翻身按钮
    @Click(R.id.btn_cuangti_zidongfansen)
    void zidongfansenButtonClicked() {
        if(buttonFlag[6]) btn_cuangti_zidongfansen.setBackgroundResource(R.drawable.btn_off);
        else btn_cuangti_zidongfansen.setBackgroundResource(R.drawable.btn_on);
        buttonFlag[6] = !buttonFlag[6];
    }
}
