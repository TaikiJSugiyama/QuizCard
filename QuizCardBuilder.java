/*
 * Support class for QuizCard.java
 * refer to HeadFirst Java p450 to 451
 */
package quizcard;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
/**
 *
 * @author Taiki
 */
public class QuizCardBuilder {
    
    //variable initilization
    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;
    private JFrame frame;
    
    
    public static void main(String[] args){
        QuizCardBuilder builder = new QuizCardBuilder();
        builder.go();
    }
    
    public void go(){
        
        //build gui setup
        frame = new JFrame("Quiz Card Builder");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);
        
        //question text area & lable setup
        question = new JTextArea(6, 20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(bigFont);
        JScrollPane qScroller = new JScrollPane(question);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JLabel qLabel = new JLabel("Question");
        
        //answer text area & label setup
        answer = new JTextArea(6, 20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(bigFont);
        JScrollPane aScroller = new JScrollPane(answer);
        aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JLabel aLabel = new JLabel("Answer");
        
        //next button setup
        JButton nextButton = new JButton("Next Card");
        
        //ready for the card list creation
        cardList = new ArrayList<QuizCard>();
        
        //add contents to the main panel
        mainPanel.add(qLabel);
        mainPanel.add(qScroller);
        mainPanel.add(aLabel);
        mainPanel.add(aScroller);
        mainPanel.add(nextButton);
        
        //Menu bar and its setup
        nextButton.addActionListener(new NextCardListener());
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        
        //frame borderlayout manage
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }//method close(go())
    
    //inner class for nextcardlistener class
    public class NextCardListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);
            clearCard();
        }
    }//inner class close
    
    //inner class for savemenulistener class
    public class SaveMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);
            
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }//inner class close
    
    public class NewMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);
            
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }//inner class close
    
    public void clearCard(){
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }//inner class close
    
    public void saveFile(File file){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            
            for(QuizCard card:cardList){
                writer.write(card.getQuestion() + "/");
                writer.write(card.getAnswer() + "\n");
            }
            writer.close();
        }catch (IOException ex){
            System.out.println("Couldnt write the the cardlist out");
            ex.printStackTrace();
        }//try close
    }
}

