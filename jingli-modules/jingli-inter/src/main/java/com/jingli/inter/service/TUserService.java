package com.jingli.inter.service;
import com.jingli.inter.entity.TUser;
 /**
 * 用户表;(t_user)表服务接口
 */
public interface TUserService{

    int insert(TUser tUser);

    int update(TUser tUser);

     TUser findByOpenId(String searchOpenId);

     String appletBindMobile(String searchOpenId, String code);
 }