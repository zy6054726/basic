package com.basic.server.client.model.vo;

import com.basic.commons.basemodel.BModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/4 17:13
 */
@Data
@NoArgsConstructor
public class ServerOauthVo extends BModel {
    private static final long serialVersionUID = -7182058735199106837L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 资源id
     */
    private String resourceId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 范围
     */
    private String scpoe;

    /**
     * 授权类型
     */
    private String authorizedGrantTypes;

    /**
     * 令牌有效时长
     */
    private Long accessTokenValidity;

    /**
     * 刷新令牌时长
     */
    private Long refreshTokenValidity;

    /**
     * 自动允许
     */
    private String autoApprove;

    /**
     * 重定向地址
     */
    private String redirectUri;
}
