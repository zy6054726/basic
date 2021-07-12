package com.basic.user.manager.controller;


import com.basic.commons.ReturnResult;
import com.basic.mybatis.controller.BaseController;
import com.basic.user.manager.model.BasicManagerUser;
import com.basic.user.manager.service.IBasicManagerUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 基础管理用户 前端控制器
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-05
 */
@RestController
@RequestMapping("/user")
public class BasicManagerUserController extends BaseController<BasicManagerUser,Long> {

    @Resource
    private IBasicManagerUserService iBasicManagerUserService;

    @PostMapping("/")
    @Override
    public ReturnResult save(@RequestBody BasicManagerUser basicManagerUser) {
        return iBasicManagerUserService.insert(basicManagerUser);
    }

    @DeleteMapping("/{t}")
    @Override
    public ReturnResult delete(@PathVariable("t") Long t) {
        return iBasicManagerUserService.deleteById(t);
    }

    @PutMapping("/")
    @Override
    public ReturnResult update(@RequestBody BasicManagerUser basicManagerUser) {
        return iBasicManagerUserService.update(basicManagerUser);
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }

    @GetMapping("/{username}")
   public ReturnResult findByManagerUserName(@PathVariable("username") String username) {
        return iBasicManagerUserService.findByManagerUserName(username);
    }

    @GetMapping("/getInfo/{userName}")
    public ReturnResult getInfo(@PathVariable("userName") String userName) {
        return iBasicManagerUserService.getInfo(userName);
    }
}
