package com.lida.mongo.util;

import java.math.BigDecimal;

public class Count {
    //������λС��
    public double getnum(String num) {
        String str;
        String a = num;
        if (a.indexOf(".") == -1)
            str = a + ".00";
        else {
            String b = a.substring(a.indexOf("."));

            if (b.length() > 2)
                str = a.substring(0, a.indexOf(".") + 3);
            else if (b.length() > 1)
                str = a + "0";
            else
                str = a + ".00";
        }
        return Double.valueOf(str);
    }

    /**
     * double ���
     *
     * @param d1
     * @param d2
     * @return
     */
    public double add(double a, double b) {
        BigDecimal bd1 = new BigDecimal(Double.toString(a));
        BigDecimal bd2 = new BigDecimal(Double.toString(b));
        return getnum(bd1.add(bd2).toString());
    }

    /**
     * double ���
     *
     * @param d1
     * @param d2
     * @return
     */
    public double sub(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return getnum(bd1.subtract(bd2).toString());
    }

    /**
     * double ���
     *
     * @param d1
     * @param d2
     * @return
     */
    public double mul(double a, double b) {
        BigDecimal bd1 = new BigDecimal(Double.toString(a));
        BigDecimal bd2 = new BigDecimal(Double.toString(b));
        return getnum(bd1.multiply(bd2).toString());
    }

    /**
     * double ���
     *
     * @param d1
     * @param d2
     * @return
     */
    public double divide(double a, double b) {
        BigDecimal bd1 = new BigDecimal(Double.toString(a));
        BigDecimal bd2 = new BigDecimal(Double.toString(b));
        return getnum(bd1.divide(bd2).toString());
    }

}
