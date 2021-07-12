package com.basic.user.manager.vo;

import com.basic.commons.basemodel.BModel;
import lombok.Data;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/5 16:09
 */
@Data
public class BasicManagerUserVo extends BModel {
    private static final long serialVersionUID = -3853762637824358540L;
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
