package com.basic.user.role.model;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.basic.mybatis.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@Data
@NoArgsConstructor
public class BasicManagerRole extends BaseModel<Page> {
    private static final long serialVersionUID = -5888756315054058996L;
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类型（0 超级管理员1 管理员 2 普通）
     */
    private Integer roleType;

    /**
     * 角色英文名
     */
    private String roleEnglishname;
}
