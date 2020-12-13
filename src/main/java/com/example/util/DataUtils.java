package com.example.util;

import org.springframework.stereotype.Controller;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DataUtils {

    //得到百分比数字
    public String percent(float remember,float number){
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到整数位
        numberFormat.setMaximumFractionDigits(1);
        String format = numberFormat.format(remember / number/ 0.01)+"%";
        return format;
    }
    //得到1-百分比数字
    public String unpercent(float remember,float number){
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到整数位
        numberFormat.setMaximumFractionDigits(1);
        String format = numberFormat.format((1-remember / number)/0.01)+"%";
        return format;
    }

    /**
     * 得到格式化日期
     */
    public Date getDate() throws ParseException {
        //得到当前日期- 目前是英文的Date
        Date date = new Date();

        //设置日期格式
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd" );
        //现在日期为yyyy-MM-dd，是String类型的
        String formatDate = format.format(date);
        //把String类型转为Date类型
        Date parse = format.parse(formatDate);

        return parse;
    }

}
