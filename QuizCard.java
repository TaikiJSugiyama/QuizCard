/*
 * This will be the main class of the QuizCard,
 * which will execute the program with card builder
 * and card player classes
 *  _________________
 *  |  Quiz Card    |
 *  |---------------|
 *  | QuizCard(q,a) |
 *  |---------------|
 *  |  question     |
 *  |  answer       |
 *  |---------------|
 *  | getQuestion() |
 *  | getAnswer()   |
 *   ---------------
 *
 * more to be added - category, id, etc...
 *
 */
package quizcard;
import java.io.*;

/**
 *
 * @author Taiki
 */
public class QuizCard implements Serializable {
    
    //instance variable initialization
    private String question;
    private String answer;
    
    //quiz card method
    /**
     * Constructor declaration
     * @param q
     * @param a the command line arguments
     * take q as question, a as answer
     */
    public QuizCard(String q, String a){
        question = q;
        answer = a;
    }//method close
    
    //getquestion method
    /**
     * return string question
     */
    public String getQuestion(){
        return question;
    }//method close
    
    //getanswer method
    /**
     * return answer
     */
    public String getAnswer(){
        return answer;
   }//method close
    
}
