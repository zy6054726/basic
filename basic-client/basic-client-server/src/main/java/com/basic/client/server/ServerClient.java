package com.basic.client.server;

import com.basic.base.serverName.UrlConstant;
import com.basic.client.server.impl.ServerClientImpl;
import com.basic.commons.ReturnResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/4 17:34
 */
@FeignClient(name = UrlConstant.BASIC_SERVER, fallback = ServerClientImpl.class)
public interface ServerClient {

    @GetMapping("/server/{clientId}")
   public ReturnResult findByClient(@PathVariable("clientId") String clientId);
}
