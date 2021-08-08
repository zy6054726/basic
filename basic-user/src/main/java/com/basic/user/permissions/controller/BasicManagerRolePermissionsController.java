package com.basic.user.permissions.controller;


import com.basic.commons.ReturnResult;
import com.basic.user.permissions.model.BasicManagerRolePermissions;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.basic.mybatis.controller.BaseController;

/**
 * <p>
 * 权限-角色表 前端控制器
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@RestController
@RequestMapping("/rolePermissions")
public class BasicManagerRolePermissionsController extends BaseController<BasicManagerRolePermissions,Long> {
    @Override
    public ReturnResult save(BasicManagerRolePermissions basicManagerRolePermissions) {
        return null;
    }

    @Override
    public ReturnResult delete(Long t) {
        return null;
    }

    @Override
    public ReturnResult update(BasicManagerRolePermissions basicManagerRolePermissions) {
        return null;
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }
}
