package com.jay.android.fragmentforhost;

import android.support.v4.app.Fragment;
import android.widget.Button;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fg3)
public class Fragment3 extends Fragment {
    @ViewById   // 机器准备按钮
    Button btn_sangzikangfu_jiqizunbei;
    @ViewById   // 开始锻炼按钮
    Button btn_sangzikangfu_kaisiduanlian;

    Boolean buttonFlag[] = {true,true};

    // 机器准备按钮
    @Click(R.id.btn_sangzikangfu_jiqizunbei)
    void jiqizunbeiButtonClicked() {
        if(buttonFlag[0]) btn_sangzikangfu_jiqizunbei.setBackgroundResource(R.drawable.btn_jiqizunbei_disable);
        else btn_sangzikangfu_jiqizunbei.setBackgroundResource(R.drawable.btn_jiqizunbei_normal);
        buttonFlag[0] = !buttonFlag[0];
    }

    // 开始锻炼按钮
    @Click(R.id.btn_sangzikangfu_kaisiduanlian)
    void kaisiduanlianButtonClicked() {
        if(buttonFlag[1]) btn_sangzikangfu_kaisiduanlian.setBackgroundResource(R.drawable.btn_kaisiduanlian_press);
        else btn_sangzikangfu_kaisiduanlian.setBackgroundResource(R.drawable.btn_kaisiduanlian_normal);
        buttonFlag[1] = !buttonFlag[1];
    }
}
