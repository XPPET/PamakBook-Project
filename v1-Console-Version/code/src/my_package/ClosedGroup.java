package my_package;

import java.util.ArrayList;

public class ClosedGroup extends Group {
	
	private ArrayList<User> CloseMembers;


	public ClosedGroup(String name, String description) {
		super(name, description);

	}

	 public void addMember(User aUser) {
	        ArrayList<User> currentMembers = super.members;

	        if (currentMembers.isEmpty()) {
	            currentMembers.add(aUser);
	            aUser.addedToGroup(this);
	            System.out.println(aUser.getName() + " has been successfully enrolled as the first member in closed group " + getName());
	            return;
	        }
	        
	        boolean isFriendWithAny = false;
	        for (User member : currentMembers) {
	            if (aUser.isFriendWith(member)) {
	                isFriendWithAny = true;
	                break;
	            }
	        }

	        if (isFriendWithAny) {
	            currentMembers.add(aUser);
	            System.out.println(aUser.getName() + " has been successfully enrolled in closed group " + getName());
	        } else {
	            System.out.println("FAILED: " + aUser.getName() + " cannot be enrolled in closed group " + getName());
	        }
	    }
}
	 
