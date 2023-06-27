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
     * 候选人姓名
     */
    private String name;
    /**
     * 最高学历
     */
    private DegreeEnum highestDegree;
    /**
     * 手机电话
     */
    private String tel;
    /**
     * 微信号
     */
    private String wechat;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 年龄
     */
    private Short age;
    /**
     * 毕业院校
     */
    private String school;
    /**
     * 求职意向
     */
    private String intention;
    /**
     * 性别
     */
    private Boolean gender;
    /**
     * 简历照片地址
     */
    private String avatarUrl;
    /**
     * 工作年限
     */
    private Short workingYears;

    /**
     * 上传用户名称
     */
    private String uploadUser;


    @TableField(fill = FieldFill.INSERT)
    private Timestamp createAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateAt;
}
