
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abc
 */
public class DealorNoDealGUI {
    public JFrame frame;
    public DealOrNoDeal game;
    public JPanel view;
    public String username;
    public int playersCase;
    
    public DealorNoDealGUI(){
        this.username = "";
        frame=new JFrame("Deal Or No Deal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Toolkit kit = Toolkit.getDefaultToolkit();
        frame.setSize(kit.getScreenSize().width, kit.getScreenSize().height);
        view = new JPanel();
        frame.add(view);
        welcome();
    }
    
    public void intiateGame(){
        game = new DealOrNoDeal(username, playersCase);
        
        
    }
    
    public void choseACase(){
        JLabel a = new JLabel("Pick a case.");
        System.out.println("In chose a case");
        JPanel caseview = new JPanel();
        GridLayout buttons = new GridLayout(3,10);
        caseview.setLayout(buttons);
        JButton[] cases = new JButton[26];
        class CaseSelection implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent ae) {
                 String output = ae.getActionCommand();
                 playersCase = Integer.parseInt(output);
                 view.removeAll();
                 view.updateUI();
                 initiateGame();
            }
        
        }
        for(int x=0;x<26;x++){
            cases[x] = new JButton(""+(x+1));
            cases[x].setActionCommand(""+x);
            cases[x].addActionListener(new CaseSelection());
            caseview.add(cases[x]);
        }
        view.add(a);
        view.add(caseview);
        view.updateUI();
    }
    
    public void welcome(){
        JLabel welcome=new JLabel("Welcome to deal or no deal!", JLabel.CENTER);
        view.add(welcome);
        try {
            sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(DealorNoDealGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        JTextField userinput=new JTextField(20);
        
        JButton b = new JButton("Enter");
        class WelcomeAction implements ActionListener{
            JTextField textFieldStore;
            
            public WelcomeAction(JTextField textFieldInput){
                textFieldStore = textFieldInput;
            }
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                updateUser();
                view.removeAll();
                view.updateUI();
                choseACase();
            }
            public void updateUser(){
                username = textFieldStore.getText();
            }
            
        }
        b.addActionListener(new WelcomeAction(userinput));
        welcome.setText("What is your name?");        
        view.add(userinput);
        view.add(b);
        
    }
    
    public static void main(String[] args){
        new DealorNoDealGUI();
    }
}
