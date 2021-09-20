package com.basic.gateway.route.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.mybatis.model.BaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Mr.zhang
 * @Date: 2021/9/20 16:18
 */
@Data
@NoArgsConstructor
@TableName("gateway_api_define")
public class gateway_route extends BaseModel {


    /**
     * 路由id
     */
    private String serviceId;

    /**
     * 转发地址
     */
    private String url;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 转发API名字
     */
    private String apiName;

    /**
     * 过滤情况
     */
    private String filters;


}
