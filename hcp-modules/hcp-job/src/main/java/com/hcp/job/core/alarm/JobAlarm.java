package com.hcp.job.core.alarm;

import com.hcp.job.domain.XxlJobInfo;
import com.hcp.job.domain.XxlJobLog;

public interface JobAlarm {

    /**
     * job alarm
     *
     * @param info
     * @param jobLog
     * @return
     */
    boolean doAlarm(XxlJobInfo info, XxlJobLog jobLog);

}
