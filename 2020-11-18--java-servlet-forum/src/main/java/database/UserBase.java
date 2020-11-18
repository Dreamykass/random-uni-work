package database;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserBase {
    private static final Lock usersFileLock = new ReentrantLock();
}
