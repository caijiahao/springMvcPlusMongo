package com.lida.mongo.model;

/**
 * �û�����λ��model
 */
public class UserLocation {
    private String openId;
    private String lng;
    private String lat;
    private String bd09Lng;
    private String bd09Lat;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getBd09Lng() {
        return bd09Lng;
    }

    public void setBd09Lng(String bd09Lng) {
        this.bd09Lng = bd09Lng;
    }

    public String getBd09Lat() {
        return bd09Lat;
    }

    public void setBd09Lat(String bd09Lat) {
        this.bd09Lat = bd09Lat;
    }
}
