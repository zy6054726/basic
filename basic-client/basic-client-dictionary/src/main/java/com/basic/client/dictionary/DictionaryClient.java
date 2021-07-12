package com.basic.client.dictionary;

import com.basic.base.serverName.UrlConstant;
import com.basic.client.dictionary.impl.DictionaryClientImpl;
import com.basic.commons.ReturnResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/9 14:21
 */
@FeignClient(name = UrlConstant.PUBLIC_DICTIONARY, fallback = DictionaryClientImpl.class)
public interface DictionaryClient {

    @GetMapping("/zuul/{enable}")
    ReturnResult findByList(@PathVariable Boolean enable);
}
