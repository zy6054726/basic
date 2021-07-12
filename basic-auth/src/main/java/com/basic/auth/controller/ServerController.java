package com.basic.auth.controller;

import com.alibaba.fastjson.JSON;
import com.basic.client.server.ServerClient;
import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: Mr.zhang
 * @Date: 2020/12/4 23:44
 */
@Slf4j
@RestController
@RequestMapping("/oauth")
public class ServerController {

    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    private ResourceServerTokenServices resourceServerTokenServices;

    @Autowired
    private AccessTokenConverter accessTokenConverter ;

    @GetMapping("/")
    public String returnTest() {
        return "test";
    }


    @GetMapping("/token")
    public ReturnResult getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.getAccessToken(principal, parameters).getBody());
    }

    @PostMapping("/token")
    public ReturnResult postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
//        String type = parameters.get("type");
//        if (!StrUtil.isEmpty(type)) {
//            parameters.put("username", parameters.get("username")+ ConstantUtil.StringConstant.comma+type);
//        }
        return custom(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }

    /**
     * 自定义返回格式
     *
     * @param accessToken
     * @return
     */
    private ReturnResult custom(OAuth2AccessToken accessToken) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        Map<String, Object> data = new LinkedHashMap(token.getAdditionalInformation());
        log.debug("token包涵的值：{}",JSON.toJSONString(token));
        if (token.getRefreshToken() != null) {
            data.put("refreshToken", token.getRefreshToken().getValue());
        }
        return new ReturnResult(Flag.SUCCESS, data);
    }
}
