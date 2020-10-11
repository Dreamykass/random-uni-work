package sengine.run;

public interface AsyncTask<T> {
    T run(TaskManager _taskManager);
}
