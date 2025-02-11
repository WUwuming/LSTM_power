package com.qiu.lstmpower.Util;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Log {

    // 获取当前日期，格式化为 yyyy-MM-dd
    private static String getData() {
        Date data = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(data);
    }

    // 获取按日期命名的日志文件路径
    private static String getLogFileName() {
        String dateStr = getData();  // 获取当前日期
        String FileName = "classpath:static/Log/log-" + dateStr + ".txt";
        try{
            File tryOpen = ResourceUtils.getFile(FileName);
        } catch (FileNotFoundException e) {
            System.out.println("日志文件缺失-创建文件");

        }
        return FileName;
    }

    // 写入日志到按日期命名的文件
    public void writeLog(String msg) throws IOException {
        String logFileName = getLogFileName();  // 获取日志文件路径
        File logFile = ResourceUtils.getFile(logFileName);
        // 创建 BufferedWriter，设置 append=true，确保内容被追加到文件末尾
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile, true));
        // 写入日期和日志内容
        bufferedWriter.newLine();
        bufferedWriter.write("-----"+getData()+"-----");
        bufferedWriter.newLine();
        bufferedWriter.write(msg);

        // 关闭资源
        bufferedWriter.close();
    }
}
