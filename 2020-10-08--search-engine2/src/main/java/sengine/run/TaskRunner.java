package sengine.run;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class TaskRunner {
    private static final Logger logger = LogManager.getLogger(TaskRunner.class);

    private final long threadId;
    private final TaskManager taskManager;
    private final AtomicBoolean running = new AtomicBoolean(true);
    private final AtomicBoolean shouldStop = new AtomicBoolean(false);
    private final AtomicReference<Task> currentTask = new AtomicReference<Task>();

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
                currentTask.set(taskManager.pollForTask(10));
                if (currentTask.get() != null) {
                    try {
                        currentTask.get().run(taskManager);
                    } catch (Exception e) {

                        String taskName;
                        if (currentTask.get() instanceof NamedTask) {
                            taskName = ((NamedTask) (currentTask.get())).getName();
                        } else {
                            taskName = "unnamed task";
                        }

                        logger.fatal("caught Exception; threadId: {}; taskName: {}", threadId, taskName);
                        logger.fatal("stack trace:");
                        e.printStackTrace();
                        logger.fatal("done ^");
                    }
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

    public String getCurrentTaskName() {
        if (currentTask.get() == null) {
            return "null?";
        } else if (currentTask.get() instanceof NamedTask) {
            return ((NamedTask) (currentTask.get())).getName();
        } else {
            return "unnamed task";
        }
    }

}
