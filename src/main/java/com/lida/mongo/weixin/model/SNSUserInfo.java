package com.lida.mongo.weixin.model;

import java.util.List;

/**
 * ͨ����ҳ��Ȩ��ȡ���û���Ϣ
 */
public class SNSUserInfo {
    // �û���ʶ
    private String openId;
    // �û��ǳ�
    private String nickname;
    // �Ա�1�����ԣ�2��Ů�ԣ�0��δ֪��
    private int sex;
    // ����
    private String country;
    // ʡ��
    private String province;
    // ����
    private String city;
    // �û�ͷ������
    private String headImgUrl;
    // �û���Ȩ��Ϣ
    private List<String> privilegeList;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public List<String> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<String> privilegeList) {
        this.privilegeList = privilegeList;
    }
}
