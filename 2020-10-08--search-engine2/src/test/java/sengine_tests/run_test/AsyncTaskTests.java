package sengine_tests.run_test;

import org.junit.jupiter.api.Test;
import sengine.run.AsyncBuilder;
import sengine.run.Future;
import sengine.run.TaskManager;

import static org.junit.jupiter.api.Assertions.*;

public class AsyncTaskTests {
    @Test
    public void testSimpleAsyncTask() {
        TaskManager taskManager = new TaskManager();

        Future<Integer> fut = new AsyncBuilder<Integer>().in(taskManager).from((TaskManager ignored) -> 4 + 7).make();

        fut.waitUntilReady();
        assertTrue(fut.ready());

        Integer i = fut.getOrNull();
        assertNotNull(i);
        assertEquals(i, Integer.valueOf(11));

        taskManager.stopRunners();
    }

    @Test
    public void testLongTask() {
        TaskManager taskManager = new TaskManager();

        Future<Integer> fut = new AsyncBuilder<Integer>().in(taskManager).from((TaskManager ignored) -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored1) {
            }
            return 11 + 9;
        }).make();

        assertFalse(fut.ready());
        fut.waitUntilReady();
        assertTrue(fut.ready());

        Integer i = fut.getOrNull();
        assertNotNull(i);
        assertEquals(i, Integer.valueOf(20));

        taskManager.stopRunners();
    }
}
