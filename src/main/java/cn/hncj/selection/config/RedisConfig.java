package cn.hncj.selection.config;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author Xkuna
 * @date 2020/11/19 14:05.
 */
//配置类专属注解 并且完成自动注入
@Configuration
public class RedisConfig {
        /**
         * 设置 redisTemplate 的序列化设置
         * @param redisConnectionFactory
         * @return
         */
        @Bean
        public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
            // 1.创建 redisTemplate 模版
            RedisTemplate<Object, Object> template = new RedisTemplate<>();
            // 2.关联 redisConnectionFactory
            template.setConnectionFactory(redisConnectionFactory);
            // 3.创建 序列化类
            GenericToStringSerializer genericToStringSerializer = new GenericToStringSerializer(Object.class);
            // 6.序列化类，对象映射设置
            // 7.设置 value 的转化格式和 key 的转化格式
            template.setValueSerializer(genericToStringSerializer);
            template.setKeySerializer(new StringRedisSerializer());
            template.afterPropertiesSet();
            return template;
        }
    }

