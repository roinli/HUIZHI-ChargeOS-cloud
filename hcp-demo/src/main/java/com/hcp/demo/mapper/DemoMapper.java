package com.hcp.demo.mapper;

import java.util.List;
import com.hcp.demo.domain.Demo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcp.common.mybatisplus.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Param;
/**
 * 测试Mapper接口
 *
 * @author vctgo
 * @date 2022-04-11
 */
public interface DemoMapper extends BaseMapperX<Demo>
{

    /**
     * 查询测试列表-分页
     *
     * @param demo 测试
     * @return 测试集合
     */
    IPage<Demo> selectDemoList(Page page, @Param("query") Demo demo);
    /**
     * 查询测试列表
     *
     * @param demo 测试
     * @return 测试集合
     */
    List<Demo> selectDemoList(@Param("query") Demo demo);

    /**
     * 批量删除测试
     *
     * @param demoIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteDemoByDemoIds(Long[] demoIds);
}
