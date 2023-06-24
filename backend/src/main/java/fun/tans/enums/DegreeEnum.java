package fun.tans.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum DegreeEnum {
    Doctor(0, "博士"), //博士
    Master(1, "硕士"), //硕士
    UndergraduateNormal(2, "本科"), //本科生
    UndergraduateShortCycle(3, "专科"), //专科生
    SeniorSecondary(4, "高中"), //高中
    JuniorSecondary(5, "初中"); //初中
    @EnumValue
    private final int code;
    private final String descp;

    DegreeEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }
}
