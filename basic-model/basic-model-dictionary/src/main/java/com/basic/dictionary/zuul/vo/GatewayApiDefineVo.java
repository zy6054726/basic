package com.basic.dictionary.zuul.vo;

import com.basic.commons.basemodel.BModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/9 14:43
 */
@Data
@NoArgsConstructor
public class GatewayApiDefineVo extends BModel {
    private static final long serialVersionUID = -6934271003824085872L;

    /**
     * 主键
     */
    private Long id;

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
     * 地址前缀
     */
    private Integer stripPrefix;

    /**
     * api的名字
     */
    private String apiName;
}
