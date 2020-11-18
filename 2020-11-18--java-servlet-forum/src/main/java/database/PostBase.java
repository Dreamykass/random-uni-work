package database;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PostBase {
    private static final Lock postsFileLock = new ReentrantLock();
}
