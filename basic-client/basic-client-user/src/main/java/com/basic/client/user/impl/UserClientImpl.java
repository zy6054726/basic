package com.basic.client.user.impl;

import com.basic.client.user.UserClient;
import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import org.springframework.stereotype.Service;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/5 16:13
 */
@Service
public class UserClientImpl implements UserClient {
    @Override
    public ReturnResult findByManagerUserName(String username) {
        return new ReturnResult<>(Flag.SYSTEM_SERVER_FALLBACK);
    }
}
