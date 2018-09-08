package com.hz.syxx.other;

import java.util.Date;
import java.util.Locale;

/**
 * Created by Pefan_Li
 * Created Time 2018/9/8 19:10.
 */
public class StrFormat {
    public static void main(String[] args) {
        String str = null;

        str = String.format("格式参数$的使用：%1$d,%2$s", 99, "abc");           // 格式化字符串

        System.out.println(str);                                                                                     // 输出字符串变量

        System.out.printf("字符类型：%c %n", 'A');

        System.out.printf("布尔类型：%b %n", 3>7);

        System.out.printf("整数类型（十进制）：%d %n", -100/2);

        System.out.printf("整数类型（十六进制）：%x %n", 100);

        System.out.printf("浮点类型：%f %n", 100.00);

        System.out.printf("百分比类型: %d%% %n", 85);

        System.out.printf("左对齐: [%-5d] %n",111);

        System.out.printf("显示正负数的符号：%+d与%2$d %n", 99, -99);

        System.out.printf("数字前面补0：%03d %n", 7);

        System.out.printf("在整数之前添加指定数量的空格：[% 4d] %n", 7);

        System.out.printf("以“,”对数字分组：%,d%n", 9989997);

        System.out.printf("格式化浮点数：%2.3f %n", 49.8);

        System.out.printf("参数索引：%1$d,%2$s", 99,"abc");

        Date date=new Date();

        System.out.printf("全部日期和时间信息：%tc%n",date);

        System.out.printf("年-月-日格式：%tF%n",date);

        System.out.printf("月/日/年格式：%tD%n",date);

        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",date);

        System.out.printf("HH:MM:SS格式（24时制）：%tT%n",date);

        System.out.printf("HH:MM格式（24时制）：%tR",date);

        str=String.format(Locale.US,"英文月份简称：%tb",date);      // 格式化日期字符串

        System.out.println(str);                                                                              // 输出字符串内容

        System.out.printf("本地月份简称：%tb%n",date);

        str=String.format(Locale.US,"英文月份全称：%tB",date);

        System.out.println(str);

        System.out.printf("本地月份全称：%tB%n",date);

        str=String.format(Locale.US,"英文星期的简称：%ta",date);

        System.out.println(str);

        System.out.printf("本地星期的简称：%tA%n",date);

        System.out.printf("年的前两位数字（不足两位前面补0）：%tC%n",date);

        System.out.printf("年的后两位数字（不足两位前面补0）：%ty%n",date);

        System.out.printf("一年中的天数（即年的第几天）：%tj%n",date);

        System.out.printf("两位数字的月份（不足两位前面补0）：%tm%n",date);

        System.out.printf("两位数字的日（不足两位前面补0）：%td%n",date);

        System.out.printf("月份的日（前面不补0）：%te",date);

    }
}
