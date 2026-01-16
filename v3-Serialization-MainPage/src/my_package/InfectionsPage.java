package my_package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InfectionsPage extends JFrame {
	
    private JTextArea displayArea;
    private JButton backButton;
    private JPanel panel = new JPanel(new BorderLayout(10, 10));
    
    private GUI parentGui;
    private User infectedUser;

    public InfectionsPage(User infectedUser, GUI parentGui) {
    	this.parentGui = parentGui;
    	this.infectedUser = infectedUser;
        this.setTitle("Πιθανή Μετάδοση Ιού");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        
        StringBuilder sb = new StringBuilder();
        sb.append("************************************************************\n");
        sb.append(infectedUser.getName()).append(" has been infected. The following users have to be tested\n");
        sb.append("************************************************************\n");
        
        ArrayList<User> riskUsers = infectedUser.scanForVirus();
        for (User u : riskUsers) {
            sb.append(u.getName()).append("\n");
        }
        displayArea.setText(sb.toString());

        
        backButton = new JButton("Back to Login Screen");
        
       

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton); 
        panel.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ButtonListener());

        
        this.setContentPane(panel);
        this.setLocationRelativeTo(null);
        
    }
    
    class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			InfectionsPage.this.dispose();
			parentGui.setVisible(true);
		}
    }
}