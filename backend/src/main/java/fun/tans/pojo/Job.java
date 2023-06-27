package fun.tans.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cv_job")
public class Job {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 公司名称
     */
    private String company;

    /**
     * 招聘人数
     */
    private Integer recruitNumber;

    /**
     * 岗位名称
     */
    private String title;

    /**
     * 岗位要求以及简要介绍
     */
    private String info;

    /**
     * 最高薪酬
     */
    private Integer maxSalary;

    /**
     * 最低薪酬
     */
    public Integer minSalary;

    /**
     * 其他信息
     */
    public String remark;

}
