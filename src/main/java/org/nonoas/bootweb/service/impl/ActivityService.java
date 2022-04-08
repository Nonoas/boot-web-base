package org.nonoas.bootweb.service.impl;

import org.nonoas.bootweb.common.error.BusinessException;
import org.nonoas.bootweb.common.error.ErrorEnum;
import org.nonoas.bootweb.common.restful.BaseReturnType;
import org.nonoas.bootweb.common.restful.DataReturnType;
import org.nonoas.bootweb.common.restful.IReturnType;
import org.nonoas.bootweb.dao.ActivityMapper;
import org.nonoas.bootweb.pojo.dto.ActSignUpDto;
import org.nonoas.bootweb.pojo.po.ActSignUpPo;
import org.nonoas.bootweb.service.IActivityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 活动
 *
 * @author Nonoas
 * @datetime 2022/4/4 1:08
 */
@Service
public class ActivityService implements IActivityService {

    private final ActivityMapper actMapper;

    @Autowired
    public ActivityService(ActivityMapper actMapper) {
        this.actMapper = actMapper;
    }

    @Override
    public IReturnType signUpService(ActSignUpDto dto) throws BusinessException {
        ActSignUpPo actSignUpPo = actMapper.selectById(dto.getActId(), dto.getOpenId());
        if (null != actSignUpPo) {
            throw new BusinessException(ErrorEnum.REPEAT_REQUEST, "您已报名，请勿重复参加");
        }
        ActSignUpPo po = new ActSignUpPo();
        BeanUtils.copyProperties(dto, po);
        actMapper.insert(po);
        return BaseReturnType.create("报名成功");
    }

    @Override
    public IReturnType signDtlService(ActSignUpDto dto) {
        ActSignUpPo actSignUpPo = actMapper.selectById(dto.getActId(), dto.getOpenId());
        return DataReturnType.create(actSignUpPo);
    }
}
