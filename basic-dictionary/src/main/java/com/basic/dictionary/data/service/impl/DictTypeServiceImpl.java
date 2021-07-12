package com.basic.dictionary.data.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import com.basic.dictionary.data.entity.DictType;
import com.basic.dictionary.data.mapper.DictTypeMapper;
import com.basic.dictionary.data.service.IDictTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 数据字典类型 服务实现类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-04
 */
@Service
@Slf4j
public class DictTypeServiceImpl implements IDictTypeService {
    @Resource
    private DictTypeMapper dictTypeMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReturnResult insert(DictType dictType) throws RuntimeException {
        if (Objects.isNull(dictType)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        dictType.setCreateTime(LocalDateTime.now());
        return dictTypeMapper.insert(dictType) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_ADD)
                : new ReturnResult(Flag.SYSTEM_ERROR_SAVE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReturnResult deleteById(Long t) throws RuntimeException {
        if (t == null) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        DictType dictType = new DictType();
        dictType.setId(t);
        dictType.setCreateTime(LocalDateTime.now());
        return dictTypeMapper.updateById(dictType) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_ADD)
                : new ReturnResult(Flag.SYSTEM_ERROR_SAVE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ReturnResult update(DictType dictType) throws RuntimeException {
        if (Objects.isNull(dictType)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        dictType.setUpdateTime(LocalDateTime.now());
        return dictTypeMapper.updateById(dictType) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_UPDATE)
                : new ReturnResult(Flag.SYSTEM_ERROR_UPDATE);
    }

    @Override
    public ReturnResult pageList(DictType dictType) {
        if (Objects.isNull(dictType)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        List<DictType> dictTypes = dictTypeMapper.pageList(dictType.getP(), dictType);
        return new ReturnResult(Flag.SYSTEM_SUCCESS_QUERY,dictTypes,dictType.getP());
    }

    @Override
    public ReturnResult findById(Long uuid) {
        return null;
    }
}
