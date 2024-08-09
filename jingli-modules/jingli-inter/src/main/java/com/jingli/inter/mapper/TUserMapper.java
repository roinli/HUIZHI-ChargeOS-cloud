package com.jingli.inter.mapper;

import com.jingli.inter.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

 /**
 * 用户表;(t_user)表数据库访问层
 * @author : Abdulla
 * @date : 2023-8-25
 */
@Mapper
public interface TUserMapper{


    int insert(TUser tUser);


    int update(TUser tUser);

     TUser findByOpenId(@Param("searchOpenId") String searchOpenId);





 }