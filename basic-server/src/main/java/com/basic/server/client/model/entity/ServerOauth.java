package com.basic.server.client.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.basic.mybatis.model.BaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName("server_oauth")
public class ServerOauth extends BaseModel<Page> {
    private static final long serialVersionUID = 1002908916083328306L;

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