package sengine_tests.run_test;

import org.junit.jupiter.api.Test;
import sengine.run.Task;
import sengine.run.TaskManager;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTaskTests {

    @Test
    public void testEmptyTask() {
        TaskManager taskManager = new TaskManager();
        taskManager.pushTaskToQueue(new EmptyTask());
        taskManager.stopRunners();
    }

    @Test
    public void testTwoAdditionTasks() {
        TaskManager taskManager = new TaskManager();

        AdditionTask task1 = new AdditionTask(1, 2);
        AdditionTask task2 = new AdditionTask(6, 4);

        taskManager.pushTaskToQueue(task1);
        taskManager.pushTaskToQueue(task2);
        taskManager.stopRunners();

        assertEquals(3, task1.out.intValue());
        assertEquals(10, task2.out.intValue());
    }

    @Test
    public void testLotsOfTasks() {
        TaskManager taskManager = new TaskManager(0);
        assertEquals(0, taskManager.getNumberOfRunners());
        assertEquals(0, taskManager.getQueueLength());

        List<Task> tasks = new LinkedList<Task>();
        for (int i = 0; i < 100; i++) {
            tasks.add(new LongRunningTask());
            tasks.add(new EmptyTask());
            tasks.add(new AdditionTask(4, 254));
        }
        Collections.shuffle(tasks);

        for (var task : tasks) {
            taskManager.pushTaskToQueue(task);
        }
        assertEquals(300, taskManager.getQueueLength());

        taskManager.addRunners(32);
        taskManager.stopRunners();

        assertEquals(0, taskManager.getQueueLength());
    }

    @Test
    public void testTaskFromLambda() {
        TaskManager taskManager = new TaskManager();
        AtomicReference<Integer> i = new AtomicReference<>(0);
        taskManager.pushTaskToQueue((TaskManager tm) -> i.set(54));
        taskManager.stopRunners();
        assertEquals(54, i.get().intValue());
    }

}
