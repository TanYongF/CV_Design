package fun.tans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.tans.pojo.Job;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobMapper extends BaseMapper<Job> {
}
