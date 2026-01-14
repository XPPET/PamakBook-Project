package my_package;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;

public class GUI extends JFrame {

    private JPanel panel = new JPanel();
    
    private JTextField nameField = new JTextField("Please enter your name...");
    private JButton enterUserPage = new JButton("Enter User Page");
    private JButton showPotentialInfections = new JButton("Show Potential Infections");
    
    private ArrayList<User> allUsers;
    
    public GUI(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
        
        this.setSize(300, 200); 
        this.setTitle("Είσοδος Χρήστη");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        
        
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        panel.add(nameField);
        panel.add(enterUserPage);
        panel.add(showPotentialInfections);
        this.setContentPane(panel);

        ButtonListener buttonListener = new ButtonListener();
        enterUserPage.addActionListener(buttonListener);
        showPotentialInfections.addActionListener(buttonListener);
        
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	// TODO Auto-generated method stub
            String name = nameField.getText();
            User foundUser = findUserByName(name);

            if (e.getSource() == enterUserPage) {
                if (foundUser == null) {
                    new Message(name, GUI.this);
                    GUI.this.setVisible(false);
                } else {
                    new UserPage(GUI.this, foundUser);
                    GUI.this.setVisible(false);
                }
            } 
            else if (e.getSource() == showPotentialInfections) {
                if (foundUser == null) {
                    new Message(name, GUI.this);
                    GUI.this.setVisible(false);
                } else {
                    new InfectionsPage(foundUser, GUI.this);
                    GUI.this.setVisible(false);
                }
            }
        }
    }
    
    private User findUserByName(String name) {
        for (User u : allUsers) { 
            if (u.getName().equals(name)) {
                return u;
            }
        }
        return null;
    }
}