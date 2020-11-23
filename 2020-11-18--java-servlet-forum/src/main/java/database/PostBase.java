package database;

import datatype.Question;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PostBase {
    private static final Lock questionsFileLock = new ReentrantLock();

    private static List<Question> getAllQuestionsNoLocking() throws FileNotFoundException {

        FileInputStream fis = new FileInputStream(Constants.DATABASE_ROOT + "questions.xml");
        XMLDecoder xd = new XMLDecoder(fis);

        @SuppressWarnings("unchecked")
        List<Question> questions = (List<Question>) xd.readObject();
        xd.close();

        return questions;
    }

    public static List<Question> getAllQuestions() {
        questionsFileLock.lock();
        List<Question> questions = null;

        try {
            questions = getAllQuestionsNoLocking();
        } catch (FileNotFoundException ignored) {
        }

        questionsFileLock.unlock();
        return questions;
    }

    public static Boolean insertQuestion(Question question) {
        questionsFileLock.lock();

        try {
            List<Question> questions = getAllQuestionsNoLocking();
            questions.add(question);

            FileOutputStream fos = new FileOutputStream(Constants.DATABASE_ROOT + "questions.xml");
            XMLEncoder xe = new XMLEncoder(fos);

            xe.writeObject(questions);
            xe.close();

            questionsFileLock.unlock();
            return true;

        } catch (Exception ignored) {
            questionsFileLock.unlock();
            return false;
        }
    }
}
