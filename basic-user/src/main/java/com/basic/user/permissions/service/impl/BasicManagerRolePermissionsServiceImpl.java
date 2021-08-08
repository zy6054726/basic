package com.basic.user.permissions.service.impl;

import com.basic.commons.ReturnResult;
import com.basic.user.permissions.model.BasicManagerRolePermissions;
import com.basic.user.permissions.service.IBasicManagerRolePermissionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限-角色表 服务实现类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@Service
@Slf4j
public class BasicManagerRolePermissionsServiceImpl implements IBasicManagerRolePermissionsService {
    @Override
    public ReturnResult insert(BasicManagerRolePermissions basicManagerRolePermissions) throws RuntimeException {
        return null;
    }

    @Override
    public ReturnResult deleteById(Long t) throws RuntimeException {
        return null;
    }

    @Override
    public ReturnResult update(BasicManagerRolePermissions basicManagerRolePermissions) throws RuntimeException {
        return null;
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }
}
