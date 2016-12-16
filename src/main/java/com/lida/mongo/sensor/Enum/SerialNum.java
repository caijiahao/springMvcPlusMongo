package com.lida.mongo.sensor.Enum;

import java.math.BigInteger;

/**
 * Created by stevenfen on 2016/12/17.
 */
public enum SerialNum {
    stationNine("stationNine",BigInteger.valueOf(318861587)),
    stationSeven("stationSeven",BigInteger.valueOf(318861616)),
    stationThree("stationThree",BigInteger.valueOf(318861652)),
    stationFive("stationFive",BigInteger.valueOf(318861653))
    ;
    private String key;
    private BigInteger value;
    //自定义的构造函数，参数数量，名字随便自己取
    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
    private SerialNum(String key, BigInteger value)
    {
        this.key = key;
        this.value = value;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public BigInteger getValue()
    {
        return value;
    }

    public void setValue(BigInteger value)
    {
        this.value = value;
    }
    //重新toString方法，默认的toString方法返回的就是枚举变量的名字，和name()方法返回值一样
    @Override
    public String toString()
    {
        return this.key+":"+this.value;

    }
}
