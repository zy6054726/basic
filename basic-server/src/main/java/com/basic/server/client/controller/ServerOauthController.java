package com.basic.server.client.controller;

import com.basic.commons.ReturnResult;
import com.basic.mybatis.controller.BaseController;
import com.basic.server.client.model.entity.ServerOauth;
import com.basic.server.client.service.IServerOauthService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/3 11:40
 */
@RestController
@RequestMapping("/server")
public class ServerOauthController extends BaseController<ServerOauth,Long> {

    @Resource
    private IServerOauthService iServerOauthService;

    @PostMapping("/")
    @Override
    public ReturnResult save(@RequestBody ServerOauth serverOauth) {
        return iServerOauthService.insert(serverOauth);
    }

    @DeleteMapping("/{t}")
    @Override
    public ReturnResult delete(@PathVariable("t") Long t) {
        return iServerOauthService.deleteById(t);
    }

    @PutMapping("/")
    @Override
    public ReturnResult update(@RequestBody ServerOauth serverOauth) {
        return iServerOauthService.update(serverOauth);
    }

    @GetMapping("/getClient/{clientId}")
    public ReturnResult findByClient(@PathVariable("clientId") String clientId) {
        return iServerOauthService.findByClient(clientId);
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }

    @PostMapping("/page")
    public ReturnResult pageList(@RequestBody ServerOauth serverOauth) {
        return iServerOauthService.pageList(serverOauth);
    }

}
