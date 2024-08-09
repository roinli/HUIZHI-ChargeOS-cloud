package com.jingli.inter.service;

import com.jingli.inter.domain.PriceVO;

import java.util.HashMap;
import java.util.List;

/**
 * 价格表;(c_custom_price)表服务接口
 */
public interface CustomPriceService{
     List<PriceVO> queryPriceByPlotId(HashMap<String, String> map);
}