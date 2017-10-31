package com.manji.messageserver.utils;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by pudding on 2016-12-16.
 */
public abstract class AbstractScheduleService {
    /**
     * 执行具体业务逻辑的TaskExecutor
     */
    private Map<Group, ThreadPoolTaskExecutor> taskExecutorMap = new HashMap<Group,ThreadPoolTaskExecutor>();
    /**
     * 新起线程处理定时任务
     * @param task
     */
    public void runInThread(Runnable task){
        new Thread(task).start();
    }
    /**
     * 在TaskExecutor中执行定时任务的具体逻辑，增加定时任务的处理效率
     * @param task
     */
    public void runInTaskExecutor(Runnable task){
        getTaskExecutor().execute(task);
    }
    /**
     * 在一个独立的TaskExecutor中执行定时任务的具体逻辑，增加定时任务的处理效率
     * @param task
     * @param group
     */
    public void runInTaskExecutor(Runnable task,Group group){
        getTaskExecutor(group).execute(task);
    }

    private synchronized ThreadPoolTaskExecutor getTaskExecutor(){
        return getTaskExecutor(Group.HISTORY);
    }

    private synchronized ThreadPoolTaskExecutor getTaskExecutor(Group group){
        ThreadPoolTaskExecutor taskExecutor=taskExecutorMap.get(group);
        if(taskExecutor==null){
            taskExecutor=initTaskExecutor();
            taskExecutorMap.put(group,taskExecutor);
        }
        return taskExecutor;
    }
    /**
     * 初始化具体的TaskExecutor实例，推荐使用MonitoredThreadPool，可对其执行情况进行监控
     * @return
     */
    protected ThreadPoolTaskExecutor initTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor=new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量
        taskExecutor.setCorePoolSize(30);
        //线程池维护线程的最大数量
        taskExecutor.setMaxPoolSize(300);
        //线程池维护线程所允许的空闲时间
        taskExecutor.setKeepAliveSeconds(300);
        //线程池等待终止秒
        taskExecutor.setAwaitTerminationSeconds(300);
        //等待线程任务完成关闭
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //线程名称前缀设置
        taskExecutor.setThreadNamePrefix("满集即时通讯 schedule-");
        //线程池所使用的缓冲队列
        taskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        //初始化线程池
        taskExecutor.initialize();
        return taskExecutor;
    }













    /**
     * 定时任务分类
     */
    protected enum Group{
        DEFAULT,
        HISTORY,
        DOWNLOAD,
        DOWNLOADFILE,

    }

}
