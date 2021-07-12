package com.basic.client.server.impl;

import com.basic.client.server.ServerClient;
import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import org.springframework.stereotype.Service;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/4 22:33
 */
@Service
public class ServerClientImpl implements ServerClient {

    @Override
    public ReturnResult findByClient(String clientId) {
        return new ReturnResult<>(Flag.SYSTEM_SERVER_FALLBACK);
    }
}
