package com.basic.server.client.service;

import com.basic.commons.ReturnResult;
import com.basic.mybatis.service.BaseService;
import com.basic.server.client.model.entity.ServerOauth;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/3 11:32
 */
public interface IServerOauthService extends BaseService<ServerOauth,Long> {

    ReturnResult findByClient(String clientId);

    /**
     * 分页查询
     * @param serverOauth
     * @return
     */
    ReturnResult pageList(ServerOauth serverOauth);
}
