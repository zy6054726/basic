package com.basic.dictionary.data.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.basic.dictionary.data.entity.DictType;
import com.basic.mybatis.mapper.BaseMappers;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 数据字典类型 Mapper 接口
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-04
 */
public interface DictTypeMapper extends BaseMappers<DictType> {

    List<DictType> pageList(Page page, @Param("dictType") DictType dictType);

}
