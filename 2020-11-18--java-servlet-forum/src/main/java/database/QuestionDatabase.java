package database;

import datatype.Question;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QuestionDatabase {
    private static final Lock questionsFileLock = new ReentrantLock();

    private static List<Question> getAllQuestionsNoLocking() throws FileNotFoundException {

        FileInputStream fis = new FileInputStream(Constants.DATABASE_ROOT + "questions.xml");
        XMLDecoder xd = new XMLDecoder(fis);

        List<Question> questions = null;
        try {
            questions = (List<Question>) xd.readObject();
        } catch (Exception ignored) {

        }
        xd.close();

        return questions;
    }

    public static List<Question> getAllQuestions() {
        questionsFileLock.lock();
        List<Question> questions = null;

        try {
            questions = getAllQuestionsNoLocking();
        } catch (Exception e) {
//            e.printStackTrace();
        }

        questionsFileLock.unlock();
        return questions;
    }

    public static void insertQuestion(Question question) {
        questionsFileLock.lock();

        try {
            List<Question> questions = null;
            questions = getAllQuestionsNoLocking();

            if (questions == null)
                questions = new ArrayList<>();

            questions.add(question);

            FileOutputStream fos = new FileOutputStream(Constants.DATABASE_ROOT + "questions.xml");
            XMLEncoder xe = new XMLEncoder(fos);

            xe.writeObject(questions);
            xe.close();

            questionsFileLock.unlock();

        } catch (Exception e) {
            e.printStackTrace();
            questionsFileLock.unlock();
        }
    }

    public static void updateQuestion(Question question) {
        questionsFileLock.lock();

        try {
            List<Question> questions = null;
            questions = getAllQuestionsNoLocking();

            if (questions == null)
                questions = new ArrayList<>();

            for (Question qq : questions) {
                if (qq.hashCode() == question.hashCode()) {
                    questions.remove(qq);
                    questions.add(question);
                    break;
                }
            }

            FileOutputStream fos = new FileOutputStream(Constants.DATABASE_ROOT + "questions.xml");
            XMLEncoder xe = new XMLEncoder(fos);

            xe.writeObject(questions);
            xe.close();

            questionsFileLock.unlock();

        } catch (Exception e) {
            e.printStackTrace();
            questionsFileLock.unlock();
        }
    }
}
