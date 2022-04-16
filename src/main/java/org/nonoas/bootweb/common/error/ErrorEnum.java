package org.nonoas.bootweb.common.error;

/**
 * @author : Nonoas
 * @time : 2021-04-20 9:59
 */
public enum ErrorEnum implements IErrorReturnType {

    SUCCESS("00000", "请求成功"),

    /**
     * 未知错误，系统中未指定类型的错误
     */
    UNKNOWN_ERROR("00001", "未知错误"),

    /**
     * A0001：数据不存在
     */
    DATA_NOT_FOUND("A0001", "数据不存在"),

    /**
     * A0111：用户已存在
     */
    USER_EXISTS("A0111", "用户已存在"),

    /**
     * A0210：账号或密码错误
     */
    LOGIN_FAIL("A0210", "用户名或密码错误"),

    /**
     * A0230：用户未登录或登录已过期
     */
    NO_LOGIN("A0230", "用户未登录"),

    /**
     * A0300：访问权限异常
     */
    AUTH_ERROR("A0300", "访问权限异常"),


    /**
     * A0400: 请求参数宏观错误
     */
    BAD_PARAM("A0400", "用户请求参数错误"),

    /**
     * A0402：请求参数值超出允许的范围
     */
    PARAM_OUT_OF_RANGE("A0402", "请求参数值超出允许的范围"),

    /**
     * A0421：参数格式不匹配
     */
    PARAM_FORMAT_MISMATCH("A0421", "参数格式不匹配"),

    /**
     * A0501：访问频率过高
     */
    FREQUENT_REQUEST("A0501", "访问频率过高"),

    /**
     * A0506：用户重复请求
     */
    REPEAT_REQUEST("A0506", "用户重复请求"),

    /**
     * A0700: 用户上传文件异常
     */
    UPLOAD_ERROR("A0700", "用户上传文件异常");


    private final String errorCode;
    private final String errorMsg;

    ErrorEnum(String code, String msg) {
        this.errorCode = code;
        this.errorMsg = msg;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
