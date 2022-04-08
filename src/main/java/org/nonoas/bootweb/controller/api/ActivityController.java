package org.nonoas.bootweb.controller.api;

import org.nonoas.bootweb.common.error.BusinessException;
import org.nonoas.bootweb.common.restful.IReturnType;
import org.nonoas.bootweb.pojo.dto.ActSignUpDto;
import org.nonoas.bootweb.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 活动请求入口
 *
 * @author Nonoas
 * @datetime 2022/4/4 0:58
 */
@RestController
public class ActivityController {

    @Autowired
    private IActivityService actService;

    @PostMapping("activity/signUp")
    public IReturnType signUp(@RequestBody ActSignUpDto dto) throws BusinessException {
        return actService.signUpService(dto);
    }

    @PostMapping("activity/signDtl")
    public IReturnType signDtl(@RequestBody ActSignUpDto dto) {
        return actService.signDtlService(dto);
    }
}
