package com.jay.android.fragmentforhost.Help;

public class DataHelp {
    public static Boolean isConnected = false;

    //起背
    public final static String CUANGTI_QIBEI_START_STR = "起背开始";
    public final static String CUANGTI_QIBEI_PAUSE_STR = "起背暂停";
    public final static byte[] CUANGTI_QIBEI_START = new byte[]{(byte) 0xb1,(byte) 0x01,(byte) 0x08,(byte) 0x01,(byte) 0x00,(byte) 0x1b,(byte) 0x35,(byte) 0x91,(byte) 0x0d,(byte) 0x0a};      //起背开始
    public final static byte[] CUANGTI_QIBEI_PAUSE = new byte[]{(byte) 0xb1,(byte) 0x01,(byte) 0x08,(byte) 0x00,(byte) 0x00,(byte) 0x1b,(byte) 0x64,(byte) 0x51,(byte) 0x0d,(byte) 0x0a};      //起背暂停
    public final static byte[] CUANGTI_QIBEI_ING = new byte[]{(byte) 0xb2,(byte) 0x01,(byte) 0x08,(byte) 0x02,(byte) 0x00,(byte) 0x2b,(byte) 0xb6,(byte) 0xc5};                                //起背执行中
    public final static byte[] CUANGTI_QIBEI_UNFINISH = new byte[]{(byte) 0xb2,(byte) 0x01,(byte) 0x08,(byte) 0x04,(byte) 0x00,(byte) 0x2b,(byte) 0xb7,(byte) 0x25};                           //起背暂停
    public final static byte[] CUANGTI_QIBEI_FINISH = new byte[]{(byte) 0xb2,(byte) 0x01,(byte) 0x08,(byte) 0x08,(byte) 0x00,(byte) 0x2b,(byte) 0xb4,(byte) 0xe5};                             //起背完成
    //躺平
    public final static String CUANGTI_PINGTANG_START_STR = "躺平开始";
    public final static String CUANGTI_PINGTANG_PAUSE_STR = "躺平暂停";
    public final static byte[] CUANGTI_PINGTANG_START = new byte[]{(byte) 0xb1,(byte) 0x02,(byte) 0x08,(byte) 0x01,(byte) 0x00,(byte) 0x1b,(byte) 0x71,(byte) 0x91,(byte) 0x0d,(byte) 0x0a};   //躺平开始
    public final static byte[] CUANGTI_PINGTANG_PAUSE = new byte[]{(byte) 0xb1,(byte) 0x02,(byte) 0x08,(byte) 0x00,(byte) 0x00,(byte) 0x1b,(byte) 0x20,(byte) 0x51,(byte) 0x0d,(byte) 0x0a};   //躺平暂停
    public final static byte[] CUANGTI_PINGTANG_ING = new byte[]{(byte) 0xb2,(byte) 0x02,(byte) 0x08,(byte) 0x02,(byte) 0x00,(byte) 0x2b,(byte) 0xb6,(byte) 0x81};                             //躺平执行中
    public final static byte[] CUANGTI_PINGTANG_UNFINISH = new byte[]{(byte) 0xb2,(byte) 0x02,(byte) 0x08,(byte) 0x04,(byte) 0x00,(byte) 0x2b,(byte) 0xb7,(byte) 0x61};                        //躺平暂停
    public final static byte[] CUANGTI_PINGTANG_FINISH = new byte[]{(byte) 0xb2,(byte) 0x02,(byte) 0x08,(byte) 0x08,(byte) 0x00,(byte) 0x2b,(byte) 0xb4,(byte) 0xa1};                          //躺平完成
    //折腿
    public final static String CUANGTI_ZETUI_START_STR = "折腿开始";
    public final static String CUANGTI_ZETUI_PAUSE_STR = "折腿暂停";
    public final static byte[] CUANGTI_ZETUI_START = new byte[]{(byte) 0xb1,(byte) 0x04,(byte) 0x08,(byte) 0x01,(byte) 0x00,(byte) 0x1b,(byte) 0xf9,(byte) 0x91,(byte) 0x0d,(byte) 0x0a};     //折腿开始
    public final static byte[] CUANGTI_ZETUI_PAUSE = new byte[]{(byte) 0xb1,(byte) 0x04,(byte) 0x08,(byte) 0x00,(byte) 0x00,(byte) 0x1b,(byte) 0xa8,(byte) 0x51,(byte) 0x0d,(byte) 0x0a};     //折腿暂停
    public final static byte[] CUANGTI_ZETUI_ING = new byte[]{(byte) 0xb2,(byte) 0x04,(byte) 0x08,(byte) 0x02,(byte) 0x00,(byte) 0x2b,(byte) 0xb6,(byte) 0x09};                               //折腿执行中
    public final static byte[] CUANGTI_ZETUI_UNFINISH = new byte[]{(byte) 0xb2,(byte) 0x04,(byte) 0x08,(byte) 0x04,(byte) 0x00,(byte) 0x2b,(byte) 0xb7,(byte) 0xe9};                          //折腿暂停
    public final static byte[] CUANGTI_ZETUI_FINISH = new byte[]{(byte) 0xb2,(byte) 0x04,(byte) 0x08,(byte) 0x08,(byte) 0x00,(byte) 0x2b,(byte) 0xb4,(byte) 0x29};                            //折腿完成
    //抬腿
    public final static String CUANGTI_TAITUI_START_STR = "抬腿开始";
    public final static String CUANGTI_TAITUI_PAUSE_STR = "抬腿暂停";
    public final static byte[] CUANGTI_TAITUI_START = new byte[]{(byte) 0xb1,(byte) 0x03,(byte) 0x08,(byte) 0x01,(byte) 0x00,(byte) 0x1b,(byte) 0x4c,(byte) 0x51,(byte) 0x0d,(byte) 0x0a};     //抬腿开始
    public final static byte[] CUANGTI_TAITUI_PAUSE = new byte[]{(byte) 0xb1,(byte) 0x03,(byte) 0x08,(byte) 0x00,(byte) 0x00,(byte) 0x1b,(byte) 0x1d,(byte) 0x91,(byte) 0x0d,(byte) 0x0a};     //抬腿暂停
    public final static byte[] CUANGTI_TAITUI_ING = new byte[]{(byte) 0xb2,(byte) 0x03,(byte) 0x08,(byte) 0x02,(byte) 0x00,(byte) 0x2b,(byte) 0x76,(byte) 0xbc};                               //抬腿执行中
    public final static byte[] CUANGTI_TAITUI_UNFINISH = new byte[]{(byte) 0xb2,(byte) 0x03,(byte) 0x08,(byte) 0x04,(byte) 0x00,(byte) 0x2b,(byte) 0x77,(byte) 0x5c};                          //抬腿暂停
    public final static byte[] CUANGTI_TAITUI_FINISH = new byte[]{(byte) 0xb2,(byte) 0x03,(byte) 0x08,(byte) 0x08,(byte) 0x00,(byte) 0x2b,(byte) 0x74,(byte) 0x9c};                            //抬腿完成
    //右翻身
    public final static String CUANGTI_YOUFANSEN_START_STR = "右翻身开始";
    public final static String CUANGTI_YOUFANSEN_PAUSE_STR = "右翻身暂停";
    public final static byte[] CUANGTI_YOUFANSEN_START = new byte[]{(byte) 0xb1,(byte) 0x05,(byte) 0x08,(byte) 0x01,(byte) 0x00,(byte) 0x1b,(byte) 0xc4,(byte) 0x51,(byte) 0x0d,(byte) 0x0a};  //右翻身开始
    public final static byte[] CUANGTI_YOUFANSEN_PAUSE = new byte[]{(byte) 0xb1,(byte) 0x05,(byte) 0x08,(byte) 0x00,(byte) 0x00,(byte) 0x1b,(byte) 0x95,(byte) 0x91,(byte) 0x0d,(byte) 0x0a};  //右翻身暂停
    public final static byte[] CUANGTI_YOUFANSEN_ING = new byte[]{(byte) 0xb2,(byte) 0x05,(byte) 0x08,(byte) 0x02,(byte) 0x00,(byte) 0x2b,(byte) 0x76,(byte) 0x34};                            //右翻身执行中
    public final static byte[] CUANGTI_YOUFANSEN_UNFINISH = new byte[]{(byte) 0xb2,(byte) 0x05,(byte) 0x08,(byte) 0x04,(byte) 0x00,(byte) 0x2b,(byte) 0x77,(byte) 0xd4};                       //右翻身暂停
    public final static byte[] CUANGTI_YOUFANSEN_FINISH = new byte[]{(byte) 0xb2,(byte) 0x05,(byte) 0x08,(byte) 0x08,(byte) 0x00,(byte) 0x2b,(byte) 0x74,(byte) 0x14};                         //右翻身完成
    //左翻身
    public final static String CUANGTI_ZUOFANSEN_START_STR = "左翻身开始";
    public final static String CUANGTI_ZUOFANSEN_PAUSE_STR = "左翻身暂停";
    public final static byte[] CUANGTI_ZUOFANSEN_START = new byte[]{(byte) 0xb1,(byte) 0x06,(byte) 0x08,(byte) 0x01,(byte) 0x00,(byte) 0x1b,(byte) 0x80,(byte) 0x51,(byte) 0x0d,(byte) 0x0a};  //左翻身开始
    public final static byte[] CUANGTI_ZUOFANSEN_PAUSE = new byte[]{(byte) 0xb1,(byte) 0x06,(byte) 0x08,(byte) 0x00,(byte) 0x00,(byte) 0x1b,(byte) 0xd1,(byte) 0x91,(byte) 0x0d,(byte) 0x0a};  //左翻身暂停
    public final static byte[] CUANGTI_ZUOFANSEN_ING = new byte[]{(byte) 0xb2,(byte) 0x06,(byte) 0x08,(byte) 0x02,(byte) 0x00,(byte) 0x2b,(byte) 0x76,(byte) 0x70};                            //左翻身执行中
    public final static byte[] CUANGTI_ZUOFANSEN_UNFINISH = new byte[]{(byte) 0xb2,(byte) 0x06,(byte) 0x08,(byte) 0x04,(byte) 0x00,(byte) 0x2b,(byte) 0x77,(byte) 0x90};                       //左翻身暂停
    public final static byte[] CUANGTI_ZUOFANSEN_FINISH = new byte[]{(byte) 0xb2,(byte) 0x06,(byte) 0x08,(byte) 0x08,(byte) 0x00,(byte) 0x2b,(byte) 0x74,(byte) 0x50};                         //左翻身完成
    //自动翻身
    public final static String CUANGTI_FANSEN_AUTO_START_STR = "自动翻身开始";
    public final static String CUANGTI_FANSEN_AUTO_PAUSE_STR = "自动翻身暂停";
    public final static byte[] CUANGTI_FANSEN_AUTO_START = new byte[]{(byte) 0xb1,(byte) 0x07,(byte) 0x08,(byte) 0x01,(byte) 0x00,(byte) 0x1b,(byte) 0xbd,(byte) 0x91,(byte) 0x0d,(byte) 0x0a};//自动翻身开始
    public final static byte[] CUANGTI_FANSEN_AUTO_PAUSE = new byte[]{(byte) 0xb1,(byte) 0x07,(byte) 0x08,(byte) 0x00,(byte) 0x00,(byte) 0x1b,(byte) 0xec,(byte) 0x51,(byte) 0x0d,(byte) 0x0a};//自动翻身暂停
    public final static byte[] CUANGTI_FANSEN_AUTO_ING = new byte[]{(byte) 0xb2,(byte) 0x07,(byte) 0x08,(byte) 0x02,(byte) 0x00,(byte) 0x2b,(byte) 0xb6,(byte) 0x4d};                          //自动翻身执行中
    public final static byte[] CUANGTI_FANSEN_AUTO_UNFINISH = new byte[]{(byte) 0xb2,(byte) 0x07,(byte) 0x08,(byte) 0x04,(byte) 0x00,(byte) 0x2b,(byte) 0xb7,(byte) 0xad};                     //自动翻身暂停
    public final static byte[] CUANGTI_FANSEN_AUTO_FINISH = new byte[]{(byte) 0xb2,(byte) 0x07,(byte) 0x08,(byte) 0x08,(byte) 0x00,(byte) 0x2b,(byte) 0xb4,(byte) 0x6d};                       //自动翻身完成
    //复位
    public final static String CUANGTI_RESET_START_STR = "复位开始";
    public final static byte[] CUANGTI_RESET_START = new byte[]{(byte) 0xb1,(byte) 0x08,(byte) 0x08,(byte) 0x01,(byte) 0x00,(byte) 0x1b,(byte) 0xe9,(byte) 0x90,(byte) 0x0d,(byte) 0x0a};      //复位开始
    public final static byte[] CUANGTI_RESET_ING = new byte[]{(byte) 0xb2,(byte) 0x08,(byte) 0x08,(byte) 0x02,(byte) 0x00,(byte) 0x2b,(byte) 0xb7,(byte) 0x19};                                //复位执行中
    public final static byte[] CUANGTI_RESET_FINISH = new byte[]{(byte) 0xb2,(byte) 0x08,(byte) 0x08,(byte) 0x08,(byte) 0x00,(byte) 0x2b,(byte) 0xb5,(byte) 0x39};                             //复位完成
    //急停
    public final static String CUANGTI_STOP_START_STR = "急停开始";
    public final static byte[] CUANGTI_STOP_START = new byte[]{(byte) 0xb1,(byte) 0x09,(byte) 0x08,(byte) 0x01,(byte) 0x01,(byte) 0x1b,(byte) 0xd5,(byte) 0xc0,(byte) 0x0d,(byte) 0x0a};       //急停开始
    public final static byte[] CUANGTI_STOP_ING = new byte[]{(byte) 0xb2,(byte) 0x09,(byte) 0x08,(byte) 0x02,(byte) 0x00,(byte) 0x2b,(byte) 0x77,(byte) 0x24};                                 //急停执行中
    public final static byte[] CUANGTI_STOP_FINISH = new byte[]{(byte) 0xb2,(byte) 0x09,(byte) 0x08,(byte) 0x08,(byte) 0x00,(byte) 0x2b,(byte) 0x75,(byte) 0x04};                              //急停完成




    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 冲洗臀部
    public final static String DAXIAOBIAN_CONGXITUNBU_STR = "冲洗臀部";
    public final static byte[] DAXIAOBIAN_CONGXITUNBU = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x01, (byte) 0x00, (byte) 0x34, (byte) 0x7D};                                   // 冲洗臀部
    public final static byte[] DAXIAOBIAN_CONGXITUNBU_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x9A, (byte) 0xA2};                  // 执行中
    public final static byte[] DAXIAOBIAN_CONGXITUNBU_UNFINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x01, (byte) 0x01, (byte) 0x00, (byte) 0x5B, (byte) 0x62};             // 未完成退出
    public final static byte[] DAXIAOBIAN_CONGXITUNBU_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x01, (byte) 0x01, (byte) 0xFF, (byte) 0x1B, (byte) 0x22};               // 执行完成

    // 冲洗便垫
    public final static String DAXIAOBIAN_CONGXIBIANDIAN_STR = "冲洗便垫";
    public final static byte[] DAXIAOBIAN_CONGXIBIANDIAN = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x02, (byte) 0x00, (byte) 0x34, (byte) 0x8D};                                // 冲洗便垫
    public final static byte[] DAXIAOBIAN_CONGXIBIANDIAN_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x02, (byte) 0x01, (byte) 0x01, (byte) 0x6A, (byte) 0xA2};               // 执行中
    public final static byte[] DAXIAOBIAN_CONGXIBIANDIAN_UNFINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x02, (byte) 0x01, (byte) 0x00, (byte) 0xAB, (byte) 0x62};          // 未完成退出
    public final static byte[] DAXIAOBIAN_CONGXIBIANDIAN_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x02, (byte) 0x01, (byte) 0xFF, (byte) 0xEB, (byte) 0x22};            // 执行完成

    // 大便模式
    public final static String DAXIAOBIAN_DABIANMOSI_STR = "大便处理";
    public final static byte[] DAXIAOBIAN_DABIANMOSI = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x03, (byte) 0x00, (byte) 0x35, (byte) 0x1D};                                    // 大便模式
    public final static byte[] DAXIAOBIAN_DABIANMOSI_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x03, (byte) 0x01, (byte) 0x01, (byte) 0x3B, (byte) 0x62};                   // 执行中
    public final static byte[] DAXIAOBIAN_DABIANMOSI_UNFINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x03, (byte) 0x01, (byte) 0x00, (byte) 0xFA, (byte) 0xA2};              // 未完成退出
    public final static byte[] DAXIAOBIAN_DABIANMOSI_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x03, (byte) 0x01, (byte) 0xFF, (byte) 0xBA, (byte) 0xE2};                // 执行完成

    // 尿失禁模式
    public final static String DAXIAOBIAN_NIAOSIJINMOSI_STR = "尿失禁模式";
    public final static byte[] DAXIAOBIAN_NIAOSIJINMOSI = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x04, (byte) 0x00, (byte) 0x37, (byte) 0x2D};                                 // 尿失禁模式
    public final static byte[] DAXIAOBIAN_NIAOSIJINMOSI_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x04, (byte) 0x01, (byte) 0x01, (byte) 0x8A, (byte) 0xA3};                // 执行中
    public final static byte[] DAXIAOBIAN_NIAOSIJINMOSI_UNFINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x04, (byte) 0x01, (byte) 0x00, (byte) 0x4B, (byte) 0x63};           // 未完成退出
    public final static byte[] DAXIAOBIAN_NIAOSIJINMOSI_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x04, (byte) 0x01, (byte) 0xFF, (byte) 0x0B, (byte) 0x23};             // 执行完成

    // 女性小便模式
    public final static String DAXIAOBIAN_NVXINGXIAOBIANMOSI_STR = "女性小便模式";
    public final static byte[] DAXIAOBIAN_NVXINGXIAOBIANMOSI = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x05, (byte) 0x00, (byte) 0x36, (byte) 0xBD};                            // 女性小便模式
    public final static byte[] DAXIAOBIAN_NVXINGXIAOBIANMOSI_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x05, (byte) 0x01, (byte) 0x01, (byte) 0xDB, (byte) 0x63};           // 执行中
    public final static byte[] DAXIAOBIAN_NVXINGXIAOBIANMOSI_UNFINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x05, (byte) 0x01, (byte) 0x00, (byte) 0x1A, (byte) 0xA3};      // 未完成退出
    public final static byte[] DAXIAOBIAN_NVXINGXIAOBIANMOSI_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x05, (byte) 0x01, (byte) 0xFF, (byte) 0x5A, (byte) 0xE3};        // 执行完成

    // 男性小便模式
    public final static String DAXIAOBIAN_NANXINGXIAOBIANMOSI_STR = "男性小便模式";
    public final static byte[] DAXIAOBIAN_NANXINGXIAOBIANMOSI = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x06, (byte) 0x00, (byte) 0x36, (byte) 0x4D};                           // 男性小便模式
    public final static byte[] DAXIAOBIAN_NANXINGXIAOBIANMOSI_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x06, (byte) 0x01, (byte) 0x01, (byte) 0x2B, (byte) 0x63};          // 执行中
    public final static byte[] DAXIAOBIAN_NANXINGXIAOBIANMOSI_UNFINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x06, (byte) 0x01, (byte) 0x00, (byte) 0xEA, (byte) 0xA3};     // 未完成退出
    public final static byte[] DAXIAOBIAN_NANXINGXIAOBIANMOSI_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x06, (byte) 0x01, (byte) 0xFF, (byte) 0xAA, (byte) 0xE3};       // 执行完成

    // 清洁杀菌模式
    public final static String DAXIAOBIAN_QINGJIESAJUNMOSI_STR = "清洁杀菌模式";
    public final static byte[] DAXIAOBIAN_QINGJIESAJUNMOSI = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x07, (byte) 0x00, (byte) 0x37, (byte) 0xDD};                              // 清洁杀菌模式
    public final static byte[] DAXIAOBIAN_QINGJIESAJUNMOSI_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x07, (byte) 0x01, (byte) 0x01, (byte) 0x7A, (byte) 0xA3};             // 执行中
    public final static byte[] DAXIAOBIAN_QINGJIESAJUNMOSI_UNFINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x07, (byte) 0x01, (byte) 0x00, (byte) 0xBB, (byte) 0x63};        // 未完成退出
    public final static byte[] DAXIAOBIAN_QINGJIESAJUNMOSI_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x07, (byte) 0x01, (byte) 0xFF, (byte) 0xFB, (byte) 0x23};          // 执行完成

    // 排水模式
    public final static String DAXIAOBIAN_PAISUIMOSI_STR = "排水";
    public final static byte[] DAXIAOBIAN_PAISUIMOSI = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x08, (byte) 0x00, (byte) 0x32, (byte) 0x2D};                                    // 排水模式
    public final static byte[] DAXIAOBIAN_PAISUIMOSI_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x08, (byte) 0x01, (byte) 0x01, (byte) 0x4A, (byte) 0xA0};                   // 执行中
    public final static byte[] DAXIAOBIAN_PAISUIMOSI_UNFINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x08, (byte) 0x01, (byte) 0x00, (byte) 0x8B, (byte) 0x60};              // 未完成退出
    public final static byte[] DAXIAOBIAN_PAISUIMOSI_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x08, (byte) 0x01, (byte) 0xFF, (byte) 0xCB, (byte) 0x20};                // 执行完成

    // 自动换气模式  开
    public final static String DAXIAOBIAN_ZIDONGHUANQIMOSI_ON_STR = "自动换气模式  开";
    public final static byte[] DAXIAOBIAN_ZIDONGHUANQIMOSI_ON = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x09, (byte) 0x00, (byte) 0x33, (byte) 0xBD};                           // 自动换气模式  开
    public final static byte[] DAXIAOBIAN_ZIDONGHUANQIMOSI_ON_OK = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x09, (byte) 0x01, (byte) 0xFF, (byte) 0x9A, (byte) 0xE0};           // 自动换气模式  开 确认

    // 自动换气模式  关
    public final static String DAXIAOBIAN_ZIDONGHUANQIMOSI_OFF_STR = "自动换气模式  关";
    public final static byte[] DAXIAOBIAN_ZIDONGHUANQIMOSI_OFF = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x0A, (byte) 0x00, (byte) 0x33, (byte) 0x4D};                          // 自动换气模式  关
    public final static byte[] DAXIAOBIAN_ZIDONGHUANQIMOSI_OFF_OK = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x0A, (byte) 0x01, (byte) 0xFF, (byte) 0x6A, (byte) 0xE0};          // 自动换气模式  关  确认

    // 出桶
    public final static String DAXIAOBIAN_CUTONG_STR = "出桶";
    public final static byte[] DAXIAOBIAN_CUTONG = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x0B, (byte) 0x00, (byte) 0x32, (byte) 0xDD};                                        // 出桶
    public final static byte[] DAXIAOBIAN_CUTONG_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x0B, (byte) 0x01, (byte) 0x01, (byte) 0xBA, (byte) 0xA0};                       // 执行中
    public final static byte[] DAXIAOBIAN_CUTONG_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x0B, (byte) 0x01, (byte) 0xFF, (byte) 0x3B, (byte) 0x20};                    // 执行完成

    // 入桶
    public final static String DAXIAOBIAN_RUTONG_STR = "入桶";
    public final static byte[] DAXIAOBIAN_RUTONG = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x0C, (byte) 0x00, (byte) 0x30, (byte) 0xED};                                        // 入桶
    public final static byte[] DAXIAOBIAN_RUTONG_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x0C, (byte) 0x01, (byte) 0x01, (byte) 0x0B, (byte) 0x61};                       // 执行中
    public final static byte[] DAXIAOBIAN_RUTONG_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x0C, (byte) 0x01, (byte) 0xFF, (byte) 0x8A, (byte) 0xE1};                    // 执行完成

    // 通风干燥
    public final static String DAXIAOBIAN_TONGFENGGANZAO_STR = "通风干燥";
    public final static byte[] DAXIAOBIAN_TONGFENGGANZAO = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x0D, (byte) 0x00, (byte) 0x31, (byte) 0x7D};                                // 通风干燥
    public final static byte[] DAXIAOBIAN_TONGFENGGANZAO_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x0D, (byte) 0x01, (byte) 0x01, (byte) 0x5A, (byte) 0xA1};               // 执行中
    public final static byte[] DAXIAOBIAN_TONGFENGGANZAO_UNFINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x0D, (byte) 0x01, (byte) 0x00, (byte) 0x9B, (byte) 0x61};          // 未完成
    public final static byte[] DAXIAOBIAN_TONGFENGGANZAO_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x0D, (byte) 0x01, (byte) 0xFF, (byte) 0xDB, (byte) 0x21};            // 完成

    // 停止模式
    public final static String DAXIAOBIAN_TINGZIMOSI_STR = "停止模式";
    public final static byte[] DAXIAOBIAN_TINGZIMOSI = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x11, (byte) 0x00, (byte) 0x39, (byte) 0xBD};                                    // 停止模式
    public final static byte[] DAXIAOBIAN_TINGZIMOSI_OK = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x11, (byte) 0x01, (byte) 0xFF, (byte) 0x1A, (byte) 0xE7};                    // 停止模式  确认

    // 停止解除
    public final static String DAXIAOBIAN_TINGZIJIECU_STR = "停止解除";
    public final static byte[] DAXIAOBIAN_TINGZIJIECU = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x22, (byte) 0x00, (byte) 0x2D, (byte) 0x4D};                                   // 停止解除
    public final static byte[] DAXIAOBIAN_TINGZIJIECU_OK = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x22, (byte) 0x01, (byte) 0xFF, (byte) 0xEA, (byte) 0xE8};                   // 停止解除  确认

    // (自动)大便模式
    public final static String DAXIAOBIAN_DABIANMOSI_AUTO_STR = "(自动)大便模式";
    public final static byte[] DAXIAOBIAN_DABIANMOSI_AUTO_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x50, (byte) 0x01, (byte) 0x01, (byte) 0xCB, (byte) 0x73};              // (自动)大便模式  进行中
    public final static byte[] DAXIAOBIAN_DABIANMOSI_AUTO_UNFINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x50, (byte) 0x01, (byte) 0x00, (byte) 0x0A, (byte) 0xB3};         // 未完成
    public final static byte[] DAXIAOBIAN_DABIANMOSI_AUTO_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x50, (byte) 0x01, (byte) 0xFF, (byte) 0x4A, (byte) 0xF3};           // 完成

    // (自动)尿失禁模式
    public final static byte[] DAXIAOBIAN_NIAOSIJINMOSI_AUTO_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x51, (byte) 0x01, (byte) 0x01, (byte) 0x9A, (byte) 0xB3};           // (自动)尿失禁模式  进行中
    public final static byte[] DAXIAOBIAN_NIAOSIJINMOSI_AUTO_UNFINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x51, (byte) 0x01, (byte) 0x00, (byte) 0x5B, (byte) 0x73};      // 未完成
    public final static byte[] DAXIAOBIAN_NIAOSIJINMOSI_AUTO_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x51, (byte) 0x01, (byte) 0xFF, (byte) 0x1B, (byte) 0x33};        // 完成

    // (自动)女性小便模式
    public final static byte[] DAXIAOBIAN_NVXINGXIAOBIANMOSI_AUTO_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x52, (byte) 0x01, (byte) 0x01, (byte) 0x6A, (byte) 0xB3};      // (自动)女性小便模式  进行中
    public final static byte[] DAXIAOBIAN_NVXINGXIAOBIANMOSI_AUTO_UNFINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x52, (byte) 0x01, (byte) 0x00, (byte) 0xAB, (byte) 0x73}; // 未完成
    public final static byte[] DAXIAOBIAN_NVXINGXIAOBIANMOSI_AUTO_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x52, (byte) 0x01, (byte) 0xFF, (byte) 0xEB, (byte) 0x33};   // 完成

    // (自动)男性小便模式
    public final static byte[] DAXIAOBIAN_NANXINGXIAOBIANMOSI_AUTO_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x53, (byte) 0x01, (byte) 0x01, (byte) 0x3B, (byte) 0x73};     // (自动)男性小便模式  进行中
    public final static byte[] DAXIAOBIAN_NANXINGXIAOBIANMOSI_AUTO_UNFINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x53, (byte) 0x01, (byte) 0x00, (byte) 0xFA, (byte) 0xB3};// 未完成
    public final static byte[] DAXIAOBIAN_NANXINGXIAOBIANMOSI_AUTO_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x53, (byte) 0x01, (byte) 0xFF, (byte) 0xBA, (byte) 0xF3};  // 完成
}
