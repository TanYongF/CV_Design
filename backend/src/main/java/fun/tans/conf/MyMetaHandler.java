package fun.tans.conf;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author tyf
 * @description 配置Mybatis Plus自动更新
 * @date 15:38 2023/6/12
 **/
@Component
public class MyMetaHandler implements MetaObjectHandler {

    /**
     * 使用mp做添加操作时候，这个方法执行
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //设置属性值
        long current = System.currentTimeMillis();
        this.setFieldValByName("createAt", new Timestamp(current), metaObject);
        this.setFieldValByName("updateAt", new Timestamp(current), metaObject);
        Object avatar = this.getFieldValByName("avatar", metaObject);
        if (avatar == null) {
            this.setFieldValByName("avatar", "https://tdesign.gtimg.com/site/avatar.jpg", metaObject);
        }
    }

    /**
     * 使用mp做修改操作时候，这个方法执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        long current = System.currentTimeMillis();
        this.setFieldValByName("updateAt", new Timestamp(current), metaObject);
    }




}