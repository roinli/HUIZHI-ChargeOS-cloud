package com.jingli.inter.service;

import com.jingli.inter.entity.CodeCommon;

import java.util.List;

/**
 * 字典码表;(code_common)表服务接口
 */
public interface CodeCommonService{
     List<CodeCommon> getCommonCode(String type);
 }