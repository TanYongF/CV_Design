package fun.tans.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Resume {
    @TableId
    public Long id;


    public String url;
    public String name;
    public Short age;
    public String school;
    public String intention;
    public Boolean gender;
    public String avatar;


    @TableField(fill = FieldFill.INSERT)
    private Timestamp createAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateAt;
}
