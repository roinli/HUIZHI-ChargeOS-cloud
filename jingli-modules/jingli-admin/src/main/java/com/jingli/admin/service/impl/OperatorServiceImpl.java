package com.jingli.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jingli.admin.mapper.UserMapper;
import com.jingli.admin.model.OperatorSelect;
import com.jingli.admin.model.User;
import com.jingli.admin.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OperatorServiceImpl
 * @Description: 运营端-代理商操作
 * @Author 杨膑
 * @Version V1.0
 **/
@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @描述 获取代理商select
     * @参数 []
     * @返回值 java.util.List<com.jingli.modules.sys.operator.model.OperatorSelect>
     * @创建人 yangbin
     */
    @Override
    public List<OperatorSelect> getOperatorSelect() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_operator", 1);
        queryWrapper.ne("state", 2);
        List<User> users = userMapper.selectList(queryWrapper);
        List<OperatorSelect> operatorSelects = new ArrayList<>();
        for (User user : users) {
            OperatorSelect o = new OperatorSelect();
            o.setOperatorNm(user.getRealName());
            o.setOperatorId(user.getId());
            operatorSelects.add(o);
        }
        return operatorSelects;
    }
}
