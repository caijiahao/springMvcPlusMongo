package com.lida.mongo.util;

import com.lida.mongo.qq.model.QQUserInfo;
import jdk.nashorn.internal.runtime.GlobalConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by stevenfen on 2016/12/22.
 */
public class QQUtils {
    public static String appId = "101375254";
    public static String appSecret = "xxx";

    public static String baseUrl = "https://graph.qq.com";

    protected static final String URL_GET_USERINFO = baseUrl
            + "/user/get_user_info?access_token=%s&oauth_consumer_key=%s&openid=%s";

    protected static final long ACCESS_TIMEOUT = 15;

    protected static final String DEF_APP_TOKEN_EXPIRE = "3h";

    private static Logger log = LoggerFactory.getLogger(QQUtils.class);
    /**
     * 获取用户信息
     *
     * <pre>
     * http://wiki.connect.qq.com/get_user_info
     *
     *
     * 调用地址：
     * https://graph.qq.com/user/get_user_info
     * 参数
     *   access_token=*************&
     *   oauth_consumer_key=12345&
     *   openid
     *
     * 返回结果如下：
     * {
     *     "ret": 0,
     *     "msg": "",
     *     "is_lost": 0,
     *     "nickname": "小吞",
     *     "gender": "女",
     *     "province": "广东",
     *     "city": "广州",
     *     "year": "1993",
     *     "figureurl": "http://qzapp.qlogo.cn/qzapp/101207268/982C9FEADAF7B242C5069B8F390784BF/30",
     *     "figureurl_1": "http://qzapp.qlogo.cn/qzapp/101207268/982C9FEADAF7B242C5069B8F390784BF/50",
     *     "figureurl_2": "http://qzapp.qlogo.cn/qzapp/101207268/982C9FEADAF7B242C5069B8F390784BF/100",
     *     "figureurl_qq_1": "http://q.qlogo.cn/qqapp/101207268/982C9FEADAF7B242C5069B8F390784BF/40",
     *     "figureurl_qq_2": "http://q.qlogo.cn/qqapp/101207268/982C9FEADAF7B242C5069B8F390784BF/100",
     *     "is_yellow_vip": "0",
     *     "vip": "0",
     *     "yellow_vip_level": "0",
     *     "level": "0",
     *     "is_yellow_year_vip": "0"
     * }
     * </pre>
     *
     * @param accessToken
     * @return
     */
    public static QQUserInfo getUserInfo(String accessToken, String openid) {
        if (StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(openid)) {
            return null;
        }

        String url = String.format(URL_GET_USERINFO, accessToken, appId, openid);

        String resultString = HttpClientUtils.httpGet(url);

        log.debug("[sso-qq]get　userinfo. use url '%s'", url);

        QQUserInfo userinfo = JsonUtil.fromJson(resultString, QQUserInfo.class);
        if (userinfo == null ) {
            log.debug("[sso-qq]get userinfo failed, with result of '%s'", resultString);
            return null;
        }

        log.debug("[sso-qq]get userinfo success, with result of '%s'", resultString);
        return userinfo;
    }
}
