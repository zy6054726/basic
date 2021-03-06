package com.basic.user.conf;

import cn.hutool.core.map.MapUtil;
import com.basic.auth.error.CustomExceptionTranslator;
import com.basic.commons.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/15 12:32
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore jwtTokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resource) throws Exception {
        resource.resourceId("test").tokenStore(jwtTokenStore);
        OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
        authenticationEntryPoint.setExceptionTranslator(new CustomExceptionTranslator());
        resource.authenticationEntryPoint(authenticationEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/getUser/**").permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }


    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        // ?????????????????????false???????????????refresh_token?????????????????????token????????????????????????true
        tokenServices.setSupportRefreshToken(true);
        // ????????????RefreshToken????????????????????? ?????????true
        tokenServices.setReuseRefreshToken(false);
        // token?????????????????????????????????12??????
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 6);
        // refresh_token??????30???
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 8);
        tokenServices.setTokenEnhancer(tokenEnhancer());
        return tokenServices;
    }

    /**
     * ???????????????token???????????????jwt???
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new TokenEnhancer() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
                Map<String, Object> maps = MapUtil.newHashMap();
                maps.put("token", oAuth2AccessToken.getValue());
                maps.put("refreshToken", oAuth2AccessToken.getRefreshToken());
                maps.put("tokenType", oAuth2AccessToken.getTokenType());
//                maps.put("scope", oAuth2AccessToken.getScope());
                maps.put("expire_in", oAuth2AccessToken.getExpiresIn());
                ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(maps);
                return oAuth2AccessToken;
            }
        };
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey(ConstantUtil.ENCRYPTION.RSA_KEY);
        accessTokenConverter.setVerifierKey(ConstantUtil.ENCRYPTION.RSA_KEY);
        return accessTokenConverter;
    }

    /**
     * ?????????token??????????????????Redis???
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory2());
    }



    public RedisConnectionFactory redisConnectionFactory2() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        jedisConnectionFactory.setHostName(hostName);
        jedisConnectionFactory.setPassword(passWord);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setDatabase(database);
        return jedisConnectionFactory;
    }
    @Value(value = "${spring.redis-auth.host}")
    private String hostName;
    @Value(value = "${spring.redis-auth.port}")
    private Integer port;
    @Value(value = "${spring.redis-auth.password}")
    private String passWord;
    @Value(value = "${spring.redis-auth.database}")
    private Integer database;
}
