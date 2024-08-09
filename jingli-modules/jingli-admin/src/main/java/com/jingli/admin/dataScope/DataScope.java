package com.jingli.admin.dataScope;

import java.lang.annotation.*;

/**
 * 数据权限过滤注解
 *
 * @author yangbin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    /**
     * 权限别名
     */
    public String alias() default "";

    /**
     * 权限为代理商本人时 要执行的Sql
     */
    public String selfSql() default "a.userId = {}";

    /**
     * 权限为自定义时 要执行的Sql
     */
    public String customSql() default "a.userId in {}";

}
