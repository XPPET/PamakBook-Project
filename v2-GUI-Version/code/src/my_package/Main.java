package my_package;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<User> allUsers = new ArrayList<>();
		
		User u1 = new User("Makis", "iis2598@uom.edu.gr");
		User u2 = new User("Petros", "ics2524@uom.edu.gr");
		User u3 = new User("Maria", "iis2512@uom.edu.gr");
		User u4 = new User("Gianna", "iis25133@uom.edu.gr");
		User u5 = new User("Nikos", "dai1758@uom.edu.gr");
		User u6 = new User("Babis", "ics25104@uom.edu.gr");
		User u7 = new User("Stella", "dai1827@uom.edu.gr");
		User u8 = new User("Eleni", "ics2586@gmail.com");
		//System.out.println("\n");
		
		
		Group g1 = new Group("WebGurus","A group for web passionates");
		ClosedGroup g2 = new ClosedGroup("ExamSolutions","Solutions to common exam questions");
		
		u1.addFriend(u2);
		u1.addFriend(u5);
		u5.addFriend(u6);
		u3.addFriend(u4);
		u3.addFriend(u2);
		u4.addFriend(u6);
		u5.addFriend(u3);
		u1.addFriend(u6);
		u5.addFriend(u2);
		u7.addFriend(u1);
		
		//System.out.println("**************************************");
		//System.out.println("Common friends of Nikos and Gianna");
		//System.out.println("**************************************");
		u5.commonFriendsWith(u4);
		//System.out.println("--------------------------------------");
		
		//System.out.println("**************************************");
		//System.out.println("Common friends of Makis and Nikos");
		u1.commonFriendsWith(u5);
		//System.out.println("**************************************");
		//System.out.println("--------------------------------------");
		
		///System.out.println("Friends of Makis");
		u1.printFriends();
		//System.out.println("**************************************");
		//System.out.println("--------------------------------------");
		
		//System.out.println("**************************************");
		//System.out.println("Friends of Maria");
		u3.printFriends();
		//System.out.println("**************************************");
		
		//group
		g1.addMember(u4);
		g1.addMember(u3);
		g1.addMember(u2);
		
		//closedgroup
		g2.addMember(u4);
		g2.addMember(u5);//fail γιατι ο νικοσ δεν εχει φιλουσ 
		g2.addMember(u6);
		g2.addMember(u5);//μπηκε ο νικοσ κανονικα γιατι εχει τον μπαμπη

	/*	System.out.println("**************************************");
		u4.printJoinedGroups();
    	System.out.println("**************************************");
    	g1.printMembers();
    	System.out.println("*******************************");
    	g2.printMembers();
    	System.out.println("*******************************");
    	u4.printVirusScan(); */
		
		
		allUsers.add(u1);
		allUsers.add(u2);
		allUsers.add(u3);
		allUsers.add(u4);
		allUsers.add(u5);
		allUsers.add(u6);
		allUsers.add(u7);

		GUI gui = new GUI(allUsers);
		
	}

	
	
}
