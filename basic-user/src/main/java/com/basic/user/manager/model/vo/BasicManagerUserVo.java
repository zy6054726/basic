package com.basic.user.manager.model.vo;

import com.basic.commons.basemodel.BModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/11 10:18
 */
@Data
@NoArgsConstructor
public class BasicManagerUserVo extends BModel {

    private static final long serialVersionUID = -7334167275260441271L;
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
     * 手机号
     */
    private String phone;

    /**
     * 是否锁定 0-否 1-是
     */
    private Boolean islock;

}
