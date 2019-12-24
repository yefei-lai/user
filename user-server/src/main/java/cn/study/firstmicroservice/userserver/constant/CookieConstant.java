package cn.study.firstmicroservice.userserver.constant;

public interface CookieConstant {
    String TOKEN = "token";

    /**
     * 过期时间（秒）
     */
    Integer expire = 7200;

    String OPENID = "openid";
}
