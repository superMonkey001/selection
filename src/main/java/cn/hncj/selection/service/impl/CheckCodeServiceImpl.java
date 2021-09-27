package cn.hncj.selection.service.impl;

import cn.hncj.selection.service.CheckCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class CheckCodeServiceImpl implements CheckCodeService {
    @Autowired
    private  RedisTemplate redisTemplate;

    public String getRedisCode(String phone){
        String codeKey = phone +":code";
        String redisCode = (String) redisTemplate.opsForValue().get(codeKey);
        return redisCode;
    }

    public int getCount(String phone){
        String countKey = phone + ":code:count";
        String count = (String) redisTemplate.opsForValue().get(countKey);
        return Integer.parseInt(count);
    }

    public  void codeLogic(String phone){
        String countKey = phone + ":code:count";
        String codeKey = phone + ":code";
        String redisCount = (String) redisTemplate.opsForValue().get(countKey);

        if(redisCount==null)
        {
            redisTemplate.opsForValue().set(countKey,1,2*60,TimeUnit.SECONDS);
        }
        else if(Integer.parseInt(redisCount)<=2)
        {
            redisTemplate.opsForValue().increment(countKey);
        }
        else{
            redisTemplate.opsForValue().increment(countKey);
            return;
        }
        redisTemplate.opsForValue().set(codeKey,getCode(),60,TimeUnit.SECONDS);
    }

    public  String getCode(){
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int code = random.nextInt(10);
            buffer.append(code);
        }
        return buffer.toString();
    }
}
