package com.basic.user.resource.controller;


import com.basic.commons.ReturnResult;
import com.basic.user.resource.model.BasicManagerPermissionsWebResource;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.basic.mybatis.controller.BaseController;

/**
 * <p>
 * 权限-资源表 前端控制器
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@RestController
@RequestMapping("/permissionsWeb")
public class BasicManagerPermissionsWebResourceController extends BaseController<BasicManagerPermissionsWebResource,Long> {

    @Override
    public ReturnResult save(BasicManagerPermissionsWebResource basicManagerPermissionsWebResource) {
        return null;
    }

    @Override
    public ReturnResult delete(Long t) {
        return null;
    }

    @Override
    public ReturnResult update(BasicManagerPermissionsWebResource basicManagerPermissionsWebResource) {
        return null;
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }
}
