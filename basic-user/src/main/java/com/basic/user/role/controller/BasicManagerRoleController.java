package com.basic.user.role.controller;


import com.basic.commons.ReturnResult;
import com.basic.user.role.model.BasicManagerRole;
import com.basic.user.role.service.IBasicManagerRoleService;
import org.springframework.web.bind.annotation.*;

import com.basic.mybatis.controller.BaseController;

import javax.annotation.Resource;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@RestController
@RequestMapping("/role")
public class BasicManagerRoleController extends BaseController<BasicManagerRole,Long> {
    @Resource
    private IBasicManagerRoleService iBasicManagerRoleService;
    @PostMapping("/")
    @Override
    public ReturnResult save(@RequestBody BasicManagerRole basicManagerRole) {
        return iBasicManagerRoleService.insert(basicManagerRole);
    }

    @DeleteMapping("/{id}")
    @Override
    public ReturnResult delete(@PathVariable("t") Long t) {
        return iBasicManagerRoleService.deleteById(t);
    }
    @PutMapping("/")
    @Override
    public ReturnResult update(@RequestBody BasicManagerRole basicManagerRole) {
        return iBasicManagerRoleService.update(basicManagerRole);
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }
}
