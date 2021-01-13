package database;

import datatype.Answer;
import datatype.Question;
import datatype.Rating;

import java.sql.*;
import java.util.Date;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QuestionDatabase {
    private static final Lock questionsFileLock = new ReentrantLock();
    public static String errors = "";

    private static List<Question> getAllQuestionsNoLocking() {
        Map<Long, Question> questions = new HashMap<>();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaforum", "root", "");

            {
                Statement statement = connection.createStatement();
                ResultSet resultSetQuestions = statement.executeQuery("select * from questions");
                while (resultSetQuestions.next()) {
                    Question question = new Question();
                    question.id = resultSetQuestions.getLong("id");
                    question.questionBody = resultSetQuestions.getString("questionBody");
                    question.authorLogin = resultSetQuestions.getString("authorLogin");
                    question.dateOfCreation = new Date(resultSetQuestions.getLong("dateOfCreation"));
                    questions.put(resultSetQuestions.getLong("id"), question);
                }
            }

            {
                Statement statement = connection.createStatement();
                ResultSet resultSetAnswers = statement.executeQuery("select * from answers");
                while (resultSetAnswers.next()) {
                    Answer answer = new Answer();
                    answer.answerBody = resultSetAnswers.getString("answerBody");
                    answer.authorLogin = resultSetAnswers.getString("authorLogin");
                    answer.dateOfCreation = new Date(resultSetAnswers.getLong("dateOfCreation"));
                    questions.get(resultSetAnswers.getLong("questionId")).answers.add(answer);
                }
            }

            {
                Statement statement = connection.createStatement();
                ResultSet resultSetRatings = statement.executeQuery("select * from ratings");
                while (resultSetRatings.next()) {
                    Rating rating = new Rating();
                    rating.authorLogin = resultSetRatings.getString("authorLogin");
                    rating.value = resultSetRatings.getInt("value");
                    questions.get(resultSetRatings.getLong("questionId")).ratings.add(rating);
                }
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            errors = e.toString();
        }

        return new ArrayList<>(questions.values());
    }

    public static List<Question> getAllQuestions() {
        questionsFileLock.lock();
        List<Question> questions = null;
        questions = getAllQuestionsNoLocking();
        questionsFileLock.unlock();
        Collections.sort(questions);
        return questions;
    }

    public static void insertQuestion(Question question) {
        questionsFileLock.lock();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaforum", "root", "");
            Statement statement = connection.createStatement();

            String query = "INSERT INTO questions (questionBody, authorLogin, dateOfCreation) " +
                    "VALUES ('" + question.questionBody + "', '" + question.authorLogin + "', '" + question.dateOfCreation.getTime() + "');";
            statement.executeUpdate(query);

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            errors = e.toString();
        }

        questionsFileLock.unlock();
    }

    public static void updateQuestion(Question question) {
        questionsFileLock.lock();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaforum", "root", "");

            {
                Statement statement = connection.createStatement();
                String query = "DELETE FROM questions WHERE id='" + question.id + "';";
                statement.executeUpdate(query);
            }
            {
                Statement statement = connection.createStatement();
                String query = "DELETE FROM answers WHERE questionId='" + question.id + "';";
                statement.executeUpdate(query);
            }
            {
                Statement statement = connection.createStatement();
                String query = "DELETE FROM ratings WHERE questionId='" + question.id + "';";
                statement.executeUpdate(query);
            }


            {
                Statement statement = connection.createStatement();
                String query = "INSERT INTO questions (id, questionBody, authorLogin, dateOfCreation) " +
                        "VALUES ('" + question.id + "', '" + question.questionBody + "', '" + question.authorLogin + "', '" + question.dateOfCreation.getTime() + "');";
                statement.executeUpdate(query);
            }
            {
                for (Answer answer : question.answers) {
                    Statement statement = connection.createStatement();
                    String query = "INSERT INTO answers (questionId, answerBody, authorLogin, dateOfCreation) " +
                            "VALUES ('" + question.id + "', '" + answer.answerBody + "', '" + answer.authorLogin + "', '" + answer.dateOfCreation.getTime() + "');";
                    statement.executeUpdate(query);
                }
            }
            {
                for (Rating rating : question.ratings) {
                    Statement statement = connection.createStatement();
                    String query = "INSERT INTO ratings (questionId, authorLogin, value) " +
                            "VALUES ('" + question.id + "', '" + rating.authorLogin + "', '" + rating.value + "');";
                    statement.executeUpdate(query);
                }
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            errors = e.toString();
        }

        questionsFileLock.unlock();
    }

    public static Question getQuestionFromHashcode(int hash) {
        List<Question> qq = getAllQuestions();

        Question q = null;
        for (Question w : qq)
            if (w.hashCode() == hash)
                q = w;

        return q;
    }
}
