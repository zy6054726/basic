package com.basic.dictionary.data.service;

import com.basic.commons.ReturnResult;
import com.basic.dictionary.data.entity.DictType;
import com.basic.mybatis.service.BaseService;

/**
 * <p>
 * 数据字典类型 服务类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-04
 */
public interface IDictTypeService extends BaseService<DictType,Long> {

    /**
     * 分页查询
     * @param dictType
     * @return
     */
    ReturnResult pageList(DictType dictType);

}
