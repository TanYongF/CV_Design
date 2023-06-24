package fun.tans.pojo;

import com.baomidou.mybatisplus.annotation.*;
import fun.tans.enums.DegreeEnum;
import lombok.Data;

import java.sql.Timestamp;


@Data
@TableName("cv_resume")
public class Resume {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;


    /**
     * 简历地址，存储放在OSS中的地址
     */
    private String fileUrl;
    /**
     * 简历名称，存储放在OSS中的简历文件名
     */
    private String fileName;
    /**
     * 简历姓名
     */
    private String name;
    private DegreeEnum highestDegree;
    private String tel;
    private String wechat;
    private String email;
    private Short age;
    private String school;
    private String intention;
    private Boolean gender;
    /**
     * 简历照片地址
     */
    private String avatarUrl;
    private Short workingYears;


    @TableField(fill = FieldFill.INSERT)
    private Timestamp createAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateAt;
}
