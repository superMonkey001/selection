package cn.hncj.selection.controller;

import cn.hncj.selection.entites.CommonResult;
import cn.hncj.selection.service.CheckCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CheckCodeController {
    @Autowired
    CheckCodeService checkCodeService;
    @GetMapping("/sendCode")
    @ResponseBody
    public CommonResult sendCode(@RequestParam("phoneNum")String phoneNum){
            checkCodeService.codeLogic(phoneNum);
        String redisCode = checkCodeService.getRedisCode(phoneNum);
        if(redisCode==null||checkCodeService.getCount(phoneNum)>3)
        {
            return new CommonResult("请稍后重试在重试吧",400);
        }
        return new CommonResult("你的验证码为"+redisCode+",有效时间为一分钟",200);
    }
}
