package com.basic.user.role.service.impl;

import com.basic.commons.ConstantUtil;
import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import com.basic.user.role.mapper.BasicManagerUserRoleMapper;
import com.basic.user.role.model.BasicManagerUserRole;
import com.basic.user.role.service.IBasicManagerUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@Slf4j
@Service
public class BasicManagerUserRoleServiceImpl implements IBasicManagerUserRoleService {
    @Resource
    private BasicManagerUserRoleMapper basicManagerUserRoleMapper;

    @Override
    public ReturnResult insert(BasicManagerUserRole basicManagerUserRole) throws RuntimeException {
        basicManagerUserRole.setCreateTime(LocalDateTime.now());
        return basicManagerUserRoleMapper.insert(basicManagerUserRole) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_ADD)
                : new ReturnResult(Flag.SYSTEM_ERROR_SAVE);
    }

    @Override
    public ReturnResult deleteById(Long t) throws RuntimeException {
        BasicManagerUserRole basicManagerUserRole = new BasicManagerUserRole();
        basicManagerUserRole.setRoleId(t);
        basicManagerUserRole.setIsDel(!ConstantUtil.Constant.isDelete);
        return basicManagerUserRoleMapper.updateById(basicManagerUserRole) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_DELETE)
                : new ReturnResult(Flag.SYSTEM_ERROR_DELETE);
    }

    @Override
    public ReturnResult update(BasicManagerUserRole basicManagerUserRole) throws RuntimeException {
        basicManagerUserRole.setUpdateTime(LocalDateTime.now());
        return basicManagerUserRoleMapper.updateById(basicManagerUserRole) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_UPDATE)
                : new ReturnResult(Flag.SYSTEM_ERROR_UPDATE);
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }
}
