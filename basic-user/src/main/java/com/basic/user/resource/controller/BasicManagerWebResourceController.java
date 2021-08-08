package com.basic.user.resource.controller;


import com.basic.commons.ReturnResult;
import com.basic.user.resource.model.BasicManagerWebResource;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.basic.mybatis.controller.BaseController;

/**
 * <p>
 * 页面资源表 前端控制器
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@RestController
@RequestMapping("/web")
public class BasicManagerWebResourceController extends BaseController<BasicManagerWebResource,Long> {
    @Override
    public ReturnResult save(BasicManagerWebResource basicManagerWebResource) {
        return null;
    }

    @Override
    public ReturnResult delete(Long t) {
        return null;
    }

    @Override
    public ReturnResult update(BasicManagerWebResource basicManagerWebResource) {
        return null;
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }
}
