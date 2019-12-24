package cn.study.firstmicroservice.userserver.service;

import cn.study.firstmicroservice.userserver.dataObject.UserInfo;

public interface UserService {

    /**
     * 通过openid来查询用户
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);
}
