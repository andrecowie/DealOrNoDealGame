
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
    private Toolkit kit;
    
    public DealorNoDealGUI(){
        this.username = "";
        frame=new JFrame("Deal Or No Deal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        kit = Toolkit.getDefaultToolkit();
        frame.setSize(kit.getScreenSize().width, kit.getScreenSize().height);
        view = new JPanel(new BorderLayout());
        frame.add(view);
        welcome();
    }
    
    public JPanel updateCases(){
        JPanel updated_panel = new JPanel(new GridLayout(5, 5));
        updated_panel.setPreferredSize(new Dimension(kit.getScreenSize().width/2, kit.getScreenSize().height));
        JButton[] case_clicks = new JButton[26];
        for(int i=0; i < 26; i++ ){
            case_clicks[i]= new JButton(""+(i+1));
            case_clicks[i].setActionCommand(""+i);
            updated_panel.add(case_clicks[i]);
        }
        
        return updated_panel;
    }
    
    public JPanel updatePrize1(){
        JPanel prize1 = new JPanel();
        prize1.setPreferredSize(new Dimension(kit.getScreenSize().width/4, kit.getScreenSize().height));
        JLabel[] prizeLabels = new JLabel[13];
        for(int x = 0; x < 13; x++){
            for(int i = 0; i< 26; i++){
                if(game.getCases()[i].getDollarsInside() == game.prizes[x]){
                    System.out.print(game.getCases()[i]);
                    if(game.getCases()[i].isOpen()){
                        System.out.println(game.prizes[x]);
                        prizeLabels[x] = new JLabel(""+game.prizes[x]);
                        prizeLabels[x].setForeground(Color.pink);
                    }else{
                        prizeLabels[x] = new JLabel(""+game.prizes[x]);
                    }
                }
            }
            prize1.add(prizeLabels[x]);
        }
        return prize1;
    }
    
    public JPanel updatePrize2(){
        JPanel prize2 = new JPanel(new BorderLayout(2, 0));
        return prize2;
    }
    
    public void intiateGame(){
        game = new DealOrNoDeal(username, playersCase);
        
        //cases.setPreferredSize(new Dimension(kit.getScreenSize().width/2, kit.getScreenSize().height));
       
        view.add(updateCases(), BorderLayout.CENTER);
        view.add(updatePrize1(), BorderLayout.WEST);
        view.updateUI();
    }
    
    public void choseACase(){
        JLabel a = new JLabel("Pick a case.");
        JPanel caseview = new JPanel();
        caseview.add(a);
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
                 intiateGame();
            }
        
        }
        for(int x=0;x<26;x++){
            cases[x] = new JButton(""+(x+1));
            cases[x].setActionCommand(""+x);
            cases[x].addActionListener(new CaseSelection());
            caseview.add(cases[x]);
        }
        view.add(caseview, BorderLayout.CENTER);
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
                if(textFieldStore.getText().length()<1){
                    
                }else{
                    updateUser();
                    view.removeAll();
                    view.updateUI();
                    choseACase();
                }
            }
            public void updateUser(){
                username = textFieldStore.getText();
            }
            
        }
        b.addActionListener(new WelcomeAction(userinput));
        welcome.setText("What is your name?");        
        JPanel subPanel = new JPanel();
        subPanel.add(userinput);
        subPanel.add(b);
        view.add(subPanel, BorderLayout.SOUTH);
        
    }
    
    public static void main(String[] args){
        new DealorNoDealGUI();
    }
}
