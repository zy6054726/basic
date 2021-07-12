package com.basic.dictionary.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.basic.mybatis.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 * 数据字典类型
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("data_dict_type")
public class DictType extends BaseModel<Page> {


    private static final long serialVersionUID = 8716151256775989771L;
    /**
     * 英文名称
     */
    private String nameEn;

    /**
     * 中文名称
     */
    private String nameCn;

    /**
     * 系统类别
     */
    private Integer systemCategory;

    /**
     * 系统名称
     */
    private String systemName;
}
