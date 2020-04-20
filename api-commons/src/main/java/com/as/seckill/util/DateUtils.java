package com.as.seckill.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Calendar calendar =Calendar.getInstance();

    public static Long timestamp(String dateTime){
        return simpleFormat.parse(dateTime, new ParsePosition(0)).getTime();
    }

    public static String format(Object object){
        return simpleFormat.format(object);
    }

    public static long add(String dateTime, int unit, int amount) throws ParseException {
        Calendar calendar =Calendar.getInstance();
        Date date = simpleFormat.parse(dateTime);
        calendar.setTime(date);
        calendar.add(unit, amount);
        return calendar.getTime().getTime();
    }
}
