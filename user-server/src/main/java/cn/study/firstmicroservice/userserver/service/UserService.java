package cn.study.firstmicroservice.userserver.service;

import cn.study.firstmicroservice.userserver.dataObject.UserInfo;

import java.util.List;

public interface UserService {

    /**
     * 通过openid来查询用户
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);

    List<UserInfo> getAllUserList();
}
