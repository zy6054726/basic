package com.basic.dictionary.zuul.model.vo;

import com.basic.commons.basemodel.BModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/16 17:38
 */
@Data
@NoArgsConstructor
public class GatewayApiDefineVo extends BModel {

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
     * 地址前缀 0-否 1-是
     */
    private Boolean stripPrefix;

    /**
     * api的名字
     */
    private String apiName;

    /**
     * 是否显示
     */
    private Boolean isShow;
}
