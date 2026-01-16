package my_package;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUI extends JFrame  implements Serializable{

    private JTextField nameField = new JTextField("user name", 15);
    private JTextField emailField = new JTextField("user email", 15);
    private JButton newUserButton = new JButton("New User");
    private JButton enterUserPage = new JButton("Enter User Page");
    private JButton showPotentialInfections = new JButton("Show Potential Infections");
    private JButton savePamakBookButton = new JButton("Save PamakBook");

    private ArrayList<User> allUsers;
    private ArrayList<Group> allGroups; // Λίστα για τις ομάδες του συστήματος

    public GUI(ArrayList<User> allUsers, ArrayList<Group> allGroups) {
        this.allUsers = allUsers;
        this.allGroups = allGroups;

        this.setSize(450, 300);
        this.setTitle("PamakBook - Κεντρική Σελίδα");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Γραμμή 1: New User + Fields
        gbc.gridx = 0; gbc.gridy = 0;
        this.add(newUserButton, gbc);
        
        gbc.gridx = 1;
        this.add(nameField, gbc);
        
        gbc.gridx = 2;
        this.add(emailField, gbc);

        // Γραμμή 2: Enter User Page
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 3;
        this.add(enterUserPage, gbc);

        // Γραμμή 3: Show Potential Infections
        gbc.gridx = 0; gbc.gridy = 2;
        this.add(showPotentialInfections, gbc);

        // Γραμμή 4: Save PamakBook
        gbc.gridx = 0; gbc.gridy = 3;
        this.add(savePamakBookButton, gbc);

        // Listeners
        ButtonListener listener = new ButtonListener();
        newUserButton.addActionListener(listener);
        enterUserPage.addActionListener(listener);
        showPotentialInfections.addActionListener(listener);
        savePamakBookButton.addActionListener(listener);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // Μέθοδος αναζήτησης χρήστη μόνο με το όνομα (όπως ζητάει η εκφώνηση για το login)
    public User findUser(String name) {
        for (User u : allUsers) {
            if (u.getName().equalsIgnoreCase(name)) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public ArrayList<Group> getAvailableGroups() {
        return allGroups;
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();

            if (e.getSource() == newUserButton) {
                if (findUser(name) == null) {
                    User newUser = new User(name, email);
                    allUsers.add(newUser);
                    JOptionPane.showMessageDialog(GUI.this, "User " + name + " created successfully!");
                } else {
                    // Αν υπάρχει ήδη, εμφανίζει το παράθυρο Message
                    new Message(name, GUI.this);
                }
            } 
            else if (e.getSource() == enterUserPage) {
                User found = findUser(name);
                if (found != null) {
                    new UserPage(GUI.this, found);
                    GUI.this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(GUI.this, "User not found! Please check the name.");
                }
            } 
            else if (e.getSource() == showPotentialInfections) {
                User found = findUser(name);
                if (found != null) {
                    new InfectionsPage(found, GUI.this);
                    GUI.this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(GUI.this, "User not found!");
                }
            } 
            else if (e.getSource() == savePamakBookButton) {
                saveData();
            }
        }
    }

    // Λειτουργία Serialization για την αποθήκευση
    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pamakbook.dat"))) {
            oos.writeObject(allUsers);
            // Σημείωση: Αν θέλετε να αποθηκεύετε και τα groups, 
            // θα πρέπει να τα βάλετε σε μια ενιαία δομή (π.χ. ένα Wrapper object)
            JOptionPane.showMessageDialog(this, "All data saved to pamakbook.dat");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}