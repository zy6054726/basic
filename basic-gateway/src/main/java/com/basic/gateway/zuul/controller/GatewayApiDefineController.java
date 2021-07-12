package com.basic.gateway.zuul.controller;


import com.basic.commons.ReturnResult;
import com.basic.gateway.zuul.service.ZuulService;
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
public class GatewayApiDefineController {
    @Resource
    private ZuulService zuulService;


    @GetMapping("/")
    public ReturnResult refreshRoute() {
        return zuulService.refreshRoute();
    }
}
