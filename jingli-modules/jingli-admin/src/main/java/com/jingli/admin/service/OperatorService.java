package com.jingli.admin.service;


import com.jingli.admin.model.OperatorSelect;
import java.util.List;

public interface OperatorService {


    /**
     * 查询代理商数据
     *
     * @return 代理商信息
     */
    List<OperatorSelect> getOperatorSelect();
}
