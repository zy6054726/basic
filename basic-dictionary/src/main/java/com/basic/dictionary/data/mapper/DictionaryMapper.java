package com.basic.dictionary.data.mapper;


import com.basic.dictionary.data.entity.Dictionary;
import com.basic.mybatis.mapper.BaseMappers;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-04
 */
public interface DictionaryMapper extends BaseMappers<Dictionary> {

    List<Dictionary> findByDataId(@Param("dataDicionayId") Long dataDicionayId,@Param("isDel")Boolean isDel);

}
