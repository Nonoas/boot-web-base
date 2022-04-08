package org.nonoas.bootweb.controller.api;

import org.nonoas.bootweb.common.error.BusinessException;
import org.nonoas.bootweb.common.restful.IReturnType;
import org.nonoas.bootweb.filter.aop.LimitRequest;
import org.nonoas.bootweb.pojo.dto.LoginDto;
import org.nonoas.bootweb.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录接口
 *
 * @author Nonoas
 * @datetime 2022/4/1 19:22
 */
@RestController
@Validated
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @PostMapping("login/login")
    @LimitRequest(time = 5000, count = 10)
    public IReturnType login(@RequestBody LoginDto dto) throws BusinessException {
        return loginService.loginService(dto);
    }

    @PostMapping("login/registry")
    @LimitRequest(time = 3000)
    public IReturnType registryService(@RequestBody LoginDto dto) throws BusinessException {
        return loginService.registryService(dto);
    }
}
