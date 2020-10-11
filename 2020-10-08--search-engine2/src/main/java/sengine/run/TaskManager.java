package sengine.run;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TaskManager {
    private static final Logger logger = Logger.getLogger(TaskManager.class.getName());

    private final BlockingQueue<Task> taskQueue = new LinkedBlockingDeque<Task>();
    private final List<TaskRunner> runnerList = new LinkedList<TaskRunner>();

    public TaskManager() {
        addRunners();
    }

    public TaskManager(int _n) {
        addRunners(_n);
    }

    public void addRunners() {
        var cpus = Runtime.getRuntime().availableProcessors();
        addRunners(cpus);
    }

    public void addRunners(int _n) {
        for (int i = 0; i < _n; i++) {
            runnerList.add(new TaskRunner(this));
        }
    }

    // blocking
    public void stopRunners() {
        for (var runner : runnerList) {
            runner.stop();
        }
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

    public int getQueueLength() {
        return taskQueue.size();
    }

    public int getNumberOfRunners() {
        return runnerList.size();
    }
}
