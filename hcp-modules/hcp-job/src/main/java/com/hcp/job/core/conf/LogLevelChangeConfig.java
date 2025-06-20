package com.hcp.job.core.conf;

import ch.qos.logback.classic.Level;
import com.hcp.common.core.utils.SpringUtils;
import com.hcp.common.core.utils.StringUtils;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author weicj
 * @Description 用于处理sql对应的日志级别
 */
@Component
public class LogLevelChangeConfig implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(LogLevelChangeConfig.class);


    @Autowired
    private LogConfig logConfig;



    /**
     * 容器初始化完成后修改nosql配置文件配置的sql语句日志级别
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Start set SQL log level!");
        List<String> sqlIds = logConfig.getList();
        if (StringUtils.isEmpty(sqlIds)) {
            logger.info("FilterSqlService sqlIds no config!");
            return;
        }
        DefaultSqlSessionFactory sqlSessionFactory = SpringUtils.getBean("sqlSessionFactory");
        Configuration configuration = sqlSessionFactory.getConfiguration();
        for (String sqlIdConfig : sqlIds) {
            if (StringUtils.isEmpty(sqlIdConfig)) {
                logger.info("FilterSqlService sqlIds item config err!");
                continue;
            }
            String [] arr = sqlIdConfig.split("-");
            if (arr.length != 2) {
                logger.info("FilterSqlService sqlIds item config err!");
                continue;
            }
            String sqlId = arr[0];
            String logLevel = arr[1];
            if(logger.isInfoEnabled()){
                logger.info("Will set SQL:[{}] LOGLEVEL to [{}]", sqlId, logLevel);
            }
            changeStatementLogLevel(configuration, sqlId, logLevel);
        }
    }

    /**
     * 通过SQlMappedStatement全名查找并修改其对应的日志对象级别为关闭
     */
    public void changeStatementLogLevel(Configuration configuration, String name,String logLevel) {
        MappedStatement mappedStatement = null;
        try {
            mappedStatement = configuration.getMappedStatement(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(mappedStatement == null){
            logger.warn("SQL:--->[{}] is not exist!",name);
            return;
        }
        Slf4jImpl slf4jLog = (Slf4jImpl)mappedStatement.getStatementLog();
        try {
            Field log = slf4jLog.getClass().getDeclaredField("log");
            log.setAccessible(true);
            Object slfLocation =log.get(slf4jLog);
            Field logger = slfLocation.getClass().getDeclaredField("logger");
            logger.setAccessible(true);
            Object loggerObj = logger.get(slfLocation);
            Field effectiveLevelInt = loggerObj.getClass().getDeclaredField("effectiveLevelInt");
            effectiveLevelInt.setAccessible(true);
            int level = getLevelInt(logLevel);
            effectiveLevelInt.setInt(loggerObj , level);
            LogLevelChangeConfig.logger.info("SQL log level setting successful!");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private int getLevelInt(String logLevel) {
        int level = 10000;
        if("off".compareToIgnoreCase(logLevel)==0){
            level = Level.OFF_INT;
        }else if("error".compareToIgnoreCase(logLevel)==0){
            level=Level.ERROR_INT;
        }else if("warn".compareToIgnoreCase(logLevel)==0){
            level=Level.WARN_INT;
        }else if("trace".compareToIgnoreCase(logLevel)==0){
            level=Level.TRACE_INT;
        }
        return level;
    }



}

