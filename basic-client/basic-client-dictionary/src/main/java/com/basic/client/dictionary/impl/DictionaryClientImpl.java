package com.basic.client.dictionary.impl;

import com.basic.client.dictionary.DictionaryClient;
import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import org.springframework.stereotype.Service;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/9 14:21
 */
@Service
public class DictionaryClientImpl implements DictionaryClient {
    @Override
    public ReturnResult findByList(Boolean enable) {
        return new ReturnResult(Flag.SYSTEM_SERVER_FALLBACK);
    }
}
