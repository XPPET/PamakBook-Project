package my_package;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
    private Date timestamp;
    private String description;
    private User creator;

    public Post(Date timestamp, String description, User creator) {
        this.timestamp = timestamp;
        this.description = description;
        this.creator = creator;
    }

    public Date getTimestamp() { return timestamp; }
    public String getDescription() { return description; }
    public User getCreator() { return creator; }
}