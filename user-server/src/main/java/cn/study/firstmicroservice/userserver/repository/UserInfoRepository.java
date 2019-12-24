package cn.study.firstmicroservice.userserver.repository;

import cn.study.firstmicroservice.userserver.dataObject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openid);
}
