package com.example.util;

public class MathUtil {


    //a/b转百分数字符串 (double)Math.round( a/(double)b*1000)/1000：保留3位小数四舍五入
    public static String toPercent(int a,int b) {
        if (a%b==0) {
            return a/b*100+"%";
        }else {
            return (double)Math.round( a/(double)b*1000)/10+"%";
        }
    }

    //Object转整数（失败返回null）
    private Integer toInt(Object o) {
        if (o!=null && !"".equals(o.toString())) {
            try {
                int parseInt = Integer.parseInt(o.toString());
                return parseInt;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
