package com.basic.user.manager.mapper;


import com.basic.mybatis.mapper.BaseMappers;
import com.basic.user.manager.model.BasicManagerUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 基础管理用户 Mapper 接口
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-05
 */
public interface BasicManagerUserMapper extends BaseMappers<BasicManagerUser> {

    BasicManagerUser findByManagerUserName(@Param("username") String username, @Param("isDel") Boolean isDel, @Param("islock") Boolean islock);

}
