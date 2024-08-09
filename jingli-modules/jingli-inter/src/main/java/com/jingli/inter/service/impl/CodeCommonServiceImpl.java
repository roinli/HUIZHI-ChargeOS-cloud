package com.jingli.inter.service.impl;

import com.jingli.inter.entity.CodeCommon;
import com.jingli.inter.mapper.CodeCommonMapper;
import com.jingli.inter.service.CodeCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典码表;(code_common)表服务实现类
 */
@Service
public class CodeCommonServiceImpl implements CodeCommonService {
    @Autowired
    private CodeCommonMapper codeCommonMapper;
    @Override
    public List<CodeCommon> getCommonCode(String type) {
        return codeCommonMapper.queryCommonCode(type);
    }
}