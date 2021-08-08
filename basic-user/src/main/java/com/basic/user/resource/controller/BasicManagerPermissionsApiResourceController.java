package com.basic.user.resource.controller;


import com.basic.commons.ReturnResult;
import com.basic.user.resource.model.BasicManagerPermissionsApiResource;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.basic.mybatis.controller.BaseController;

/**
 * <p>
 * 权限-接口表 前端控制器
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@RestController
@RequestMapping("/permissionsApi")
public class BasicManagerPermissionsApiResourceController extends BaseController<BasicManagerPermissionsApiResource,Long> {

    @Override
    public ReturnResult save(BasicManagerPermissionsApiResource basicManagerPermissionsApiResource) {
        return null;
    }

    @Override
    public ReturnResult delete(Long t) {
        return null;
    }

    @Override
    public ReturnResult update(BasicManagerPermissionsApiResource basicManagerPermissionsApiResource) {
        return null;
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }
}
