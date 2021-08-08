package com.basic.user.permissions.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.commons.ReturnResult;
import com.basic.user.permissions.model.BasicManagerPermissions;
import com.basic.user.permissions.service.IBasicManagerPermissionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-31
 */
@Service
@Slf4j
public class BasicManagerPermissionsServiceImpl implements IBasicManagerPermissionsService {
    @Override
    public ReturnResult insert(BasicManagerPermissions basicManagerPermissions) throws RuntimeException {
        return null;
    }

    @Override
    public ReturnResult deleteById(Long t) throws RuntimeException {
        return null;
    }

    @Override
    public ReturnResult update(BasicManagerPermissions basicManagerPermissions) throws RuntimeException {
        return null;
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }
}
