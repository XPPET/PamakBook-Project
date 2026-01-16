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
import java.io.Serializable;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class UserPage extends JFrame implements Serializable {
    
    private JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    private JTextField userNameField, userEmailField, friendNameField;
    private JButton backToLogin, postButton, addFriendButton, joinGroupButton;
    private JTextArea postInputField; 
    private JTextArea feedsArea;      
    private JList<String> suggestedFriendsList;
    private JList<Group> groupsList; // Λίστα για τα Groups
    private DefaultListModel<String> listModel;

    private GUI parentGui;
    private User currentUser;

    public UserPage(GUI aGui, User aUser) {
        this.parentGui = aGui;
        this.currentUser = aUser;
        this.setSize(700, 900);
        this.setTitle("Σελίδα Χρήστη");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // --- ΠΑΝΩ ΜΕΡΟΣ: Πληροφορίες & Προσθήκη Φίλου ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        userNameField = new JTextField(aUser.getName(), 10);
        userEmailField = new JTextField(aUser.getEmail(), 15);
        userNameField.setEditable(false);
        userEmailField.setEditable(false);
        
        friendNameField = new JTextField("Name to add friend", 12); // Πεδίο για φιλία
        addFriendButton = new JButton("Add Friend");
        backToLogin = new JButton("Logout");

        topPanel.add(userNameField);
        topPanel.add(userEmailField);
        topPanel.add(new JLabel(" | "));
        topPanel.add(friendNameField);
        topPanel.add(addFriendButton);
        topPanel.add(backToLogin);

        // --- ΜΕΣΑΙΟ ΜΕΡΟΣ: Αναρτήσεις & Groups ---
        JPanel middlePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        postInputField = new JTextArea(5, 25);
        postInputField.setBorder(BorderFactory.createTitledBorder("What's on your mind?"));
        postInputField.setLineWrap(true);
        postButton = new JButton("Post");

        // Λίστα Groups για εγγραφή
        DefaultListModel<Group> groupModel = new DefaultListModel<>();
        // Υποθέτουμε ότι η GUI κλάση έχει μέθοδο getAvailableGroups()
        for(Group g : aGui.getAvailableGroups()) { 
            groupModel.addElement(g);
        }
        groupsList = new JList<>(groupModel);
        groupsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane groupScroll = new JScrollPane(groupsList);
        groupScroll.setPreferredSize(new Dimension(150, 100));
        joinGroupButton = new JButton("Join Group");

        gbc.gridx = 0; gbc.gridy = 0;
        middlePanel.add(new JScrollPane(postInputField), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        middlePanel.add(postButton, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        middlePanel.add(groupScroll, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        middlePanel.add(joinGroupButton, gbc);

        // --- ΚΑΤΩ ΜΕΡΟΣ: Feeds & Suggestions ---
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        JLabel recentLabel = new JLabel("Recent Posts by Friends");
        recentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        feedsArea = new JTextArea(10, 40);
        feedsArea.setEditable(false);
        feedsArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        JLabel suggestedLabel = new JLabel("Suggested Friends");
        suggestedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        listModel = new DefaultListModel<>();
        suggestedFriendsList = new JList<>(listModel);
        updateSuggestions();

        bottomPanel.add(recentLabel);
        bottomPanel.add(new JScrollPane(feedsArea));
        bottomPanel.add(Box.createVerticalStrut(10));
        bottomPanel.add(suggestedLabel);
        bottomPanel.add(new JScrollPane(suggestedFriendsList));

        // Layout Integration
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(middlePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        this.setContentPane(mainPanel);

        // Listeners
        backToLogin.addActionListener(e -> {
            this.dispose();
            parentGui.setVisible(true);
        });

        postButton.addActionListener(new PostButtonListener());
        
        // Listener για προσθήκη φίλου
        addFriendButton.addActionListener(e -> {
            String targetName = friendNameField.getText();
            User targetUser = parentGui.findUser(targetName);
            
            if (targetUser == null) {
                JOptionPane.showMessageDialog(this, "User not found!");
            } else if (currentUser.isFriendWith(targetUser)) {
                JOptionPane.showMessageDialog(this, targetName + " is already your friend!");
            } else if (targetUser.equals(currentUser)) {
                JOptionPane.showMessageDialog(this, "You cannot friend yourself!");
            } else {
                currentUser.addFriend(targetUser); // Αμφίδρομη φιλία
                JOptionPane.showMessageDialog(this, "You are now friends with " + targetName);
                updateSuggestions();
                // Εμφάνιση γράφου και διαμέτρου
                new GraphWindow(parentGui.getAllUsers()); 
            }
        });

        
        joinGroupButton.addActionListener(e -> {
            Group selected = groupsList.getSelectedValue();
            if (selected != null) {
                if (selected.addMember(currentUser)) {
                    JOptionPane.showMessageDialog(this, "Successfully joined " + selected.getName());
                } else {
                    JOptionPane.showMessageDialog(this, "FAILED: You cannot join this group (already member or no common friend).");
                }
            }
        });

        refreshFeeds();
        this.setVisible(true);
    }

    private void updateSuggestions() {
        listModel.clear();
        ArrayList<User> suggestions = currentUser.suggestedFriends();
        for (User u : suggestions) {
            listModel.addElement(u.getName());
        }
    }

    private void refreshFeeds() {
        feedsArea.setText("");
        ArrayList<Post> allPosts = currentUser.returnAllPosts();
        for (Post p : allPosts) {
            feedsArea.append(p.getCreator().getName() + " (" + p.getTimestamp() + "): " + p.getDescription() + "\n");
        }
    }

    class PostButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String content = postInputField.getText();
            if (!content.isEmpty()) {
                Post newPost = new Post(new Date(), content, currentUser); 
                currentUser.addNewPost(newPost); 
                postInputField.setText("");
                refreshFeeds();
            }
        }
    }
}