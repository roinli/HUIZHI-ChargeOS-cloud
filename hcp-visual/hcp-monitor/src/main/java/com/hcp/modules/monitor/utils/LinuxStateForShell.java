package com.hcp.modules.monitor.utils;

import com.jcraft.jsch.*;
import com.hcp.common.core.utils.Arith;
import com.hcp.modules.monitor.domain.MonitorSysInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 远程调用Linux shell 命令
 *
 * @author wei.Li by 14-9-2.
 */
@Slf4j
public class LinuxStateForShell {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinuxStateForShell.class);

    public static final String CPU_MEM_SHELL = "top -b -n 1";
    public static final String FILES_SHELL = "df -h /";
    public static final String HOST_SHELL = "grep -c 'model name' /proc/cpuinfo";
    public static final String TOTAL_SHELL = "df -h / && grep -c 'model name' /proc/cpuinfo && top -b -n 1";
    public static final String[] COMMANDS = {TOTAL_SHELL};
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static Session session;
    //建立通讯超时时间
    private static final int TIMEOUT = 500;

    public static void main(String[] args) {
        Map<String, String> result = runDistanceShell("root", "Wonders804", "47.98.231.175", 22);
        MonitorSysInfo monitorSysInfo = disposeResultMessage(result);
        log.info(monitorSysInfo.toString());
    }


    /**
     * @author: Wang excellence
     * @date: 2021/7/1 15:09
     * @Description: 获取远程服务器的的cpu占用率，内存使用率，磁盘状况
     * 返回由数组返回  数组顺序为【0】cpu占用率，【1】内存使用率  【2】磁盘状况【3】CPU核数
     */
    public static MonitorSysInfo disposeResultMessage(Map<String, String> result) {
        String[] results = new String[4];
        if (result == null) {
            return null;
        }
        String commandResult = result.get(TOTAL_SHELL);
        if (null == commandResult) {
            return null;
        }
        String[] strings = commandResult.split(LINE_SEPARATOR);
        try {
            results[2] = (disposeFilesSystem(strings[1])).replace(" ", "");
        } catch (Exception e) {
            e.printStackTrace();
            results[2] = ("计算过程出错");
        }
        //记录cpu核心
        try {
            results[3] = strings[2];
        } catch (Exception e) {
            e.printStackTrace();
            results[3] = ("计算过程出错");
        }
        String cpuStr = "";
        try {
            cpuStr += strings[5].split(":")[1].split(",")[0].replace("us", "");
            cpuStr = cpuStr.trim();
        } catch (Exception e) {
            e.printStackTrace();
            cpuStr += "计算过程出错";
        }
        results[0] = cpuStr.trim();
        //处理内存 Mem:  66100704k total, 65323404k used,   777300k free,    89940k buffers
        try {
            strings[6] = strings[6].replace("  ", " ").replace("  ", " ");
            String[] mems = strings[6].split(" ");
            double parseInt = Integer.parseInt(mems[7]);
            double parseInt1 = Integer.parseInt(mems[3]);
            //System.err.println("内存使率：" + (Arith.div(parseInt,parseInt1,4))*100 + "%");
            results[1] = ((Arith.div(parseInt, parseInt1, 1)) * 100 + "");
        } catch (Exception e) {
            e.printStackTrace();
            results[1] = "计算过程出错";
        }
        //处理对象
        MonitorSysInfo sysInfo = new MonitorSysInfo();
        sysInfo.setCpuInfo(results[0]);
        sysInfo.setMemInfo(results[1]);
        sysInfo.setDiskInfo(results[2].split(",")[0]);
        sysInfo.setCpuCoreInfo(results[3]);
        sysInfo.setDiskPercent(results[2].split(",")[1]);
        return sysInfo;
    }

    /**
     * 连接到指定的HOST
     *
     * @return isConnect
     * @throws JSchException JSchException
     */
    public static boolean connect(String user, String passwd, String host, Integer port) {
        JSch jsch = new JSch();
        try {
            session = jsch.getSession(user, host, port);
            session.setPassword(passwd);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect(TIMEOUT);
        } catch (JSchException e) {
            return false;
        }
        return true;
    }

    /**
     * 远程连接Linux 服务器 执行相关的命令
     *
     * @param user   远程连接的用户名
     * @param passwd 远程连接的密码
     * @param host   远程连接的主机IP
     * @return 最终命令返回信息
     */
    public static Map<String, String> runDistanceShell(String user, String passwd, String host, Integer port) {
        String[] commands = COMMANDS;
        if (!connect(user, passwd, host, port)) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        StringBuilder stringBuffer;

        BufferedReader reader = null;
        Channel channel = null;
        try {
            for (String command : commands) {
                stringBuffer = new StringBuilder();
                channel = session.openChannel("exec");
                ((ChannelExec) channel).setCommand(command);

                channel.setInputStream(null);
                ((ChannelExec) channel).setErrStream(System.err);

                channel.connect();
                InputStream in = channel.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                String buf;
                while ((buf = reader.readLine()) != null) {

                    //舍弃PID 进程信息
                    if (buf.contains("PID")) {
                        break;
                    }
                    stringBuffer.append(buf.trim()).append(LINE_SEPARATOR);
                }
                //每个命令存储自己返回数据-用于后续对返回数据进行处理
                map.put(command, stringBuffer.toString());
            }
        } catch (IOException | JSchException e) {
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (channel != null) {
                channel.disconnect();
            }
            session.disconnect();
        }
        return map;
    }

    //处理系统磁盘状态

    /**
     * Filesystem            Size  Used Avail Use% Mounted on
     * /dev/sda3             442G  327G   93G  78% /
     * tmpfs                  32G     0   32G   0% /dev/shm
     * /dev/sda1             788M   60M  689M   8% /boot
     * /dev/md0              1.9T  483G  1.4T  26% /ezsonar
     *
     * @param commandResult 处理系统磁盘状态shell执行结果
     * @return 处理后的结果
     */
    private static String disposeFilesSystem(String commandResult) {
        String[] strings = commandResult.split(" ");
        int size = 0;
        int used = 0;
        int temp = 0;
        for (String s : strings) {
            if (temp == 0) {
                temp++;
                continue;
            }
            if (!s.trim().isEmpty()) {
                if (temp == 1) {
                    size += disposeUnitG(s);
                    temp++;
                } else {
                    used += disposeUnitG(s);
                    break;
                }
            }
        }
        return new StringBuilder().append(size).append("G,").append(((Arith.div(used, size, 1)) * 100 + "")).toString();
    }

    /**
     * 处理单位转换
     * K/KB/M/T 最终转换为G 处理
     *
     * @param s 带单位的数据字符串
     * @return 以G 为单位处理后的数值
     */
    private static int disposeUnitG(String s) {

        try {
            s = s.toUpperCase();
            String lastIndex = s.substring(s.length() - 1);
            String num = s.substring(0, s.length() - 1);
            int parseInt = Integer.parseInt(num);
            if ("G".equals(lastIndex)) {
                return parseInt;
            } else if ("T".equals(lastIndex)) {
                return parseInt * 1024;
            } else if ("M".equals(lastIndex)) {
                return parseInt / 1024;
            } else if ("K".equals(lastIndex) || "KB".equals(lastIndex)) {
                return parseInt / (1024 * 1024);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

}
