package com.basic.dictionary.druid.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.filter.config.ConfigTools;
import com.basic.commons.ConstantUtil;
import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import com.basic.dictionary.druid.model.DruidModel;
import com.basic.dictionary.druid.service.DruidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/30 9:02
 */
@Service
@Slf4j
public class DruidServiceImpl implements DruidService {
    @Override
    public ReturnResult encrypt(String passWord) {
        DruidModel druidModel = new DruidModel();
        String[] arr = new String[0];
        try {
            arr = ConfigTools.genKeyPair(ConstantUtil.ByteLengthConstant.BYTE_LENGTH);
            druidModel.setPassword(ConfigTools.encrypt(arr[0], passWord));
            druidModel.setPrivateKey(arr[0]);
            druidModel.setPublicKey(arr[1]);
        } catch (NoSuchAlgorithmException e) {
            log.error("获取公私钥算法异常：{}",e);
        } catch (NoSuchProviderException e) {
            log.error("获取公私钥异常：{}",e);
        } catch (Exception e) {
            log.error("密码加密异常: {}", e);
        }
        return new ReturnResult(druidModel);
    }

    @Override
    public ReturnResult decrypt(DruidModel druidModel) {
        if (StrUtil.isEmpty(druidModel.getPassword()) || StrUtil.isEmpty(druidModel.getPublicKey())) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        try {
            return new ReturnResult(ConfigTools.decrypt(druidModel.getPublicKey(), druidModel.getPassword()));
        } catch (Exception e) {
            log.error("解密异常：{}",e);
        }
        return new ReturnResult(Flag.SYSTEM_ERROR,"解密失败，请确定公钥和加密后密码是否正确");
    }
}
