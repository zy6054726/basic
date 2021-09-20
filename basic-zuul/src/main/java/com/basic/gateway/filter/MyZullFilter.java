package com.basic.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Mr.zhang
 * @Date: 2021/9/19 19:20
 */
@Slf4j
@Component
public class MyZullFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //表示在之前过滤，一般都是之前，之后是post
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //过滤器执行顺序，0优先级最高
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //当前过滤器是否开启，true开启，false不开启
        return Boolean.TRUE;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext=RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String header = request.getHeader("Authorization");
        if (header != null && "".equals(header)) {
            requestContext.addZuulRequestHeader("Authorization",header);
        }
//        log.info("获取请求类型：{}",request.getContentType());
//        if (!StrUtil.isEmpty(request.getContentType()) && request.getContentType().contains("form-data")) {
//            return null;
//        }
//        try {
//            String method = request.getMethod();
//            // 转化成json
////            JSONObject json = JSONObject.fromObject(body);
//            // get方法和post、put方法处理方式不同
////            if ("GET".equals(method)) {
////                    // 关键步骤，一定要get一下,下面才能取到值requestQueryParams
////                    request.getParameterMap();
////                    Map<String, List<String>> requestQueryParams = requestContext.getRequestQueryParams();
////                    if (requestQueryParams == null) {
////                        requestQueryParams = new HashMap<>();
////                    }
////                    List<String> arrayList = new ArrayList<>();
////                    String key = "key";
////                    String aes_decodedStr = AESUtil.getInstance().decode(name, key);
////                    arrayList.add(aes_decodedStr + "");
////                    requestQueryParams.put("decodename", arrayList);
////                    requestContext.setRequestQueryParams(requestQueryParams);
////            }// post和put需重写HttpServletRequestWrapper
////            else
//            if ("POST".equals(method) || "PUT".equals(method)) {
//                log.info(String.format("%s >>> %s", method, request.getRequestURL().toString()));
//                // 获取请求的输入流
//                InputStream in = request.getInputStream();
//                String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
//                // 如果body为空 初始化为空json
//                if (StrUtil.isBlank(body)) {
//                    body = "{}";
//                }
//                final byte[] reqBodyBytes = body.getBytes();
//                // 重写上下文的HttpServletRequestWrapper
//                requestContext.setRequest(new HttpServletRequestWrapper(request) {
//                    @Override
//                    public ServletInputStream getInputStream() throws IOException {
//                        return new ServletInputStreamWrapper(reqBodyBytes);
//                    }
//                    @Override
//                    public int getContentLength() {
//                        return reqBodyBytes.length;
//                    }
//                    @Override
//                    public long getContentLengthLong() {
//                        return reqBodyBytes.length;
//                    }
//                });
//            }
//
//        } catch (IOException e) {
//            log.error("网关处理数据异常:{}",e.getMessage());
//            e.printStackTrace();
//        }
        return null;
    }
}
