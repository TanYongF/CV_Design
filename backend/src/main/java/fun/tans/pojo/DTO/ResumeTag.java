package fun.tans.pojo.DTO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cv_resume_tag")
public class ResumeTag extends Model<ResumeTag> {
    private String resumeId;
    private String tagId;

}
