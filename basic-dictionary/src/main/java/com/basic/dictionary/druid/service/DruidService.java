package com.basic.dictionary.druid.service;

import com.basic.commons.ReturnResult;
import com.basic.dictionary.druid.model.DruidModel;

/**
 * 数据连接池加密和解密
 * @author: Mr.zhang
 * @Date: 2021/7/30 8:59
 */
public interface DruidService {

    /**
     * 加密
     * @param passWord 需加密密码
     * @return
     */
    ReturnResult encrypt(String passWord);

    /**
     * 解密
     * @param druidModel 解密对象
     * @return
     */
    ReturnResult decrypt(DruidModel druidModel);
}
