package cn.study.firstmicroservice.userserver.controller;

import cn.study.firstmicroservice.userserver.ResultEnum;
import cn.study.firstmicroservice.userserver.constant.CookieConstant;
import cn.study.firstmicroservice.userserver.constant.RedisConstant;
import cn.study.firstmicroservice.userserver.dataObject.UserInfo;
import cn.study.firstmicroservice.userserver.service.UserService;
import cn.study.firstmicroservice.userserver.utils.CookieUtil;
import cn.study.firstmicroservice.userserver.utils.ResultInfoVOUtil;
import cn.study.firstmicroservice.userserver.vo.ResultInfo;
import cn.study.firstmicroservice.userserver.vo.RoleEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 卖家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public ResultInfo seller(@RequestParam("openid")String openid,
                             HttpServletRequest request,
                             HttpServletResponse response){
        //判断是否已登录 cookie是否不为空且cookie中的token值与redis中的token值一致
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null &&
                !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))){
            return ResultInfoVOUtil.success();
        }
        //openid和数据库数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null){
            return ResultInfoVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //判断角色
        if (RoleEnum.SELLER.getCode() != userInfo.getRole()){
            return ResultInfoVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        //Redis设置key=UUID，vaule=xyz
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token),
                openid,
                expire,
                TimeUnit.SECONDS);
        //设置cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);
        return ResultInfoVOUtil.success();
    }

    /**
     * 买家登录
     * @param openid
     * @param response
     * @return
     */
    @GetMapping("/buyer")
    public ResultInfo buyer(@RequestParam("openid")String openid, HttpServletResponse response){
        //openid和数据库数据是否匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null){
            return ResultInfoVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //判断角色
        if (RoleEnum.BUYER.getCode() != userInfo.getRole()){
            return ResultInfoVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        //设置cookie
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);
        return ResultInfoVOUtil.success();
    }
}
