package com.lida.mongo.weixin.message.resp;

/**
 * ������Ϣ
 */
public class MusicMessage extends BaseMessage {
    // ����
    private com.lida.mongo.weixin.message.resp.Music Music;

    public com.lida.mongo.weixin.message.resp.Music getMusic() {
        return Music;
    }

    public void setMusic(com.lida.mongo.weixin.message.resp.Music music) {
        Music = music;
    }
}
