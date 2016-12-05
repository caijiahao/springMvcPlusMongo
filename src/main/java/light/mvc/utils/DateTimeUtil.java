package light.mvc.utils;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ChenHehong on 2016/5/4.
 */
public class DateTimeUtil {

    public static String getCurrentDateStr(String format){
        if (format==null||format.length()==0){
            format = "yyyy-MM-dd";//定义默认格式
        }
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(today);
    }

    public static Date getCurrentDate(String format){
        if (format==null||format.length()==0){
            format = "yyyy-MM-dd";//定义默认格式
        }
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String todayString = sdf .format (today);
        Date todayFormat = null;
        try {
            todayFormat = sdf. parse(todayString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  todayFormat;
    }

//  获取当前时间几天前的日期时间
    public static Date getPreDate(int preDate,String format){
        if (format==null||format.length()==0){
            format = "yyyy-MM-dd";//定义默认格式
        }
        long preDateTime = System.currentTimeMillis()-preDate*24*60*60;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String d = sdf.format(preDateTime);
        Date date = null;
        try {
            date = sdf.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getPreDateStr(int preDate,String format){
        if (format==null||format.length()==0){
            format = "yyyy-MM-dd";//定义默认格式
        }
        BigInteger now = BigInteger.valueOf(System.currentTimeMillis());
        BigInteger preDateBig = BigInteger.valueOf(preDate);
        BigInteger dateTimeMillis = BigInteger.valueOf(24*60*60*1000);
        BigInteger gap = preDateBig.multiply(dateTimeMillis);
        BigInteger preDateTime = now. subtract(gap);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String d = sdf.format(preDateTime);
        return  d;
    }

}