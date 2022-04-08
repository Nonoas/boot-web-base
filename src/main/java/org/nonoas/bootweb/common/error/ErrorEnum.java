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
     * A0111：用户已存在
     */
    USER_EXISTS("A0111", "用户已存在"),

    /**
     * A0402：请求参数值超出允许的范围
     */
    BAD_PARAM_VALUE("A0402", "请求参数值超出允许的范围"),

    /**
     * A0501：访问频率过高
     */
    FREQUENT_REQUEST("A0501", "访问频率过高"),

    /**
     * A0506：用户重复请求
     */
    REPEAT_REQUEST("A0506", "用户重复请求");


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
