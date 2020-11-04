package sengine.run;

import java.util.concurrent.atomic.AtomicReference;

public class AsyncBuilder<T> {
    private AsyncTask<T> task;
    private TaskManager manager;

    public AsyncBuilder() {
    }

    public AsyncBuilder<T> from(AsyncTask<T> _task) {
        assert _task != null;
        task = _task;
        return this;
    }

    public AsyncBuilder<T> in(TaskManager _manager) {
        assert _manager != null;
        manager = _manager;
        return this;
    }

    public Future<T> make() {
        assert task != null;
        assert manager != null;

        AtomicReference<T> sharedRef = new AtomicReference<T>(null);

        manager.pushTaskToQueue((TaskManager localManager) -> {
            var out = task.run(localManager);
            sharedRef.set(out);
        });

        return new Future<T>(sharedRef);
    }
}
