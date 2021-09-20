package com.basic.gateway.controller;

import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mr.zhang
 * @Date: 2021/9/20 13:17
 */
@RestController
public class GateWayErrorController {

    @GetMapping("/fallback")
    public ReturnResult selectList() {
        return new ReturnResult(Flag.ERROR_GATEWAY_TIMEOUT);
    }

}
