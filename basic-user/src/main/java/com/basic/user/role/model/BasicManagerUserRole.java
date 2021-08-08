package com.basic.user.role.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.basic.mybatis.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@Data
@NoArgsConstructor
@TableName("basic_manager_user_role")
public class BasicManagerUserRole extends BaseModel<Page> {

    private static final long serialVersionUID = -1230654746904146837L;
    /**
     * 用户关联主键
     */
    private Long userId;

    /**
     * 角色关联主键
     */
    private Long roleId;
}
