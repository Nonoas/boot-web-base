package org.nonoas.bootweb.filter;

import org.nonoas.bootweb.common.error.BusinessException;
import org.nonoas.bootweb.common.error.ErrorEnum;
import org.nonoas.bootweb.common.restful.BaseReturnType;
import org.nonoas.bootweb.common.restful.IReturnType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常捕获类
 *
 * @author : Nonoas
 * @time : 2021-03-22 15:59
 */
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public IReturnType doError(BusinessException e) {
        logger.error(e.getMessage(), e);
        BaseReturnType baseReturnType = BaseReturnType.getNewInstance();
        baseReturnType.setErrorCode(e.getErrorCode());
        baseReturnType.setErrorMsg(e.getErrorMsg());
        return baseReturnType;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public IReturnType doError(NoHandlerFoundException e) {
        logger.error(e.getMessage(), e);
        BaseReturnType baseReturnType = BaseReturnType.getNewInstance();
        baseReturnType.setErrorCode(ErrorEnum.UNKNOWN_ERROR.getErrorCode());
        baseReturnType.setErrorMsg("没有找到访问路径");
        return baseReturnType;
    }

    /**
     * 为设置全局捕获的异常，都视为 “未知错误”
     */
    @ExceptionHandler(Exception.class)
    public IReturnType doError(Exception e) {
        logger.error(e.getMessage(), e);
        return ErrorEnum.UNKNOWN_ERROR;
    }

}
