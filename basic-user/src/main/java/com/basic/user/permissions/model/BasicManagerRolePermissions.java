package com.basic.user.permissions.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.basic.mybatis.model.BaseModel;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限-角色表
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@Data
@NoArgsConstructor
public class BasicManagerRolePermissions extends BaseModel<Page> {

    private static final long serialVersionUID = -7492112396497195838L;
    /**
     * 角色主键
     */
    private Long roleId;

    /**
     * 权限主键
     */
    private Long permissionsId;

}
