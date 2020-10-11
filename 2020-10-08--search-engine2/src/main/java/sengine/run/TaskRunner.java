package sengine.run;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class TaskRunner {
    private static final Logger logger = Logger.getLogger(TaskManager.class.getName());

    private final long threadId;
    private final TaskManager taskManager;
    private final AtomicBoolean running = new AtomicBoolean(true);
    private final AtomicBoolean shouldStop = new AtomicBoolean(false);

    public TaskRunner(TaskManager _taskManager) {
        assert _taskManager != null;
        taskManager = _taskManager;
        Thread thread = new Thread(this::threadMethod);
        thread.start();
        threadId = thread.getId();
    }

    //
    private void threadMethod() {
        while (!shouldStop.get()) {
            try {
                var task = taskManager.pollForTask(10);
                if (task != null) {
                    task.run(taskManager);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
        assert running.get();
        running.set(false);
    }

    // blocking
    public void stop() {
        assert !shouldStop.get();
        shouldStop.set(true);
        while (running.get()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
        }
    }

    //
    public long getThreadId() {
        return threadId;
    }


}
