package com.jay.android.fragmentforhost;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fg2)
public class Fragment2 extends Fragment {
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

    Boolean buttonFlag[] = {true,true,true,true,true,true,true,true,true,true,true,true,true};

    @Click(R.id.ll_daxiaobian_soudong)
    void daxiaobianSoudongLayoutClicked() { setChioceItem(0); }

    @Click(R.id.ll_daxiaobian_zidong)
    void daxiaobianZidongLayoutClicked() { setChioceItem(1); }

    @Click(R.id.ll_daxiaobian_sezi)
    void daxiaobianSeziLayoutClicked() { setChioceItem(2); }

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
        if(buttonFlag[0]) btn_daxiaobian_dabianculi.setBackgroundResource(R.drawable.btn_stop);
        else btn_daxiaobian_dabianculi.setBackgroundResource(R.drawable.btn_daxiaobian_dabianculi);
        buttonFlag[0] = !buttonFlag[0];
    }

    // 小便处理按钮
    @Click(R.id.btn_daxiaobian_xiaobianculi)
    void xiaobianculiButtonClicked() {
        if(buttonFlag[1]) btn_daxiaobian_xiaobianculi.setBackgroundResource(R.drawable.btn_stop);
        else btn_daxiaobian_xiaobianculi.setBackgroundResource(R.drawable.btn_daxiaobian_xiaobianculi);
        buttonFlag[1] = !buttonFlag[1];
    }

    // 清洗臀部按钮
    @Click(R.id.btn_daxiaobian_qingxitunbu)
    void qingxitunbuButtonClicked() {
        if(buttonFlag[2]) btn_daxiaobian_qingxitunbu.setBackgroundResource(R.drawable.btn_stop);
        else btn_daxiaobian_qingxitunbu.setBackgroundResource(R.drawable.btn_daxiaobian_qingxitunbu);
        buttonFlag[2] = !buttonFlag[2];
    }

    // 烘干臀部按钮
    @Click(R.id.btn_daxiaobian_honggantunbu)
    void honggantunbuButtonClicked() {
        if(buttonFlag[3]) btn_daxiaobian_honggantunbu.setBackgroundResource(R.drawable.btn_stop);
        else btn_daxiaobian_honggantunbu.setBackgroundResource(R.drawable.btn_daxiaobian_honggantunbu);
        buttonFlag[3] = !buttonFlag[3];
    }

    // 冲洗便垫按钮
    @Click(R.id.btn_daxiaobian_congxibiandian)
    void congxibiandianButtonClicked() {
        if(buttonFlag[4]) btn_daxiaobian_congxibiandian.setBackgroundResource(R.drawable.btn_stop);
        else btn_daxiaobian_congxibiandian.setBackgroundResource(R.drawable.btn_daxiaobian_congxibiandian);
        buttonFlag[4] = !buttonFlag[4];
    }

    // 消毒杀菌按钮
    @Click(R.id.btn_daxiaobian_xiaodusajun)
    void xiaodusajunButtonClicked() {
        if(buttonFlag[5]) btn_daxiaobian_xiaodusajun.setBackgroundResource(R.drawable.btn_stop);
        else btn_daxiaobian_xiaodusajun.setBackgroundResource(R.drawable.btn_daxiaobian_xiaodusajun);
        buttonFlag[5] = !buttonFlag[5];
    }

    // 换气除臭按钮
    @Click(R.id.btn_daxiaobian_huanqicucou)
    void huanqicucouButtonClicked() {
        if(buttonFlag[6]) btn_daxiaobian_huanqicucou.setBackgroundResource(R.drawable.btn_stop);
        else btn_daxiaobian_huanqicucou.setBackgroundResource(R.drawable.btn_daxiaobian_huanqicucou);
        buttonFlag[6] = !buttonFlag[6];
    }

    // 自动处理大小便按钮
    @Click(R.id.btn_daxiaobian_zidongculidaxiaobian)
    void zidongculidaxiaobianButtonClicked() {
        if(buttonFlag[7]) btn_daxiaobian_zidongculidaxiaobian.setBackgroundResource(R.drawable.btn_off);
        else btn_daxiaobian_zidongculidaxiaobian.setBackgroundResource(R.drawable.btn_on);
        buttonFlag[7] = !buttonFlag[7];
    }

    // 自动换气除臭按钮
    @Click(R.id.btn_daxiaobian_zidonghuanqicucou)
    void zidonghuanqicucouButtonClicked() {
        if(buttonFlag[8]) btn_daxiaobian_zidonghuanqicucou.setBackgroundResource(R.drawable.btn_off);
        else btn_daxiaobian_zidonghuanqicucou.setBackgroundResource(R.drawable.btn_on);
        buttonFlag[8] = !buttonFlag[8];
    }

    // 尿失禁模式按钮
    @Click(R.id.btn_daxiaobian_niaosijinmosi)
    void niaosijinmosiButtonClicked() {
        if(buttonFlag[9]) btn_daxiaobian_niaosijinmosi.setBackgroundResource(R.drawable.btn_off);
        else btn_daxiaobian_niaosijinmosi.setBackgroundResource(R.drawable.btn_on);
        buttonFlag[9] = !buttonFlag[9];
    }

    // 女性模式按钮
    @Click(R.id.btn_daxiaobian_nvxingmosi)
    void nvxingmosiButtonClicked() {
        if(buttonFlag[10]) btn_daxiaobian_nvxingmosi.setBackgroundResource(R.drawable.btn_off);
        else btn_daxiaobian_nvxingmosi.setBackgroundResource(R.drawable.btn_on);
        buttonFlag[10] = !buttonFlag[10];
    }

    // 污水桶锁定按钮
    @Click(R.id.btn_daxiaobian_wusuitongsuiding)
    void wusuitongsuidingButtonClicked() {
        if(buttonFlag[11]) btn_daxiaobian_wusuitongsuiding.setBackgroundResource(R.drawable.btn_off);
        else btn_daxiaobian_wusuitongsuiding.setBackgroundResource(R.drawable.btn_on);
        buttonFlag[11] = !buttonFlag[11];
    }

    // 排水开关按钮
    @Click(R.id.btn_daxiaobian_paisuikaiguan)
    void paisuikaiguanButtonClicked() {
        if(buttonFlag[12]) btn_daxiaobian_paisuikaiguan.setBackgroundResource(R.drawable.btn_off);
        else btn_daxiaobian_paisuikaiguan.setBackgroundResource(R.drawable.btn_on);
        buttonFlag[12] = !buttonFlag[12];
    }
}
