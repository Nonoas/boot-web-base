package org.nonoas.bootweb.dao;

import org.nonoas.bootweb.pojo.po.ActSignUpPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Nonoas
 * @datetime 2022/4/4 1:14
 */
@Repository
public interface ActivityMapper {
    @Select("select * from activity_user where act_id=#{actId} and open_id=#{openId}")
    ActSignUpPo selectById(@Param("actId") String actId, @Param("openId") String openId);

    @Insert("insert into activity_user(act_id, open_id) values(#{actId}, #{openId})")
    void insert(ActSignUpPo po);
}
