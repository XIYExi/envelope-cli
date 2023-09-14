package com.envelope.back.common.tools;

/**
 * 测试时用于生成虚拟电话号码的工具类
 */
public class PhoneTool {

    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }

    private static final String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153,181,182,183,184,185,186,187,188,189,171,172,173,174,175,176,177,178,179,180".split(",");

    public static Long getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return Long.parseLong(first+second+third);
    }
}

