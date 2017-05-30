/*
 * Support class for QuizCard.java
 * refer to HeadFirst Java p456 to 457
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
public class QuizCardPlayer {
    
    //variable initilization
    private JTextArea display;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;
    private QuizCard currentCard;
    private int currentCardIndex;
    private JFrame frame;
    private JButton nextButton;
    private boolean isShowAnswer;
    
    public static void main(String[] args){
        QuizCardPlayer reader = new QuizCardPlayer();
        reader.go();
    }
    
    public void go(){
        
        //build Gui
        frame = new JFrame("Quiz Card Player");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);
        
        //display setup
        display = new JTextArea(10, 20);
        display.setFont(bigFont);
        display.setLineWrap(true);
        display.setEditable(false);
        
        //panel setup
        JScrollPane qScroller = new JScrollPane(display);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        nextButton = new JButton("Show Question");
        mainPanel.add(qScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());
        
        //setup display to be displayed
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load card set");
        loadMenuItem.addActionListener(new OpenMenuListener());
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(640, 500);
        frame.setVisible(true);
    }// close method(go())
    
    //inner class for nextcardlistener class
    public class NextCardListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            if (isShowAnswer){
                //show answer as player sees question
                display.setText(currentCard.getAnswer());
                nextButton.setText("Next card");
                isShowAnswer = false;
            }else{
                //show next question
                if (currentCardIndex < cardList.size()){
                    showNextCard();
                }else{
                    //no more cards
                    display.setText("That was last card");
                    nextButton.setEnabled(false);
                }//close if (currentcardindex)
            }//close if (isshowanswer)
        }//method close
    }//inner class close
        
    //inner class for oepnmenulistener
    public class OpenMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
        }//method close
    }//inner class close
    
    private void loadFile(File file){
        
        cardList = new ArrayList<QuizCard>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line  = reader.readLine()) != null){
                makeCard(line);
            }//while loop close
        }catch (Exception ex){
            System.out.println("couldnt read the card file");
            ex.printStackTrace();
        }//try/catch close
    }//method close
    
    private void makeCard(String lineToParse){
        String[] result = lineToParse.split("/");
        QuizCard card = new QuizCard(result[0], result[1]);
        cardList.add(card);
        System.out.println("made a card");
    }//method close
    
    private void showNextCard(){
        currentCard = cardList.get(currentCardIndex);
        currentCardIndex++;
        display.setText(currentCard.getQuestion());
        nextButton.setText("Show Answer");
        isShowAnswer = true;
    }//method close
}
