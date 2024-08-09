
package com.jingli.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jingli.admin.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


/**
 * @ClassName UserMapper
 * @Description:
 * @Author yangbin
 * @Version V1.0
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询运营者列表
     *
     * @param params 参数信息
     * @param page   分页信息
     * @return 运营者信息
     */
    List<UserResult> getUserList(@Param("p") Map<String, Object> params, Page<UserResult> page);

    /**
     * 功能描述：根据电话号码查询登录
     *
     * @param telephone 电话号码
     * @Return:com.jingli.modules.sys.user.model.User
     * @Author: Guo Liangbo
     */
    User selectByTelephone(@Param("telephone") String telephone);
}

