package fun.tans.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DegreeEnum {
    Doctor(0, "博士"), //博士
    Master(1, "硕士"), //硕士
    UndergraduateNormal(2, "本科"), //本科生
    DaZhuan(3, "大专"),
    ZhongZhuan(4, "中专"),//专科生
    SeniorSecondary(5, "高中"), //高中
    JuniorSecondary(6, "初中"), //初中
    Primary(7, "小学"),
    NoEducation(8, "无");

    /**
     * 此注解表示实际存储到数据库中的值
     */
    @JsonValue
    @EnumValue
    private final int code;
    private final String descp;

    DegreeEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }
}
