package com.basic.auth.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import com.basic.client.server.ServerClient;
import com.basic.commons.ReturnResult;
import com.basic.server.client.model.vo.ServerOauthVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/3 11:38
 */
@Service
@Log4j2
public class ServerOauthServiceImpl implements ClientDetailsService {

    @Resource
    private ServerClient serverClient;

    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        ReturnResult serverOauthVo = serverClient.findByClient(s);

        if (Objects.isNull(serverOauthVo.getObj())) {
            throw new RuntimeException("该client_id不存在");
        }
        ServerOauthVo serverOauth = Convert.convert(ServerOauthVo.class,serverOauthVo.getObj());

        BaseClientDetails clientDetails = new BaseClientDetails();
        //客户端(client)id
        clientDetails.setClientId(s);
        //客户端所能访问的资源id集合
//        clientDetails.setResourceIds();
//客户端(client)的访问密匙
        clientDetails.setClientSecret(SecureUtil.md5(serverOauth.getClientSecret()));
//        客户端支持的grant_type授权类型
        clientDetails.setAuthorizedGrantTypes(Arrays.asList(serverOauth.getAuthorizedGrantTypes().split(",")));
        //客户端申请的权限范围
        clientDetails.setScope(Arrays.asList(serverOauth.getScpoe().split(",")));
        Long accessTokenValidity = serverOauth.getAccessTokenValidity();
        if (accessTokenValidity != null && accessTokenValidity > 0) {
            //设置token的有效期，不设置默认12小时
            clientDetails.setAccessTokenValiditySeconds(accessTokenValidity.intValue());
        }
        Long refreshTokenValidity = serverOauth.getRefreshTokenValidity();
        if (refreshTokenValidity != null && refreshTokenValidity > 0) {
            //设置刷新token的有效期，不设置默认30天
            clientDetails.setRefreshTokenValiditySeconds(refreshTokenValidity.intValue());
        }
        Set<String> set = new HashSet<>();
        set.add(serverOauth.getRedirectUri());
        clientDetails.setRegisteredRedirectUri(set);
        clientDetails.isAutoApprove(serverOauth.getAutoApprove());
        log.info("clientId是：{}", s);

        return clientDetails;
    }

}
