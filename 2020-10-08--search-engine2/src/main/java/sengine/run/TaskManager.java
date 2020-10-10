package sengine.run;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TaskManager {
    private static final Logger logger = Logger.getLogger(TaskManager.class.getName());

    private BlockingQueue<Task> taskQueue = new LinkedBlockingDeque<Task>();
    private List<TaskRunner> runnerList = new LinkedList<TaskRunner>();

    public TaskManager() {
        int cpus = Runtime.getRuntime().availableProcessors();
        logger.info("available cpus: " + Integer.toString(cpus));

        for (int i = 0; i < cpus; i++) {
            runnerList.add(new TaskRunner(this));
        }
    }

    public void stopRunners() {
        logger.info("stopping runners: " + Integer.toString(runnerList.size()));
        for (var runner : runnerList) {
            runner.stop();
            logger.info("stopped a runner...");
        }
        logger.info("stopped all runners");
    }

    // block until _timeout milliseconds
    // Task or null
    public Task pollForTask(long _timeoutMilliseconds) throws InterruptedException {
        return taskQueue.poll(_timeoutMilliseconds, TimeUnit.MILLISECONDS);
    }

    //
    public void pushTaskToQueue(Task _task) {
        assert _task != null;
        taskQueue.add(_task);
    }

    public int getQueueSize() {
        return taskQueue.size();
    }

}
