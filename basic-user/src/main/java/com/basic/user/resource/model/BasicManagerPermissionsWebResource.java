package com.basic.user.resource.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.basic.mybatis.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 权限-资源表
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@Data
@NoArgsConstructor
public class BasicManagerPermissionsWebResource extends BaseModel<Page> {

    private static final long serialVersionUID = -5189432995408977364L;
    /**
     * 权限主键
     */
    private Long permissionsId;

    /**
     * 资源主键
     */
    private Long resourceId;
}
