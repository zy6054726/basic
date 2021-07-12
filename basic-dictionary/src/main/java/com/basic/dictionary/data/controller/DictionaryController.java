package com.basic.dictionary.data.controller;


import com.basic.commons.ReturnResult;
import com.basic.dictionary.data.entity.Dictionary;
import com.basic.dictionary.data.service.IDictionaryService;
import com.basic.mybatis.controller.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-04
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryController extends BaseController<Dictionary,Long> {

    @Resource
    private IDictionaryService iDictionaryService;

    @PostMapping("/")
    @Override
    public ReturnResult save(@RequestBody Dictionary dictionary) {
        return iDictionaryService.insert(dictionary);
    }

    @DeleteMapping("/{t}")
    @Override
    public ReturnResult delete(@PathVariable("t") Long t) {
        return iDictionaryService.deleteById(t);
    }

    @PutMapping("/")
    @Override
    public ReturnResult update(@RequestBody Dictionary dictionary) {
        return iDictionaryService.update(dictionary);
    }

    @GetMapping("/{uuid}")
    @Override
    public ReturnResult findById(@PathVariable Long uuid) {
        return iDictionaryService.findById(uuid);
    }
}
