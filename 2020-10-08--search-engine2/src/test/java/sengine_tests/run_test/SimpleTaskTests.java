package sengine_tests.run_test;


import org.junit.jupiter.api.Test;
import sengine.run.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTaskTests {
    @Test
    public void test1() {
        TaskManager taskManager = new TaskManager();
        taskManager.pushTaskToQueue(new EmptyTask());
        taskManager.stopRunners();
    }

    @Test
    public void test2() {
        TaskManager taskManager = new TaskManager();

        AdditionTask task1 = new AdditionTask(1, 2);
        AdditionTask task2 = new AdditionTask(6, 4);

        taskManager.pushTaskToQueue(task1);
        taskManager.pushTaskToQueue(task2);
        taskManager.stopRunners();

        assertEquals(3, task1.out.intValue());
        assertEquals(10, task2.out.intValue());
    }
}
