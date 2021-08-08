package com.basic.user.manager.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.basic.mybatis.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 基础管理用户
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("basic_manager_user")
public class BasicManagerUser extends BaseModel<Page> {


    private static final long serialVersionUID = 9011881491697753190L;
    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String actualname;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 是否锁定 0-否 1-是
     */
    private Boolean islock;

}
