package com.basic.user.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.basic.commons.ConstantUtil;
import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import com.basic.user.manager.model.BasicManagerUser;
import com.basic.user.manager.mapper.BasicManagerUserMapper;
import com.basic.user.manager.model.vo.BasicManagerUserVo;
import com.basic.user.manager.service.IBasicManagerUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 基础管理用户 服务实现类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-05
 */
@Service
@Slf4j
public class BasicManagerUserServiceImpl implements IBasicManagerUserService {

    @Resource
    private BasicManagerUserMapper basicManagerUserMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReturnResult insert(BasicManagerUser basicManagerUser) throws RuntimeException {
        if (Objects.isNull(basicManagerUser)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        basicManagerUser.setCreateTime(LocalDateTime.now());
        basicManagerUser.setPassword(SecureUtil.md5(basicManagerUser.getPassword()));
        return basicManagerUserMapper.insert(basicManagerUser) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_ADD)
                : new ReturnResult(Flag.SYSTEM_ERROR_SAVE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReturnResult deleteById(Long t) throws RuntimeException {
        if (t == null) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        BasicManagerUser basicManagerUser = new BasicManagerUser();
        basicManagerUser.setId(t);
        basicManagerUser.setIsDel(!ConstantUtil.Constant.isDelete);
        return basicManagerUserMapper.updateById(basicManagerUser) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_DELETE)
                : new ReturnResult(Flag.SYSTEM_ERROR_DELETE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReturnResult update(BasicManagerUser basicManagerUser) throws RuntimeException {
        if (Objects.isNull(basicManagerUser)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        if (StrUtil.isNotBlank(basicManagerUser.getPassword())) {
            basicManagerUser.setPassword(SecureUtil.md5(basicManagerUser.getPassword()));
        }
        basicManagerUser.setUpdateTime(LocalDateTime.now());
        return basicManagerUserMapper.updateById(basicManagerUser) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_UPDATE)
                : new ReturnResult(Flag.SYSTEM_ERROR_UPDATE);
    }

    @Override
    public ReturnResult findByManagerUserName(String username) {
        if (StrUtil.isEmpty(username)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        BasicManagerUser basicManagerUser = basicManagerUserMapper.findByManagerUserName(username,
                ConstantUtil.Constant.isDelete,
                ConstantUtil.Constant.isDelete);
        if (Objects.isNull(basicManagerUser)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_QUERY.getCode(), "用户不存在或已锁定");
        }
        return new ReturnResult(basicManagerUser);
    }

    @Override
    public ReturnResult getInfo(String userName) {
        if (StrUtil.isEmpty(userName)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
       BasicManagerUser basicManagerUser =  basicManagerUserMapper.findByManagerUserName(userName, ConstantUtil.Constant.isDelete, ConstantUtil.Constant.isDelete);
       if(Objects.isNull(basicManagerUser)){
           return new ReturnResult(Flag.SYSTEM_ERROR_QUERY.getCode(), "用户不存在或已锁定");
       }
        BasicManagerUserVo basicManagerUserVo = new BasicManagerUserVo();
        BeanUtil.copyProperties(basicManagerUser,basicManagerUserVo);
        return new ReturnResult(basicManagerUserVo);
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }
}
