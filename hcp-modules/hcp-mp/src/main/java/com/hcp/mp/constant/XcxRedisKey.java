package com.hcp.mp.constant;

public interface XcxRedisKey {

    String VER = "mp:v1:";

    /**
     * 充电站geotm
     */
    String STATION_GEO_TM = VER + "geo:stations:geotm";

    /**
     * 根据geo时间保存全部充电站Geo信息，存储ID
     */
    String STATION_GEO = VER + "geo:stations:";

    /**
     * 通过token 反查用户id
     */
    String USER_TOKEN = "user:token:";

    /**
     * 通过appkey查询应用信息
     */
    String SYS_APP_INFO = VER + "sys:app:info:";

}
