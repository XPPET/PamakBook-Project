package my_package;

import java.io.Serializable;
import java.util.ArrayList;

public class ClosedGroup extends Group implements Serializable {
	
	private ArrayList<User> CloseMembers;


	public ClosedGroup(String name, String description) {
		super(name, description);

	}

	@Override
	public boolean addMember(User aUser) {
	    ArrayList<User> currentMembers = super.members;
	    if (currentMembers.isEmpty()) {
	        currentMembers.add(aUser);
	        aUser.addedToGroup(this);
	        return true;
	    }
	    for (User member : currentMembers) {
	        if (aUser.isFriendWith(member)) {
	            currentMembers.add(aUser);
	            aUser.addedToGroup(this);
	            return true;
	        }
	    }
	    return false; 
	}
}
	 
