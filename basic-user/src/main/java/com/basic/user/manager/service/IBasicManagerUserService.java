package com.basic.user.manager.service;


import com.basic.commons.ReturnResult;
import com.basic.mybatis.service.BaseService;
import com.basic.user.manager.model.BasicManagerUser;

/**
 * <p>
 * 基础管理用户 服务类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-05
 */
public interface IBasicManagerUserService extends BaseService<BasicManagerUser,Long> {

    /**
     * 根据帐号查询用户信息
     * @param username
     * @return
     */
    ReturnResult findByManagerUserName(String username);

    /**
     * 根据帐号查询除密码外所有数据
     * @param userName
     * @return
     */
    ReturnResult getInfo(String userName);
}
