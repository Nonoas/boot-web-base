package org.nonoas.bootweb.common.restful;

/**
 * 返回类型基类
 *
 * @author : Nonoas
 * @time : 2021-04-20 9:42
 */
public class BaseReturnType implements IReturnType {
    /**
     * 错误代码，默认为成功 “00000”
     */
    private String errorCode = "00000";
    /**
     * 错误信息
     */
    private String errorMsg = "请求成功";

    protected BaseReturnType() {
    }

    /**
     * 获取
     *
     * @return 获取通用返回类型
     */
    public static BaseReturnType getNewInstance() {
        return new BaseReturnType();
    }

    /**
     * 获取从 IReturnType 对应的实例
     *
     * @return 获取通用返回类型
     */
    public static BaseReturnType create(IReturnType returnType) {
        BaseReturnType type = new BaseReturnType();
        type.setErrorMsg(returnType.getErrorMsg());
        type.setErrorCode(returnType.getErrorCode());
        return type;
    }

    /**
     * 获取成功返回结果
     *
     * @param errorMsg 成功提示信息
     * @return 成功返回结果
     */
    public static BaseReturnType create(String errorMsg) {
        BaseReturnType baseReturnType = new BaseReturnType();
        baseReturnType.setErrorMsg(errorMsg);
        return baseReturnType;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
