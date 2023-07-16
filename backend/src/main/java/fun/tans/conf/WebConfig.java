package fun.tans.conf;

import fun.tans.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Describe: web全局配置类
 * @Author: tyf
 * @CreateTime: 2022/4/17
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private UserArgumentResolvers userArgumentResolvers;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册相应的鉴权策略拦截器
        registry.addInterceptor(authInterceptor).addPathPatterns("/user/**").excludePathPatterns("/user/avatar");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolvers);
    }
}