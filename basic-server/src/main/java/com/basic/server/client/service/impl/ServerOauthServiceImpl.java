package com.basic.server.client.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.basic.commons.ConstantUtil;
import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import com.basic.server.client.mapper.ServerOauthMapper;
import com.basic.server.client.model.entity.ServerOauth;
import com.basic.server.client.service.IServerOauthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author: Mr.zhang
 * @Date: 2021/7/3 11:38
 */
@Service
@Log4j2
public class ServerOauthServiceImpl implements IServerOauthService {

    @Resource
    private ServerOauthMapper serverOauthMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReturnResult insert(ServerOauth serverOauth) throws RuntimeException {
        if (Objects.isNull(serverOauth)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        serverOauth.setClientSecret(SecureUtil.md5(serverOauth.getClientSecret()));
        serverOauth.setCreateTime(LocalDateTime.now());
        return serverOauthMapper.insert(serverOauth) > 0
                ? new ReturnResult<>(Flag.SYSTEM_SUCCESS_ADD)
                : new ReturnResult<>(Flag.SYSTEM_ERROR_SAVE);
    }

    @Override
    public ReturnResult deleteById(Long t) throws RuntimeException {
        if (t == null) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        ServerOauth serverOauth = new ServerOauth();
        serverOauth.setId(t);
        serverOauth.setIsDel(!ConstantUtil.Constant.isDelete);
        return serverOauthMapper.updateById(serverOauth) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_DELETE)
                : new ReturnResult(Flag.SYSTEM_ERROR_DELETE);
    }

    @Override
    public ReturnResult update(ServerOauth serverOauth) throws RuntimeException {
        if (Objects.isNull(serverOauth)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        serverOauth.setUpdateTime(LocalDateTime.now());
        if (StrUtil.isNotBlank(serverOauth.getClientSecret())) {
            serverOauth.setClientSecret(SecureUtil.md5(serverOauth.getClientSecret()));
        }
        return serverOauthMapper.updateById(serverOauth) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_UPDATE)
                : new ReturnResult(Flag.SYSTEM_ERROR_UPDATE);
    }

    @Override
    public ReturnResult findByClient(String clientId) {
        if (StrUtil.isEmpty(clientId)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        return new ReturnResult(serverOauthMapper.findByClient(clientId));
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }

    @Override
    public ReturnResult pageList(ServerOauth serverOauth) {
        serverOauth.setIsDel(ConstantUtil.Constant.isDelete);
        List<ServerOauth> serverOauths = serverOauthMapper.pageList(serverOauth.getP(), serverOauth);
        return new ReturnResult(Flag.SYSTEM_SUCCESS_QUERY,serverOauths,serverOauth.getP());
    }
}
