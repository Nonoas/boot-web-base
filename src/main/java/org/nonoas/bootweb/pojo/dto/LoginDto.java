package org.nonoas.bootweb.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Nonoas
 * @datetime 2022/4/1 21:04
 */
@Data
public class LoginDto {

    /** 用户唯一标识 */
    String openId;

    /** 用户昵称 */
    String userName;

    /** 微信平台返回的code，用于获取 用户唯一标识：openId  */
    String jsCode;

    /** 用户类型 */
    String userType;

    /** 头像 url */
    String avatar;

    /** 用户昵称 */
    String nickname;
}
