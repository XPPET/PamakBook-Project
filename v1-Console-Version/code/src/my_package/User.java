package my_package;

import java.util.ArrayList;

public class User {

	private String name;
	private String email;
	private ArrayList<Group> registeredGroups;
	private ArrayList<User> friends;
	

	
	public User(String name, String email) {
		super();
		this.name = name;
		this.email = email;
		checkForName(this.email);
		this.registeredGroups = new ArrayList<>();
		this.friends=new ArrayList<>();

	}
	
	
	public String getName() {
		return name;
	}



	public String getEmail() {
		return email;
	}

	
	
	public void checkForName(String email) {
		if (!( (this.email.startsWith("ics") ||this.email.startsWith("iis") ||this.email.startsWith("dai"))&& this.email.endsWith("@uom.edu.gr"))) {
			    System.out.println("User " + this.name + " has not been created. Email format is not acceptable.");
			}

	}
	
	
	
	/*Μέθοδο η οποία δέχεται ως παράμετρο έναν άλλο χρήστη και επιστρέφει true ή false ανάλογα με το
	αν είναι φίλοι ή όχι. Κάθε χρήστης ΔΕΝ είναι φίλος με τον εαυτό του.*/
	public boolean isFriendWith(User aUser) {
        if (aUser.name.equals(this.name)) return false; // δεν είναι φίλος με τον εαυτό του
        return friends.contains(aUser); // true αν υπάρχει ήδη στη λίστα φίλων
    }
	
	/*Μέθοδο η οποία προσθέτει στην λίστα των φίλων έναν άλλο χρήστη. Αυτό γίνεται αφού προηγηθεί
	έλεγχος για το αν πρόκειται για το ίδιο πρόσωπο ή για χρήστη που είναι ήδη φίλος.*/
	public void addFriend(User aUser) {
		if (!aUser.isFriendWith(this)) {
		    aUser.friends.add(this);
		    friends.add(aUser);
		    System.out.println(this.name + " and " + aUser.name + " are now friends!");
		} else {
	    System.out.println(this.name + " and " + aUser.name + " are already friends or same person.");
	    }
	}
	
	/*Μέθοδο η οποία δέχεται ως παράμετρο έναν άλλο χρήστη και επιστρέφει μια λίστα με τους κοινούς
	φίλους των δύο χρηστών*/
	public ArrayList<User> commonFriendsWith(User aUser) {
	    ArrayList<User> common = new ArrayList<>();

	    for (User friend : this.friends) {
	        if (aUser.friends.contains(friend)) {
	            common.add(friend);
	        }
	    }

	    if (common.isEmpty()) {
	        System.out.println("No common friends found.");
	    } else {
	        int i = 1;
	        for (User f : common) {
	         System.out.println(i + ": " + f.getName());
	       
	         i++;
	        }
	    }

	    return common;
	}

	
	
	
	/*Μέθοδο η οποία τυπώνει τους φίλους του χρήστη*/
	public void printFriends() {
		if (friends.isEmpty()) {
	        System.out.println("This user has no friends");
	    } else {
	        int i = 1;
	        for (User f : friends) {
	        System.out.println(i + ": " + f.getName());
	        i++;
	        }
	    }
	}
	
	/*Μέθοδο η οποία τυπώνει τα Groups στα οποία έχει γραφτεί ο χρήστης.*/
	public void addedToGroup(Group aGroup) {
		registeredGroups.add(aGroup);
	}
	
	public void printJoinedGroups() {
		if (registeredGroups.isEmpty()) {
	        System.out.println("This use has no Groups");
	    } else {
	    	System.out.println("Groups that " + getName() + " has been enrolled in");
	    	System.out.println("**************************************");
	        int i = 1;
	        for (Group g : registeredGroups) {
	        System.out.println(i + ": " + g.getName());
	        i++;
	        }
	    	System.out.println("--------------------------------------");

	    }
	}
	
	/*Μέθοδο εντοπισμού πιθανών μεταδόσεων ιού, η οποία να επιστρέφει μια λίστα με όλους τους
	φίλους του χρήστη καθώς και όλους τους φίλους αυτών.*/
	public ArrayList<User> scanForVirus() {
		 ArrayList<User> result = new ArrayList<>();
		 for (User friend : this.friends) {
		        if (!result.contains(friend)) {
		            result.add(friend);
		        }
		        for (User friendsFriend : friend.friends) {
		            if (!friendsFriend.equals(this) && !result.contains(friendsFriend)) {
		                result.add(friendsFriend);
		            }
		        }
		    }
		 return result;
	}	
	
	public void printVirusScan() {
	    ArrayList<User> virusList = scanForVirus();

	    if (virusList.isEmpty()) {
	        System.out.println("No users at risk.");
	    } else {
	    	System.out.println(getName()+" has been infected. The following users have to be tested");
	    	System.out.println("*******************************");
	        int i = 1;
	        for (User u : virusList) {
	            System.out.println(u.getName());
	            i++;
	        }
	    }
	    System.out.println("--------------------------------------");
	}
	
}




