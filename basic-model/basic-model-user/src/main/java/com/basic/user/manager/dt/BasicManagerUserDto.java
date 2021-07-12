package com.basic.user.manager.dt;

import com.basic.commons.basemodel.BModel;
import lombok.Data;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/5 16:08
 */
@Data
public class BasicManagerUserDto extends BModel {
    private static final long serialVersionUID = 9108923784877017318L;
    private Long id;
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
