package org.nonoas.bootweb.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.nonoas.bootweb.common.error.BusinessException;
import org.nonoas.bootweb.common.error.ErrorEnum;
import org.nonoas.bootweb.common.restful.DataReturnType;
import org.nonoas.bootweb.common.restful.IReturnType;
import org.nonoas.bootweb.dao.LoginMapper;
import org.nonoas.bootweb.pojo.dto.LoginDto;
import org.nonoas.bootweb.pojo.po.LoginPo;
import org.nonoas.bootweb.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Nonoas
 * @datetime 2022/4/1 21:10
 */
@Slf4j
@Service
public class LoginService implements ILoginService {

    private final LoginMapper loginMapper;


    @Autowired
    public LoginService(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    @Override
    public IReturnType loginService(LoginDto dto) throws BusinessException {
        Assert.notNull(dto.getJsCode(), "code can not be null");

        return ErrorEnum.SUCCESS;
    }


    @Override
    public IReturnType registryService(LoginDto dto) throws BusinessException {
        Assert.notNull(dto.getJsCode(), "code can not be null");

        String openId = dto.getOpenId();
        // 登录并查询出用户所有信息
        LoginPo loginPo = loginMapper.selectById(openId);
        if (null != loginPo) {
            return ErrorEnum.USER_EXISTS;
        }

        // 如果用户不存在则新建用户
        loginPo = new LoginPo();
        BeanUtils.copyProperties(dto, loginPo);
        loginMapper.insert(loginPo);

        // 将新建的用户返回到前端
        loginPo = loginMapper.selectById(openId);
        return DataReturnType.create(loginPo);
    }
}
