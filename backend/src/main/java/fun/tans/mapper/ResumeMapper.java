package fun.tans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.tans.pojo.Resume;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ResumeMapper extends BaseMapper<Resume> {
    //TODO
    @Select("SELECT ${key}, COUNT(*) AS count FROM cv_resume GROUP BY ${key}")
    List<Map<String, Long>> countByColumnKey(@Param("key") String key);



}
