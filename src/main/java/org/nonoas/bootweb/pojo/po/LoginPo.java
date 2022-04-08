package org.nonoas.bootweb.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Nonoas
 * @datetime 2022/4/3 13:10
 */
@Data
@TableName("login_info")
public class LoginPo {
    @TableId("open_id")
    String openId;
    /** 头像 url */
    String avatar;
    /** 用户昵称 */
    String nickname;

    String secret;

    String userType;
}
