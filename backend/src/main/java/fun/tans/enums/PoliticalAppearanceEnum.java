package fun.tans.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PoliticalAppearanceEnum {
    COMMUNIST_PARTY_MEMBER(0, "中共党员"),
    PROBATIONARY_COMMUNIST_PARTY_MEMBER(1, "中共预备党员"),
    YOUTH_LEAGUE_MEMBER(2, "共青团员"),
    DEMOCRATIC_PARTY_MEMBER(3, "民主党派"),
    MASS(4, "群众");

    /**
     * 此注解表示实际存储到数据库中的值
     */
    @JsonValue
    @EnumValue
    private final int code;
    private final String descp;

    PoliticalAppearanceEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

}
