package com.basic.dictionary.data.service.impl;

import com.basic.commons.ConstantUtil;
import com.basic.commons.ReturnResult;
import com.basic.commons.enums.Flag;
import com.basic.dictionary.data.entity.Dictionary;
import com.basic.dictionary.data.mapper.DictionaryMapper;
import com.basic.dictionary.data.service.IDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author Mr.Zhang
 * @since 2021-07-04
 */
@Service
@Slf4j
public class DictionaryServiceImpl implements IDictionaryService {
    @Resource
    private DictionaryMapper dictionaryMapper;

    @Override
    public ReturnResult insert(Dictionary dictionary) throws RuntimeException {
        if (Objects.isNull(dictionary)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        dictionary.setCreateTime(LocalDateTime.now());
        return dictionaryMapper.insert(dictionary) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_ADD)
                : new ReturnResult(Flag.SYSTEM_ERROR_SAVE);
    }

    @Override
    public ReturnResult deleteById(Long t) throws RuntimeException {
        if (t == null) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        Dictionary dictionary = new Dictionary();
        dictionary.setId(t);
        dictionary.setIsDel(!ConstantUtil.Constant.isDelete);
        return dictionaryMapper.updateById(dictionary) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_UPDATE)
                : new ReturnResult(Flag.SYSTEM_ERROR_UPDATE);
    }

    @Override
    public ReturnResult update(Dictionary dictionary) throws RuntimeException {
        if (Objects.isNull(dictionary)) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        dictionary.setUpdateTime(LocalDateTime.now());
        return dictionaryMapper.updateById(dictionary) > 0
                ? new ReturnResult(Flag.SYSTEM_SUCCESS_UPDATE)
                : new ReturnResult(Flag.SYSTEM_ERROR_UPDATE);
    }

    @Override
    public ReturnResult findById(Long uuid) {
        if (uuid == null) {
            return new ReturnResult(Flag.SYSTEM_ERROR_EMPTY);
        }
        return new ReturnResult(Flag.SYSTEM_SUCCESS_QUERY,dictionaryMapper.findByDataId(uuid, ConstantUtil.Constant.isDelete));
    }
}
