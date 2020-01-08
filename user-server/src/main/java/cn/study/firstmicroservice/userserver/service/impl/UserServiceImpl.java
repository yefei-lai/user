package cn.study.firstmicroservice.userserver.service.impl;

import cn.study.firstmicroservice.userserver.dataObject.UserInfo;
import cn.study.firstmicroservice.userserver.repository.UserInfoRepository;
import cn.study.firstmicroservice.userserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoRepository.findByOpenid(openid);
    }

    @Override
    public List<UserInfo> getAllUserList() {
        return userInfoRepository.queryAllBy();
    }
}
