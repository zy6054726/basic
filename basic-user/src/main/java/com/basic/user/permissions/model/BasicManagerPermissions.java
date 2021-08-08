package com.basic.user.permissions.model;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.basic.mybatis.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@Data
@NoArgsConstructor
public class BasicManagerPermissions extends BaseModel<Page> {
    private static final long serialVersionUID = -4661348929229905207L;
    /**
     * 权限名称
     */
    private String permissionsName;

    /**
     * 权限英文标识
     */
    private String permissionsEnglishname;

}
