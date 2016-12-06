package com.lida.mongo.sensor.Enum;

/**
 * Created by stevenfen on 2016/12/6.
 */
public enum StationType {
    //不需要从云端拉数据的操作
    NoNeedUpdate("不需要更新",61L);
    private String key;
    private Long value;
    //自定义的构造函数，参数数量，名字随便自己取
    //构造器默认也只能是private, 从而保证构造函数只能在内部使用
    private StationType(String key, Long value)
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

    public Long getValue()
    {
        return value;
    }

    public void setValue(Long value)
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
