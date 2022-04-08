package org.nonoas.bootweb.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 活动报名数据
 *
 * @author Nonoas
 * @datetime 2022/4/4 1:03
 */
@Data
public class ActSignUpPo {
    /** 活动id */
    private String actId;
    /** 用户 openid */
    private String openId;
}
