package com.basic.user.resource.controller;


import com.basic.commons.ReturnResult;
import com.basic.user.resource.model.BasicManagerApiResource;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.basic.mybatis.controller.BaseController;

/**
 * <p>
 * 接口资源表 前端控制器
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@RestController
@RequestMapping("/api")
public class BasicManagerApiResourceController extends BaseController<BasicManagerApiResource,Long> {
    @Override
    public ReturnResult save(BasicManagerApiResource basicManagerApiResource) {
        return null;
    }

    @Override
    public ReturnResult delete(Long t) {
        return null;
    }

    @Override
    public ReturnResult update(BasicManagerApiResource basicManagerApiResource) {
        return null;
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }
}
