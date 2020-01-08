package cn.study.firstmicroservice.userserver.controller;

import cn.study.firstmicroservice.userserver.dataObject.UserInfo;
import cn.study.firstmicroservice.userserver.service.UserService;
import cn.study.firstmicroservice.userserver.utils.ResultInfoVOUtil;
import cn.study.firstmicroservice.userserver.vo.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAllUsers")
    public ResultInfo<List<UserInfo>> getAllUserList(){
        List<UserInfo> userList = null;
        try {
            Thread.sleep(2000);
            userList = userService.getAllUserList();
            log.info("接收到的用户信息为：{}"+ userList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResultInfoVOUtil.success(userList);
    }
}
