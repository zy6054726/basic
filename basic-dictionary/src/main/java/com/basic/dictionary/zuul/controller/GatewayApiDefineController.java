package com.basic.dictionary.zuul.controller;


import com.basic.commons.ReturnResult;
import com.basic.dictionary.zuul.model.GatewayApiDefine;
import com.basic.dictionary.zuul.service.IGatewayApiDefineService;
import com.basic.mybatis.controller.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-09
 */
@RestController
@RequestMapping("/zuul")
public class GatewayApiDefineController extends BaseController<GatewayApiDefine,Long> {
    @Resource
    private IGatewayApiDefineService iGatewayApiDefineService;

    @PostMapping("/")
    @Override
    public ReturnResult save(@RequestBody GatewayApiDefine gatewayApiDefine) {
        return iGatewayApiDefineService.insert(gatewayApiDefine);
    }

    @DeleteMapping("/{t}")
    @Override
    public ReturnResult delete(@PathVariable Long t) {
        return iGatewayApiDefineService.deleteById(t);
    }

    @PutMapping("/")
    @Override
    public ReturnResult update(@RequestBody GatewayApiDefine gatewayApiDefine) {
        return iGatewayApiDefineService.update(gatewayApiDefine);
    }
    @GetMapping("/{enable}")
    public ReturnResult findByList(@PathVariable Boolean enable) {
        return iGatewayApiDefineService.findByList(enable);
    }
//    @GetMapping("/")
//    public ReturnResult refreshRoute() {
//        return iGatewayApiDefineService.refreshRoute();
//    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }
}
