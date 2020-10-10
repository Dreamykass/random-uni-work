package sengine_tests.run_test;

import sengine.run.Task;
import sengine.run.TaskManager;

public class AdditionTask implements Task {
    public Integer out;
    Integer a;
    Integer b;

    AdditionTask(Integer _a, Integer _b) {
        a = _a;
        b = _b;
    }

    @Override
    public void run(TaskManager _taskManager) {
        out = a + b;
    }
}
