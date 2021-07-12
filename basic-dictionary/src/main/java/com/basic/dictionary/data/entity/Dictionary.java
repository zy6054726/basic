package com.basic.dictionary.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.mybatis.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 数据字典
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("data_dictionary")
public class Dictionary extends BaseModel<Dictionary> {


    private static final long serialVersionUID = -8829051074788888052L;
    /**
     * 字典数据名
     */
    private String label;

    /**
     * 字典类型
     */
    private Long dataDicionayId;

    /**
     * 字典数据值
     */
    private Integer dataValue;

    /**
     * 排序
     */
    private Integer sort;
}
