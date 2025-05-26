package com.hcp.job.core.conf;

import com.hcp.job.core.alarm.JobAlarmer;
import com.hcp.job.core.scheduler.XxlJobScheduler;
import com.hcp.job.mapper.*;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Component
public class XxlJobAdminConfig implements InitializingBean, DisposableBean {

    private static XxlJobAdminConfig adminConfig = null;
    public static XxlJobAdminConfig getAdminConfig() {
        return adminConfig;
    }

    // ---------------------- XxlJobScheduler ----------------------

    private XxlJobScheduler xxlJobScheduler;

    @Override
    public void afterPropertiesSet() throws Exception {
        adminConfig = this;

        xxlJobScheduler = new XxlJobScheduler();
        xxlJobScheduler.init();
    }

    @Override
    public void destroy() throws Exception {
        xxlJobScheduler.destroy();
    }


    // ---------------------- XxlJobScheduler ----------------------

    // conf
    @Value("${xxl.job.i18n}")
    private String i18n;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${spring.mail.from}")
    private String emailFrom;

    @Value("${xxl.job.triggerpool.fast.max}")
    private int triggerPoolFastMax;

    @Value("${xxl.job.triggerpool.slow.max}")
    private int triggerPoolSlowMax;

    @Value("${xxl.job.logretentiondays}")
    private int logretentiondays;


    @Resource
    private XxlJobInfoMapper xxlJobInfoDao;

    @Resource
    private XxlJobGroupMapper xxlJobGroupDao;

    @Resource
    private XxlJobRegistryMapper xxlJobRegistryDao;

    @Resource
    private XxlJobLogMapper xxlJobLogDao;

    @Resource
    private JobAlarmer jobAlarmer;

    @Resource
    private XxlJobLogReportMapper xxlJobLogReportDao;

    @Resource
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public XxlJobLogReportMapper getXxlJobLogReportDao() {
        return xxlJobLogReportDao;
    }

    public void setXxlJobLogReportDao(XxlJobLogReportMapper xxlJobLogReportDao) {
        this.xxlJobLogReportDao = xxlJobLogReportDao;
    }

    public JobAlarmer getJobAlarmer() {
        return jobAlarmer;
    }

    public void setJobAlarmer(JobAlarmer jobAlarmer) {
        this.jobAlarmer = jobAlarmer;
    }

    public XxlJobLogMapper getXxlJobLogDao() {
        return xxlJobLogDao;
    }

    public void setXxlJobLogDao(XxlJobLogMapper xxlJobLogDao) {
        this.xxlJobLogDao = xxlJobLogDao;
    }

    public XxlJobRegistryMapper getXxlJobRegistryDao() {
        return xxlJobRegistryDao;
    }

    public void setXxlJobRegistryDao(XxlJobRegistryMapper xxlJobRegistryDao) {
        this.xxlJobRegistryDao = xxlJobRegistryDao;
    }

    public XxlJobGroupMapper getXxlJobGroupDao() {
        return xxlJobGroupDao;
    }

    public void setXxlJobGroupDao(XxlJobGroupMapper xxlJobGroupDao) {
        this.xxlJobGroupDao = xxlJobGroupDao;
    }

    public XxlJobInfoMapper getXxlJobInfoDao() {
        return xxlJobInfoDao;
    }

    public void setXxlJobInfoDao(XxlJobInfoMapper xxlJobInfoDao) {
        this.xxlJobInfoDao = xxlJobInfoDao;
    }

    public String getI18n() {
        return i18n;
    }

    public void setI18n(String i18n) {
        this.i18n = i18n;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public void setTriggerPoolFastMax(int triggerPoolFastMax) {
        this.triggerPoolFastMax = triggerPoolFastMax;
    }

    public int getTriggerPoolSlowMax() {
        return triggerPoolSlowMax;
    }

    public void setTriggerPoolSlowMax(int triggerPoolSlowMax) {
        this.triggerPoolSlowMax = triggerPoolSlowMax;
    }

    public int getLogretentiondays() {
        return logretentiondays;
    }

    public void setLogretentiondays(int logretentiondays) {
        this.logretentiondays = logretentiondays;
    }

    public int getTriggerPoolFastMax() {
        if (triggerPoolFastMax < 200) {
            return 200;
        }
        return triggerPoolFastMax;
    }


}
