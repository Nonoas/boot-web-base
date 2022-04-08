package org.nonoas.bootweb.service;

import org.nonoas.bootweb.common.error.BusinessException;
import org.nonoas.bootweb.common.restful.IReturnType;
import org.nonoas.bootweb.pojo.dto.LoginDto;

/**
 * @author Nonoas
 * @datetime 2022/4/1 20:59
 */
public interface ILoginService {

    /**
     * 登录
     *
     * @param dto 参数：jsCode
     * @return 用户在本平台的所有信息
     */
    IReturnType loginService(LoginDto dto) throws BusinessException;

    /**
     * 注册
     *
     * @param dto 参数：头像、昵称、openid等
     * @return 用户在本平台的所有信息
     */
    public IReturnType registryService(LoginDto dto) throws BusinessException;

}
