package cn.study.firstmicroservice.userserver.utils;

import cn.study.firstmicroservice.userserver.ResultEnum;
import cn.study.firstmicroservice.userserver.vo.ResultInfo;

public class ResultInfoVOUtil {

    public static ResultInfo success(){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode("0");
        resultInfo.setMsg("成功");
        return resultInfo;
    }

    public static ResultInfo success(Object o){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode("0");
        resultInfo.setMsg("成功");
        resultInfo.setData(o);
        return resultInfo;
    }

    public static ResultInfo error(ResultEnum e){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(e.getCode().toString());
        resultInfo.setMsg(e.getMessage());
        return resultInfo;
    }
}
