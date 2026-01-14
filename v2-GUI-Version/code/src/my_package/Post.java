package my_package;

import java.util.Date;

public class Post {
	
	private Date timestamp;
	private String description;
	private User creator;
	
	
	public Post(Date timestamp, String description, User creator) {
		super();
		this.timestamp = timestamp;
		this.description = description;
		this.creator = creator;
	}
	
	
	public Date getTimestamp() {
		return timestamp;
	}
	public String getDescription() {
		return description;
	}
	public User getCreator() {
		return creator;
	}
	
	
	
	
}
