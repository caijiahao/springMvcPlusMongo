package light.mvc.framework.constant;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


public class GlobalConstant {

    public static final String ACCESS_TOKEN_TEST = "ACCESS_TOKEN";//专家

    public static final String USERCODE = "wsnwsn640";
    public static final String USERSECRET = "wsnwsn640";
    public static final String SECRET = "wsnwsn640";

    //	专家和普通用户类型字符串
    public static final String EXPERT = "expert";//专家
    public static final String MEMBER = "member";//普通用户

    //	系统各种类型的用户session字段设置
    public static final String SESSION_INFO_ADMIN = "sessionInfo";//管理员session常量
    public static final String SESSION_INFO_EXPERT = "sessionInfo";//专家用户session常量
    public static final String SESSION_INFO_MEMBER = "sessionInfo";//专家用户session常量
    public static final String SESSION_INFO_USER = "sessionInfo";//专家用户session常量

    public static final Integer ENABLE = 0; // 启用
    public static final Integer DISABLE = 1; // 禁用


    public static final Integer DEFAULT = 0; // 默认
    public static final Integer NOT_DEFAULT = 1; // 非默认

    public static final Map sexlist = new HashMap() {
        {
            put("0", "男");
            put("1", "女");
        } };
    public static final Map needPublishList = new HashMap() {
        {
            put("0", "不发布");
            put("1", "发布");
        } };
    public static final Map statelist = new HashMap() {
        {
            put("1", "启用");
            put("0", "停用");
        } };
    public static final Map statuslist = new HashMap() {
        {
            put("0", "待审核");
            put("1", "通过");
            put("-1", "不通过");
        } };

    public static final String uploadFolder = "/uploadfile";
    public static final String uploadThumbFolder = "/uploadfile/thumb";

    //	xx_resource表中资源type的类别定义
    public static final String PICTURETYPE = "picture";
    public static final String VIDEOTYPE = "video";


    public static final SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd");
}
