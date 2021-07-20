package com.basic.dictionary.zuul.service;

import com.basic.commons.ReturnResult;
import com.basic.dictionary.zuul.model.GatewayApiDefine;
import com.basic.mybatis.service.BaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-09
 */
public interface IGatewayApiDefineService extends BaseService<GatewayApiDefine,Long> {

    /**
     * 查询路由
     * @return
     */
    ReturnResult findByList(Boolean enable);

    /**
     * 分页查询
     * @param gatewayApiDefine
     * @return
     */
    ReturnResult pageList(GatewayApiDefine gatewayApiDefine);
}
