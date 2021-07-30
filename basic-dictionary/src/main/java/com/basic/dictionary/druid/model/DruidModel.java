package com.basic.dictionary.druid.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/30 8:48
 */
@Data
@NoArgsConstructor
public class DruidModel implements Serializable {
    private static final long serialVersionUID = 1247056403384172373L;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 密码
     */
    private String password;

}
