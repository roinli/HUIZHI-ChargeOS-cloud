package com.jingli.inter.mapper;

import com.jingli.inter.entity.CodeCommon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

 /**
 * 字典码表;(code_common)表数据库访问层
 * @author : Abdulla
 * @date : 2023-8-25
 */
@Mapper
public interface CodeCommonMapper{

     List<CodeCommon> queryCommonCode(String type);
 }