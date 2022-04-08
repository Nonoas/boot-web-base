package org.nonoas.bootweb.common.restful;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 顶级返回响应接口
 *
 * @author : Nonoas
 * @time : 2021-04-20 10:08
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface IReturnType {

    String getErrorCode();

    String getErrorMsg();

}
