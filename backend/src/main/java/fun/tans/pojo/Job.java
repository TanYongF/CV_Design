package fun.tans.pojo;


import com.baomidou.mybatisplus.annotation.*;
import fun.tans.enums.DegreeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("cv_job")
@AllArgsConstructor
public class Job {

    /**
     * 其他信息
     */
    public String remark;
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 最低薪酬
     */
    private Integer minSalary;
    /**
     * 上传企业
     */
    private String userName;
    /**
     * 最低学历
     */
    private DegreeEnum minDegree;
    /**
     * 最低工作年限
     */
    private Integer minWorkYears;


    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 招聘人数
     */
    private Integer headCount;
    /**
     * 岗位名称
     */
    private String positionName;
    /**
     * 岗位要求以及简要介绍
     */
    private String info;
    /**
     * 最高薪酬
     */
    private Integer maxSalary;
    @TableField(fill = FieldFill.INSERT)
    private Timestamp createAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateAt;

}
