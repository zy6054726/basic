package com.basic.dictionary.druid.controller;

import com.basic.commons.ReturnResult;
import com.basic.dictionary.druid.model.DruidModel;
import com.basic.dictionary.druid.service.DruidService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/30 9:21
 */
@RestController
@RequestMapping("/druid")
public class DruidController {

    @Resource
    private DruidService druidService;

    /**
     * 加密
     *
     * @param passWord 需加密密码
     * @return
     */
    @GetMapping("/{passWord}")
    public ReturnResult encrypt(@PathVariable("passWord") String passWord) {
        return druidService.encrypt(passWord);
    }

    /**
     * 解密
     *
     * @param druidModel 解密对象
     * @return
     */
    @PostMapping("/")
    public ReturnResult decrypt(@RequestBody DruidModel druidModel) {
        return druidService.decrypt(druidModel);
    }
}
