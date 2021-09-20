package com.basic.gateway.zuul.mapper;

import com.basic.gateway.zuul.model.GatewayApiDefine;
import com.basic.mybatis.mapper.BaseMappers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-09
 */
@Mapper
@Repository
public interface GatewayApiDefineMapper extends BaseMappers<GatewayApiDefine> {

    /**
     * 根據是否删除以及是否启用查询要转发的路由
     * @param enabled
     * @param isDel
     * @return
     */
    public List<GatewayApiDefine> findByList(@Param("enabled") Boolean enabled, @Param("isDel") Boolean isDel);
}
