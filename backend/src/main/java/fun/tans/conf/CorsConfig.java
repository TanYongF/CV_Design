package fun.tans.conf;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {
    static final String ORIGINS[] = new String[]{"GET", "POST", "PUT", "DELETE", "OPTIONS"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
                allowedOrigins("*")
                .allowedOriginPatterns("*")//所有地址都可以访问，也可以配置具体地址
                .allowedMethods(ORIGINS).maxAge(3600);
    }
}
