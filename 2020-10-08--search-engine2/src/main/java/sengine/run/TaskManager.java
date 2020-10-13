package sengine.run;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sengine.crawl.CrawlingTask;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class TaskManager {
    private static final Logger logger = LogManager.getLogger(CrawlingTask.class);

    private final BlockingQueue<Task> taskQueue = new LinkedBlockingDeque<Task>();
    private final List<TaskRunner> runnerList = new LinkedList<TaskRunner>();
    private JList<String> frameList;
    private JFrame frame;

    public TaskManager() {
        addRunners();
    }

    public TaskManager(int _n) {
        addRunners(_n);
    }

    public void addRunners() {
        var cpus = Runtime.getRuntime().availableProcessors();
        addRunners(cpus);
    }

    public void addRunners(int _n) {
        for (int i = 0; i < _n; i++) {
            runnerList.add(new TaskRunner(this));
        }
    }

    // blocking
    public void stopRunners() {
        for (var runner : runnerList) {
            runner.stop();
        }
    }

    // block until _timeout milliseconds
    // Task or null
    public Task pollForTask(long _timeoutMilliseconds) throws InterruptedException {
        return taskQueue.poll(_timeoutMilliseconds, TimeUnit.MILLISECONDS);
    }

    //
    public void pushTaskToQueue(Task _task) {
        assert _task != null;
        taskQueue.add(_task);
    }

    public int getQueueLength() {
        return taskQueue.size();
    }

    public int getNumberOfRunners() {
        return runnerList.size();
    }

    public void openRunnerStatusViewer() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(1200, 600);
        frame.setLayout(null);
    }

    public void updateRunnerStatusViewer() {
        if (frame == null)
            openRunnerStatusViewer();

        if (frameList != null)
            frame.remove(frameList);

        DefaultListModel<String> l1 = new DefaultListModel<>();
        for (var runner : runnerList)
            l1.addElement("runner of thread id: " + runner.getThreadId() + "; ----------- task name: " + runner.getCurrentTaskName());

        frameList = new JList<>(l1);
        frameList.setBounds(0, 0, 1200, 600);
        frameList.setSize(1200, 600);
        frame.add(frameList);

        SwingUtilities.updateComponentTreeUI(frame);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    public void closeRunnerStatusViewer() {
        if (frame != null) {
            frame.dispose();
        }
    }
}
