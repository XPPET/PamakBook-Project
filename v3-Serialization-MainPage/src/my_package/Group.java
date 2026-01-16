package my_package;

import java.io.Serializable;
import java.util.ArrayList;

public class Group  implements Serializable {

	private String name;
	private String description;
	protected  ArrayList<User> members;

	
	public Group(String name, String description) {
		this.name = name;
		this.description = description;
		this.members = new ArrayList<>();

	}
	
	public String getName() {
		return name;
	}



	public String getDescription() {
		return description;
	}

	
	/*Μέθοδο η οποία να δέχεται ως παράμετρο έναν χρήστη και να απαντά (true/false) αν είναι μέλος
	της ομάδας ή όχι.*/
	public boolean isMember(User aUser) {
		 return members.contains(aUser);
	}
	
	/*Μέθοδο η οποία να δέχεται ως παράμετρο έναν χρήστη και αν δεν είναι ήδη μέλος, να τον
	προσθέτει στα μέλη της ομάδας.*/
	public boolean addMember(User aUser) {
	    if (!isMember(aUser)) {
	        members.add(aUser);
	        aUser.addedToGroup(this);
	        return true;
	    }
	    return false;
	}
	
	/*Μέθοδο η οποία να τυπώνει τα μέλη της ομάδας*/
	public void printMembers() {
		if (members.isEmpty()) {
	        //System.out.println("This Group has no members");
	    } else {
	    	//System.out.println("Members of group "+getName());
	    	//System.out.println("*******************************");
	        int i = 1;
	        for (User f : members) {
	       // System.out.println(i + ": " + f.getName());
	        }//System.out.println("-----------------------------");
	    }
	}


	public String toString() {
	    return this.getName();
	}
	
	
	
}
