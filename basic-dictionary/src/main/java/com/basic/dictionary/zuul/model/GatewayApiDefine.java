package com.basic.dictionary.zuul.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.basic.mybatis.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gateway_api_define")
public class GatewayApiDefine extends BaseModel<Page> {
    private static final long serialVersionUID = 4186151896292287210L;
    /**
     * 请求路径
     */
    private String path;

    /**
     * 服务名
     */
    private String serviceId;

    /**
     * 转发地址
     */
    private String url;

    /**
     * 是否可重试 0-否 1-是
     */
    private Boolean retryable;

    /**
     * 是否启用 1- 是 0- 否
     */
    private Boolean enabled;

    /**
     * 地址前缀 0-否 1-是
     */
    private Boolean stripPrefix;

    /**
     * api的名字
     */
    private String apiName;


}
