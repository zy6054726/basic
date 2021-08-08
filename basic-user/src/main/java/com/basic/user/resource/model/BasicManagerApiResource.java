package com.basic.user.resource.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.basic.mybatis.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 接口资源表
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@Data
@NoArgsConstructor
public class BasicManagerApiResource extends BaseModel<Page> {


    private static final long serialVersionUID = 97042341668114752L;
    /**
     * 接口名称
     */
    private String apiName;

    /**
     * 接口父节点id
     */
    private Long apiParentId;

    /**
     * 资源接口url
     */
    private String apiUrl;

    /**
     * 资源分类（0模块 1 接口）
     */
    private Integer apiType;

    /**
     * 排序
     */
    private Integer apiSort;

}
