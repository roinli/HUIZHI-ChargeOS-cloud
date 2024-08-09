package com.jingli.inter.mapper;

import com.jingli.inter.domain.PriceVO;
import com.jingli.inter.entity.CustomPrice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

 /**
 * 价格表;(c_custom_price)表数据库访问层
 * @author : Abdulla
 * @date : 2023-8-25
 */
@Mapper
public interface CustomPriceMapper{

     List<PriceVO> queryPriceByPlotId(@Param("map") HashMap<String, String> map);

     List<CustomPrice> queryWxOrderPrice(@Param("orderPrice") CustomPrice orderPrice);


 }