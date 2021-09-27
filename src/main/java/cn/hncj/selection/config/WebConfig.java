package cn.hncj.selection.config;


import cn.hncj.selection.intercept.LoginIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class WebConfig {
    @Autowired
    LoginIntercept loginIntercept;
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(loginIntercept).excludePathPatterns("/css/**","/js/**",
                        "/fonts/**","/images/**","/","/login","/sendCode");
            }
        };
    }
}
