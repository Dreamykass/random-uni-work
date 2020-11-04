package sengine_tests.run_test;

import sengine.run.Task;
import sengine.run.TaskManager;

import java.util.logging.Logger;

public class EmptyTask implements Task {
    private static final Logger logger = Logger.getLogger(TaskManager.class.getName());

    @Override
    public void run(TaskManager _taskManager) {
//        logger.info("empty task is being ran");
    }
}
