package com.basic.client.user;

import com.basic.base.serverName.UrlConstant;
import com.basic.client.user.impl.UserClientImpl;
import com.basic.commons.ReturnResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/5 16:12
 */
@FeignClient(name = UrlConstant.BASIC_USER, fallback = UserClientImpl.class)
public interface UserClient {
    @GetMapping("/user/{username}")
    ReturnResult findByManagerUserName(@PathVariable("username") String username);
}
