package org.nonoas.bootweb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.nonoas.bootweb.pojo.po.LoginPo;
import org.springframework.stereotype.Repository;

/**
 * @author Nonoas
 * @datetime 2022/4/1 21:12
 */
@Repository
public interface LoginMapper extends BaseMapper<LoginPo> {
}
