package fun.tans.pojo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cv_user")
public class User {
    @TableId
    private String username;
    private String phone;
    private String salt;
    private String password;

    private String nickname;
    /**
     * value 0 : female
     * value 1 : male
     */
    private Boolean gender;
    /**
     * value 0 : simple user
     * value 1 : admin user
     */
    private Boolean role;

    @TableField(fill = FieldFill.INSERT)
    private String avatar;

    @TableField(fill = FieldFill.INSERT)
    private Timestamp createAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateAt;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
