package com.basic.dictionary.zuul.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.basic.commons.ConstantUtil;
import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import com.basic.dictionary.zuul.mapper.GatewayApiDefineMapper;
import com.basic.dictionary.zuul.model.GatewayApiDefine;
import com.basic.dictionary.zuul.model.vo.GatewayApiDefineVo;
import com.basic.dictionary.zuul.service.IGatewayApiDefineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-09
 */
@Service
@Slf4j
public class GatewayApiDefineServiceImpl implements IGatewayApiDefineService {
    @Resource
    private GatewayApiDefineMapper gatewayApiDefineMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReturnResult insert(GatewayApiDefine gatewayApiDefine) throws RuntimeException {
        if (Objects.isNull(gatewayApiDefine)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        gatewayApiDefine.setCreateTime(LocalDateTime.now());
        return gatewayApiDefineMapper.insert(gatewayApiDefine) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_ADD)
                : new ReturnResult(Flag.SYSTEM_ERROR_SAVE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReturnResult deleteById(Long t) throws RuntimeException {
        if (t == null) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        GatewayApiDefine gatewayApiDefine = new GatewayApiDefine();
        gatewayApiDefine.setId(t);
        gatewayApiDefine.setIsDel(!ConstantUtil.Constant.isDelete);
        return gatewayApiDefineMapper.updateById(gatewayApiDefine) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_DELETE)
                : new ReturnResult(Flag.SYSTEM_ERROR_DELETE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReturnResult update(GatewayApiDefine gatewayApiDefine) throws RuntimeException {
        if (Objects.isNull(gatewayApiDefine)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        gatewayApiDefine.setUpdateTime(LocalDateTime.now());
        return gatewayApiDefineMapper.updateById(gatewayApiDefine) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_UPDATE)
                : new ReturnResult(Flag.SYSTEM_ERROR_UPDATE);
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }

    @Override
    public ReturnResult findByList(Boolean enable) {
        if (!enable || enable == null) {
            enable = Boolean.TRUE;
        }
        return new ReturnResult(gatewayApiDefineMapper.findByList(enable, ConstantUtil.Constant.isDelete));
    }

    @Override
    public ReturnResult pageList(GatewayApiDefine gatewayApiDefine) {
        gatewayApiDefine.setIsDel(ConstantUtil.Constant.isDelete);
        List<GatewayApiDefine> gatewayApiDefines = gatewayApiDefineMapper.pageList(gatewayApiDefine.getP(), gatewayApiDefine);
        List<GatewayApiDefineVo> gatewayApiDefineVos = CollUtil.newArrayList();
        if (CollUtil.isNotEmpty(gatewayApiDefines)) {
            gatewayApiDefines.forEach(s -> {
                GatewayApiDefineVo gatewayApiDefineVo = new GatewayApiDefineVo();
                BeanUtil.copyProperties(s,gatewayApiDefineVo);
                gatewayApiDefineVo.setIsShow(ConstantUtil.Constant.isDelete);
                gatewayApiDefineVos.add(gatewayApiDefineVo);
            });
        }
        return new ReturnResult(gatewayApiDefineVos,gatewayApiDefine.getP());
    }
}
