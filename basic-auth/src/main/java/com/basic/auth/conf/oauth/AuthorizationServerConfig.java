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
 * 服务认证
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
     * 认证管理器
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
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
//                .checkTokenAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()")
                .passwordEncoder(passwordEncoder).allowFormAuthenticationForClients();
//                .addTokenEndpointAuthenticationFilter(new MyBaseicAuthenticationFilter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
////        //抽时间写成数据库配置
//        clients.inMemory()  // 使用in-memory存储
//                .withClient("test")    //client_id用来标识客户的Id  客户端1
//                .resourceIds("test")
//                .authorizedGrantTypes("password","refresh_token","client_credentials","authorization_code")  //允许授权类型   客户端授权模式
//                .scopes("all")  //允许授权范围
////                .authorities("golen-auth-user")  //客户端可以使用的权限
//                .secret(SecureUtil.md5("e16b2ab8d12314bf4efbd6203906ea6c"));  //secret客户端安全码
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //指定认证管理器
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
     * 注入自定义token生成方式（jwt）
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
        // 这里如果设置为false则不能更新refresh_token，如果需要刷新token的功能需要设置成true
        tokenServices.setSupportRefreshToken(true);
        // 设置上次RefreshToken是否还可以使用 默认为true
        tokenServices.setReuseRefreshToken(false);
//        // token有效期自定义设置，默认12小时
        tokenServices.setAccessTokenValiditySeconds(3*24*3600);
//        // refresh_token默认30天
        tokenServices.setRefreshTokenValiditySeconds(7*24*3600);
        tokenServices.setTokenEnhancer(tokenEnhancer());
        return tokenServices;
    }

    /**
     * 对Jwt签名时，增加一个密钥
     * JwtAccessTokenConverter：对Jwt来进行编码以及解码的类
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        //测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
        accessTokenConverter.setSigningKey(ConstantUtil.ENCRYPTION.RSA_KEY);
        return accessTokenConverter;
    }

    /**
     * 自定义token存储器，存入Redis中
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

}
