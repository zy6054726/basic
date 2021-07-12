package com.basic.dictionary.data.controller;


import com.basic.commons.ReturnResult;
import com.basic.dictionary.data.entity.DictType;
import com.basic.dictionary.data.service.IDictTypeService;
import com.basic.mybatis.controller.BaseController;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 数据字典类型 前端控制器
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-04
 */
@RestController
@RequestMapping("/dicttype")
public class DictTypeController extends BaseController<DictType,Long> {
    @Resource
    private IDictTypeService iDictTypeService;

    @PostMapping("/")
    @Override
    public ReturnResult save(@RequestBody DictType dictType) {
        return iDictTypeService.insert(dictType);
    }

    @DeleteMapping("/{t}")
    @Override
    public ReturnResult delete(@PathVariable("t") Long t) {
        return iDictTypeService.deleteById(t);
    }

    @PutMapping("/")
    @Override
    public ReturnResult update(@RequestBody DictType dictType) {
        return iDictTypeService.update(dictType);
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }

    @PostMapping("/page")
    public ReturnResult pageList(@RequestBody DictType dictType) {
        return iDictTypeService.pageList(dictType);
    }
}
