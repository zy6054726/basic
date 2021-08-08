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
 * 页面资源表
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@Data
@NoArgsConstructor
public class BasicManagerWebResource extends BaseModel<Page> {
    private static final long serialVersionUID = 4078082523242069872L;
    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源父节点主键
     */
    private Long resourceParentId;

    /**
     * 资源排序编号
     */
    private Integer resourceNo;

    /**
     * 资源图片路径
     */
    private String resourceImg;

    /**
     * 资源接口url
     */
    private String resourceUrl;

    /**
     * 资源分类（0模块 1 页面 2 按钮）
     */
    private Integer resourceType;

    /**
     * 前段文件名
     */
    private String resourceComponent;

    /**
     * 排序
     */
    private Integer resourceSort;
}
