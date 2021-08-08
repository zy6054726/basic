package com.basic.user.permissions.controller;


import com.basic.commons.ReturnResult;
import com.basic.user.permissions.model.BasicManagerPermissions;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.basic.mybatis.controller.BaseController;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@RestController
@RequestMapping("/permissions")
public class BasicManagerPermissionsController extends BaseController<BasicManagerPermissions,Long> {
    @Override
    public ReturnResult save(BasicManagerPermissions basicManagerPermissions) {
        return null;
    }

    @Override
    public ReturnResult delete(Long t) {
        return null;
    }

    @Override
    public ReturnResult update(BasicManagerPermissions basicManagerPermissions) {
        return null;
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }
}
