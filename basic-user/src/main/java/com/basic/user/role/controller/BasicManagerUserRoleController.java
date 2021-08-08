package com.basic.user.role.controller;


import com.basic.commons.ReturnResult;
import com.basic.user.role.model.BasicManagerUserRole;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.basic.mybatis.controller.BaseController;

/**
 * <p>
 * 用户角色表 前端控制器
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@RestController
@RequestMapping("/userRole")
public class BasicManagerUserRoleController extends BaseController<BasicManagerUserRole,Long> {
    @Override
    public ReturnResult save(BasicManagerUserRole basicManagerUserRole) {
        return null;
    }

    @Override
    public ReturnResult delete(Long t) {
        return null;
    }

    @Override
    public ReturnResult update(BasicManagerUserRole basicManagerUserRole) {
        return null;
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }
}
