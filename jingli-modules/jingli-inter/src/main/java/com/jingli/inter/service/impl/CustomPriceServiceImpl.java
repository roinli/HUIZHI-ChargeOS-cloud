package com.jingli.inter.service.impl;

import com.jingli.inter.domain.PriceVO;
import com.jingli.inter.mapper.CustomPriceMapper;
import com.jingli.inter.service.CustomPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 价格表;(c_custom_price)表服务实现类
 */
@Service
public class CustomPriceServiceImpl implements CustomPriceService {
    @Autowired
    private CustomPriceMapper customPriceMapper;
    @Override
    public List<PriceVO> queryPriceByPlotId(HashMap<String, String> map) {
        return customPriceMapper.queryPriceByPlotId(map);
    }
}