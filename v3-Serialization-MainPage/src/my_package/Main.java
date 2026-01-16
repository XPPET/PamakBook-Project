package my_package;

import java.io.*;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<User> allUsers = new ArrayList<>();
		ArrayList<Group> allGroups = new ArrayList<>();
		
		File file = new File("pamakbook.dat");
		
		// 1. Έλεγχος αν υπάρχει αποθηκευμένο αρχείο (Serialization)
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				allUsers = (ArrayList<User>) ois.readObject();
				System.out.println("Data loaded successfully from pamakbook.dat");
			} catch (Exception e) {
				System.out.println("Error loading file, creating new data...");
				allUsers = createInitialData(allGroups);
			}
		} else {
			// Αν δεν υπάρχει αρχείο, δημιουργούμε τους αρχικούς χρήστες και ομάδες
			allUsers = createInitialData(allGroups);
		}

		// 2. Εκκίνηση της Γραφικής Διασύνδεσης (GUI)
		// Περνάμε και τη λίστα των χρηστών ΚΑΙ των groups
		new GUI(allUsers, allGroups);
	}

	/**
	 * Μέθοδος για τη δημιουργία αρχικών δεδομένων αν δεν υπάρχει αρχείο
	 */
	private static ArrayList<User> createInitialData(ArrayList<Group> groups) {
		ArrayList<User> users = new ArrayList<>();
		
		// Δημιουργία Χρηστών
		User u1 = new User("Makis", "iis2598@uom.edu.gr");
		User u2 = new User("Petros", "ics2524@uom.edu.gr");
		User u3 = new User("Maria", "iis2512@uom.edu.gr");
		User u4 = new User("Gianna", "iis25133@uom.edu.gr");
		User u5 = new User("Nikos", "dai1758@uom.edu.gr");
		User u6 = new User("Babis", "ics25104@uom.edu.gr");
		
		users.add(u1); users.add(u2); users.add(u3);
		users.add(u4); users.add(u5); users.add(u6);
		
		// Δημιουργία Ομάδων
		Group g1 = new Group("WebGurus", "A group for web passionates");
		ClosedGroup g2 = new ClosedGroup("ExamSolutions", "Solutions to common exam questions");
		
		groups.add(g1);
		groups.add(g2);
		
		// Αρχικές φιλίες
		u1.addFriend(u2);
		u1.addFriend(u5);
		u5.addFriend(u6);
		u3.addFriend(u4);
		
		return users;
	}
}