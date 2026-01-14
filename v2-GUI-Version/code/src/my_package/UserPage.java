package my_package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserPage extends JFrame {
    
    private JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    
    private JTextField userNameField, userEmailField;
    private JButton backToLogin, postButton;
    private JTextArea postInputField; 
    private JTextArea feedsArea;      
    
    private GUI parentGui;
    private User currentUser;
    
    
    private JList<String> suggestedFriendsList;
    private DefaultListModel<String> listModel;

    public UserPage(GUI aGui, User aUser) {
    	this.parentGui = aGui;
    	this.currentUser = aUser;
        this.setSize(600, 800);
        this.setTitle("Σελίδα Χρήστη");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //ΠΑΝΩ ΜΕΡΟΣ
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        userNameField = new JTextField(aUser.getName(), 10);
        userEmailField = new JTextField(aUser.getEmail(), 15);
        userNameField.setEditable(false);
        userEmailField.setEditable(false);
        userNameField.setBackground(Color.WHITE);
        userEmailField.setBackground(Color.WHITE);

        
        backToLogin = new JButton("Back to Login Screen");
        
        topPanel.add(userNameField);
        topPanel.add(userEmailField);
        topPanel.add(backToLogin);

        //ΜΕΣΑΙΟ ΜΕΡΟΣ
        JPanel middlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        postInputField = new JTextArea(8, 25);
        postInputField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        postInputField.setLineWrap(true);
        
        postButton = new JButton("Post");

        gbc.gridx = 0; gbc.gridy = 0;
        middlePanel.add(new JScrollPane(postInputField), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        middlePanel.add(postButton, gbc);

        //ΚΑΤΩ ΜΕΡΟΣ
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        JLabel recentLabel = new JLabel("Recent Posts by Friends");
        recentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        feedsArea = new JTextArea(10, 35);
        feedsArea.setEditable(false);
        feedsArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JScrollPane feedsScroll = new JScrollPane(feedsArea);
        feedsScroll.setMaximumSize(new Dimension(500, 200));

        JLabel suggestedLabel = new JLabel("Suggested Friends");
        suggestedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        listModel = new DefaultListModel<>();
        suggestedFriendsList = new JList<>(listModel);

        ArrayList<User> suggestions = aUser.suggestedFriends();
        for (User u : suggestions) {
            listModel.addElement(u.getName());
        }
        
        JScrollPane listScroll = new JScrollPane(suggestedFriendsList);
        listScroll.setMaximumSize(new Dimension(150, 100));

        bottomPanel.add(Box.createVerticalStrut(20));
        bottomPanel.add(recentLabel);
        bottomPanel.add(feedsScroll);
        bottomPanel.add(Box.createVerticalStrut(20));
        bottomPanel.add(suggestedLabel);
        bottomPanel.add(listScroll);

        
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        this.setContentPane(mainPanel);

        ButtonListener buttonListener = new ButtonListener();
        backToLogin.addActionListener(buttonListener);
        
        postButton.addActionListener(new PostButtonListener());
        
        
        
        refreshFeeds();
        	
        	
    }
    
    
    
    class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			UserPage.this.dispose();
			parentGui.setVisible(true);		}
    	
    }
    
    
    class PostButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	// TODO Auto-generated method stub
            String content = postInputField.getText();
            if (!content.isEmpty()) {

            	Post newPost = new Post(new Date(), content, currentUser); 
                currentUser.addNewPost(newPost); 
                
                postInputField.setText("");
                refreshFeeds();
            }
        }
    }
    
    
    
    private void refreshFeeds() {
        feedsArea.setText("");
        ArrayList<Post> allPosts = currentUser.returnAllPosts();
        for (Post p : allPosts) {
            feedsArea.append(p.getCreator().getName() + ", " + p.getTimestamp() + ", " + p.getDescription() + "\n");
        }
    }
}