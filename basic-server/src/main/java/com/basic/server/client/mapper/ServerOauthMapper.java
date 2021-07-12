package com.basic.server.client.mapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.basic.mybatis.mapper.BaseMappers;
import com.basic.server.client.model.entity.ServerOauth;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户端服务
 */
public interface ServerOauthMapper extends BaseMappers<ServerOauth> {

    ServerOauth findByClient(@Param("clientId") String clientId);

    /**
     * 分页条件查询
     * @param page
     * @param serverOauth
     * @return
     */
    List<ServerOauth> pageList(Page page, @Param("serverOauth") ServerOauth serverOauth);
}