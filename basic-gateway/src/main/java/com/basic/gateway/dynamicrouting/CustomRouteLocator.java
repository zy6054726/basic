package com.basic.gateway.dynamicrouting;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.basic.commons.ConstantUtil;
import com.basic.gateway.zuul.mapper.GatewayApiDefineMapper;
import com.basic.gateway.zuul.model.GatewayApiDefine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 动态路由
 *
 * @author: Mr.zhang
 * @Date: 2021/7/8 14:52
 */
@Slf4j
public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {
    /**
     * zuul路由配置
     */
    private ZuulProperties properties;

    /**
     * 获取动态路由数据
     */
    private GatewayApiDefineMapper gatewayApiDefineMapper;


    public CustomRouteLocator(String servletPath, ZuulProperties properties,
                              GatewayApiDefineMapper gatewayApiDefineMapper) {
        super(servletPath, properties);
        this.properties = properties;
        this.gatewayApiDefineMapper = gatewayApiDefineMapper;
        log.info("servletPath:{}", servletPath);
    }


    @Override
    public void refresh() {
        doRefresh();
    }

    /**
     * @author:XingWL
     * @description:路由规则加载算法
     * @date: 2019/4/27 18:17
     */
    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<String, ZuulProperties.ZuulRoute>();
        //从application.properties中加载路由信息
        routesMap.putAll(super.locateRoutes());
        //从db中加载路由信息
        routesMap.putAll(locateRoutesFromDB());
        //优化一下配置
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            // Prepend with slash if not already present.
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return values;
    }

    private Map<String, ZuulProperties.ZuulRoute> locateRoutesFromDB() {
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
        List<GatewayApiDefine> gatewayApiDefines = gatewayApiDefineMapper.findByList(Boolean.TRUE,ConstantUtil.Constant.isDelete);
        if (CollUtil.isEmpty(gatewayApiDefines)) {
            return routes;
        }
        gatewayApiDefines.forEach(s -> {
            if (StrUtil.isEmpty(s.getPath()) || StrUtil.isEmpty(s.getServiceId())) {
                return;
            }
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            zuulRoute.setSensitiveHeaders(new LinkedHashSet());
            BeanUtil.copyProperties(s, zuulRoute);
            zuulRoute.setId(zuulRoute.getServiceId());
            routes.put(zuulRoute.getPath(), zuulRoute);
        });
        return routes;
    }
}
