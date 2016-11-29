package com.lida.mongo.weixin.message.resp;


/**
 * ��Ϣ���ࣨ�����ʺ� -> ��ͨ�û���
 */
public class BaseMessage {
    // ���շ��ʺţ��յ���OpenID��
    private String ToUserName;
    // ������΢�ź�  
    private String FromUserName;
    // ��Ϣ����ʱ�� �����ͣ�  
    private long CreateTime;
    // ��Ϣ���ͣ�text/music/news��  
    private String MsgType;
    // λ0x0001����־ʱ���Ǳ���յ�����Ϣ  
    private int FuncFlag;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public int getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(int funcFlag) {
        FuncFlag = funcFlag;
    }
}
