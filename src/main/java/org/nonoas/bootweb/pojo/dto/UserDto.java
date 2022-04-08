package org.nonoas.bootweb.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Nonoas
 * @datetime 2022/4/1 19:31
 */
@Data
@TableName("user")
public class UserDto {
    @TableId("user_name")
    String userName;
    String secret;
    String type;
}
