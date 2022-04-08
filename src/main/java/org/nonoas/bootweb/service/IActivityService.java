package org.nonoas.bootweb.service;

import org.nonoas.bootweb.common.error.BusinessException;
import org.nonoas.bootweb.common.restful.IReturnType;
import org.nonoas.bootweb.pojo.dto.ActSignUpDto;

/**
 * @author Nonoas
 * @datetime 2022/4/4 1:07
 */
public interface IActivityService {
    /**
     * 活动报名
     *
     * @param dto 前台报文
     * @return 响应数据
     */
    IReturnType signUpService(ActSignUpDto dto) throws BusinessException;

    /**
     * 报名信息
     *
     * @param dto 前台报文：<br/>
     *            openId，actId
     * @return 报名详情
     */
    IReturnType signDtlService(ActSignUpDto dto);
}
