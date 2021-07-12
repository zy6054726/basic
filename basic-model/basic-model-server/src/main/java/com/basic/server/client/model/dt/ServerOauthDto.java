package com.basic.server.client.model.dt;

import com.basic.commons.basemodel.BModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/4 17:09
 */
@Data
@NoArgsConstructor
public class ServerOauthDto extends BModel {
    private static final long serialVersionUID = 7648817164276307444L;
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
}
