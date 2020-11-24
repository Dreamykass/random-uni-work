package database;

import datatype.User;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserDatabase {
    private static final Lock usersFileLock = new ReentrantLock();

    private static List<User> getAllUsersNoLocking() throws FileNotFoundException {

        FileInputStream fis = new FileInputStream(Constants.DATABASE_ROOT + "users.xml");
        XMLDecoder xd = new XMLDecoder(fis);

        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) xd.readObject();
        xd.close();

        return users;
    }

    public static List<User> getAllUsers() {
        usersFileLock.lock();
        List<User> users = null;

        try {
            users = getAllUsersNoLocking();
        } catch (FileNotFoundException ignored) {
        }

        usersFileLock.unlock();
        return users;
    }

    public static Boolean insertUserIfPossible(User user) {
        usersFileLock.lock();

        try {
            List<User> users = getAllUsersNoLocking();
            users.add(user);

            FileOutputStream fos = new FileOutputStream(Constants.DATABASE_ROOT + "users.xml");
            XMLEncoder xe = new XMLEncoder(fos);

            xe.writeObject(users);
            xe.close();

            usersFileLock.unlock();
            return true;

        } catch (Exception ignored) {
            usersFileLock.unlock();
            return false;
        }
    }
}
