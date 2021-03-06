package com.basic.auth.conf.oauth;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.basic.commons.ConstantUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.Map;

/**
 * ????????????
 * @author: Mr.zhang
 */
@Configuration
@EnableAuthorizationServer
@Order(100)
@Slf4j
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * ???????????????
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyPasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("serverOauthServiceImpl")
    private ClientDetailsService clientDetailsService;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // ??????/oauth/token_key???????????????????????????
                .tokenKeyAccess("permitAll()")
                // ??????/oauth/check_token??????????????????????????????
//                .checkTokenAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()")
                .passwordEncoder(passwordEncoder).allowFormAuthenticationForClients();
//                .addTokenEndpointAuthenticationFilter(new MyBaseicAuthenticationFilter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
////        //??????????????????????????????
//        clients.inMemory()  // ??????in-memory??????
//                .withClient("test")    //client_id?????????????????????Id  ?????????1
//                .resourceIds("test")
//                .authorizedGrantTypes("password","refresh_token","client_credentials","authorization_code")  //??????????????????   ?????????????????????
//                .scopes("all")  //??????????????????
////                .authorities("golen-auth-user")  //??????????????????????????????
//                .secret(SecureUtil.md5("e16b2ab8d12314bf4efbd6203906ea6c"));  //secret??????????????????
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //?????????????????????
        endpoints.authenticationManager(authenticationManager)
//                .userDetailsService(userDetailsService)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET,HttpMethod.DELETE,HttpMethod.PUT)
                .tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter())
//                .tokenEnhancer(tokenEnhancerChain)
                .tokenServices(defaultTokenServices())
                .reuseRefreshTokens(true);
    }

    /**
     * ???????????????token???????????????jwt???
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new TokenEnhancer() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
//                Object obj = oAuth2Authentication.getPrincipal();
                Map<String, Object> maps = MapUtil.newHashMap();
                maps.put("token", oAuth2AccessToken.getValue());
                maps.put("refreshToken", oAuth2AccessToken.getRefreshToken());
                maps.put("tokenType", oAuth2AccessToken.getTokenType());
//                maps.put("scope", oAuth2AccessToken.getScope());
                maps.put("expire_in", oAuth2AccessToken.getExpiresIn());
//
                ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(maps);
                return oAuth2AccessToken;
            }
        };


//        return new JwtTokenEnhancer();
    }

    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        // ?????????????????????false???????????????refresh_token?????????????????????token????????????????????????true
        tokenServices.setSupportRefreshToken(true);
        // ????????????RefreshToken????????????????????? ?????????true
        tokenServices.setReuseRefreshToken(false);
//        // token?????????????????????????????????12??????
        tokenServices.setAccessTokenValiditySeconds(3*24*3600);
//        // refresh_token??????30???
        tokenServices.setRefreshTokenValiditySeconds(7*24*3600);
        tokenServices.setTokenEnhancer(tokenEnhancer());
        return tokenServices;
    }

    /**
     * ???Jwt??????????????????????????????
     * JwtAccessTokenConverter??????Jwt?????????????????????????????????
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        //?????????,??????????????????????????????????????????????????????????????????,??????????????????RSA?????????????????????
        accessTokenConverter.setSigningKey(ConstantUtil.ENCRYPTION.RSA_KEY);
        return accessTokenConverter;
    }

    /**
     * ?????????token??????????????????Redis???
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

}
