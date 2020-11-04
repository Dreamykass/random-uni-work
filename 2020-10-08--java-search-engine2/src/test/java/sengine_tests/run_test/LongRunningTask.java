package sengine_tests.run_test;

import sengine.run.Task;
import sengine.run.TaskManager;

import java.util.concurrent.ThreadLocalRandom;

public class LongRunningTask implements Task {
    @Override
    public void run(TaskManager _taskManager) {
        try {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 100);
            Thread.sleep(randomNum);
        } catch (InterruptedException ignored) {
        }
    }
}
