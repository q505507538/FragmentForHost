package com.jay.android.fragmentforhost.Help;

public class DataHelp {
    public static Boolean isConnected = false;

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
    public final static String DAXIAOBIAN_NANXINGXIAOBIANMOSI_STR = "女性小便模式";
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
    public final static byte[] DAXIAOBIAN_PAISUIMOSI = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x08, (byte) 0x00, (byte) 0x32, (byte) 0x2D};                                    // 排水模式
    public final static byte[] DAXIAOBIAN_PAISUIMOSI_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x08, (byte) 0x01, (byte) 0x01, (byte) 0x4A, (byte) 0xA0};                   // 执行中
    public final static byte[] DAXIAOBIAN_PAISUIMOSI_UNFINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x08, (byte) 0x01, (byte) 0x00, (byte) 0x8B, (byte) 0x60};              // 未完成退出
    public final static byte[] DAXIAOBIAN_PAISUIMOSI_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x08, (byte) 0x01, (byte) 0xFF, (byte) 0xCB, (byte) 0x20};                // 执行完成

    // 自动换气模式  开
    public final static byte[] DAXIAOBIAN_ZIDONGHUANQIMOSI_ON = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x09, (byte) 0x00, (byte) 0x33, (byte) 0xBD};                           // 自动换气模式  开
    public final static byte[] DAXIAOBIAN_ZIDONGHUANQIMOSI_ON_OK = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x09, (byte) 0x01, (byte) 0xFF, (byte) 0x9A, (byte) 0xE0};           // 自动换气模式  开 确认

    // 自动换气模式  关
    public final static byte[] DAXIAOBIAN_ZIDONGHUANQIMOSI_OFF = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x0A, (byte) 0x00, (byte) 0x33, (byte) 0x4D};                          // 自动换气模式  关
    public final static byte[] DAXIAOBIAN_ZIDONGHUANQIMOSI_OFF_OK = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x0A, (byte) 0x01, (byte) 0xFF, (byte) 0x6A, (byte) 0xE0};          // 自动换气模式  关  确认

    // 出桶
    public final static byte[] DAXIAOBIAN_CUTONG = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x0B, (byte) 0x00, (byte) 0x32, (byte) 0xDD};                                        // 出桶
    public final static byte[] DAXIAOBIAN_CUTONG_ING = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x0B, (byte) 0x01, (byte) 0x01, (byte) 0xBA, (byte) 0xA0};                       // 执行中
    public final static byte[] DAXIAOBIAN_CUTONG_FINISH = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x0B, (byte) 0x01, (byte) 0xFF, (byte) 0x3B, (byte) 0x20};                    // 执行完成

    // 入桶
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
    public final static byte[] DAXIAOBIAN_TINGZIMOSI = new byte[]{(byte) 0xEB, (byte) 0x90, (byte) 0x11, (byte) 0x00, (byte) 0x39, (byte) 0xBD};                                    // 停止模式
    public final static byte[] DAXIAOBIAN_TINGZIMOSI_OK = new byte[]{(byte) 0xB2, (byte) 0x4E, (byte) 0x11, (byte) 0x01, (byte) 0xFF, (byte) 0x1A, (byte) 0xE7};                    // 停止模式  确认

    // 停止解除
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
