package fun.tans.annotation;

import java.lang.annotation.*;

/**
 * @Describe: 注解用标记需要进行管理认证的接口方法
 * @Author: tyf
 * @CreateTime: 2022/4/21
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedAuth {

    String[] value() default {};

}
