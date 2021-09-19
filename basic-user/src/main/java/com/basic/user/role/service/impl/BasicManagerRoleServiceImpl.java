package com.basic.user.role.service.impl;

import com.basic.commons.ConstantUtil;
import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import com.basic.mybatis.util.ParameterUtils;
import com.basic.user.role.mapper.BasicManagerRoleMapper;
import com.basic.user.role.model.BasicManagerRole;
import com.basic.user.role.service.IBasicManagerRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@Service
@Slf4j
public class BasicManagerRoleServiceImpl implements IBasicManagerRoleService {

    @Resource
    private BasicManagerRoleMapper basicManagerRoleMapper;

    @Override
    public ReturnResult insert(BasicManagerRole basicManagerRole) throws RuntimeException {
        if (ParameterUtils.attributesIsNull(basicManagerRole)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        basicManagerRole.setCreateTime(LocalDateTime.now());
        return basicManagerRoleMapper.insert(basicManagerRole) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_ADD)
                : new ReturnResult(Flag.SYSTEM_ERROR_SAVE);
    }

    @Override
    public ReturnResult deleteById(Long t) throws RuntimeException {
        if (ParameterUtils.attributesIsNull(t)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        BasicManagerRole basicManagerRole = new BasicManagerRole();
        basicManagerRole.setId(t);
        basicManagerRole.setIsDel(!ConstantUtil.Constant.isDelete);
        return basicManagerRoleMapper.updateById(basicManagerRole) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_DELETE)
                : new ReturnResult(Flag.SYSTEM_ERROR_DELETE);
    }

    @Override
    public ReturnResult update(BasicManagerRole basicManagerRole) throws RuntimeException {
        if (ParameterUtils.attributesIsNull(basicManagerRole)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        basicManagerRole.setUpdateTime(LocalDateTime.now());
        return basicManagerRoleMapper.updateById(basicManagerRole) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_UPDATE)
                : new ReturnResult(Flag.SYSTEM_ERROR_UPDATE);
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }
}
