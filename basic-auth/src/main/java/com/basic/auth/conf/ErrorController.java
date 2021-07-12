package com.basic.auth.conf;

import cn.hutool.core.convert.Convert;
import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

/**
 * @author: Mr.zhang
 */
@Slf4j
@ControllerAdvice
public class ErrorController implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("请求返回数据类型class="+ o.getClass().getName());
        if (o.toString().contains("error") && o.toString().contains("Unauthorized")){
            log.error("无权访问信息:{}",o);
            return new ReturnResult(Flag.SYSTEM_USERNAME_NOT_PERMISSION,o);
        }else if (o.toString().contains("error")){
            Map authModel = Convert.convert(Map.class, o);
            return new ReturnResult(authModel.get("status"),authModel.get("error"));
        }
        if (log.isDebugEnabled()) {
            log.debug("请求返回数据body=     " + o.toString());
        }
        return o;
    }

}
