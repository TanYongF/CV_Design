package fun.tans.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("cv_tag")
@AllArgsConstructor
public class Tag {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    private String info;

}
