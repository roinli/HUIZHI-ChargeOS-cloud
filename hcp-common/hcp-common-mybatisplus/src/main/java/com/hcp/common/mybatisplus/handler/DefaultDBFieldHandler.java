package com.hcp.common.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.hcp.common.core.utils.StringUtils;
import com.hcp.common.core.web.domain.BaseEntity;
import com.hcp.common.security.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;
import java.util.Objects;

/**
 * 通用参数填充实现类
 *
 * 如果没有显式的对通用参数进行赋值，这里会对通用参数进行填充、赋值
 *
 * @author hexiaowu
 */
public class DefaultDBFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();

            Date current = new Date();
            // 创建时间为空，则以当前时间为插入时间
            if (Objects.isNull(baseEntity.getCreateTime())) {
                baseEntity.setCreateTime(current);
            }
            // 更新时间为空，则以当前时间为更新时间
//            if (Objects.isNull(baseEntity.getUpdateTime())) {
//                baseEntity.setUpdateTime(current);
//            }
//
            String userName = SecurityUtils.getUsername();
            // 当前登录用户不为空，创建人为空，则当前登录用户为创建人
            if (Objects.nonNull(userName) && Objects.isNull(baseEntity.getCreateBy())) {
                baseEntity.setCreateBy(userName);
            }
            // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
//            if (Objects.nonNull(userName) && Objects.isNull(baseEntity.getUpdateBy())) {
//                baseEntity.setUpdateBy(userName);
//            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间为空，则以当前时间为更新时间
        Object modifyTime = getFieldValByName("updateTime", metaObject);
        if (Objects.isNull(modifyTime)) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }

        // 当前登录用户不为空，更新人为空，则当前登录用户为更新人
        Object modifier = getFieldValByName("updateBy", metaObject);
        String userName = SecurityUtils.getUsername();
        if (Objects.nonNull(userName) && StringUtils.isEmpty((String) modifier)) {
            setFieldValByName("updateBy", userName, metaObject);
        }
    }
}
