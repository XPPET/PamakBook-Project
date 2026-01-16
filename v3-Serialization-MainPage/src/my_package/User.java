package my_package;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class User implements Serializable {
    private String name;
    private String email;
    private ArrayList<Group> registeredGroups;
    private ArrayList<User> friends;
    private ArrayList<Post> myPosts;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.registeredGroups = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.myPosts = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    
    // Απαραίτητο για τον σωστό γράφο στην GraphWindow
    public ArrayList<User> getFriends() { return friends; }

    public boolean isFriendWith(User aUser) {
        if (aUser.getName().equals(this.name)) return false;
        return friends.contains(aUser);
    }

    public void addFriend(User aUser) {
        if (!isFriendWith(aUser)) {
            this.friends.add(aUser);
            aUser.friends.add(this); // Αμφίδρομη φιλία
        }
    }

    public void addNewPost(Post aPost) { myPosts.add(aPost); }

    public ArrayList<Post> returnAllPosts() {
        ArrayList<Post> allPosts = new ArrayList<>(myPosts);
        for (User friend : friends) {
            allPosts.addAll(friend.myPosts);
        }
        allPosts.sort(Comparator.comparing(Post::getTimestamp).reversed());
        return allPosts;
    }

    public ArrayList<User> suggestedFriends() {
        ArrayList<User> result = new ArrayList<>();
        for (User friend : friends) {
            for (User friendsFriend : friend.friends) {
                if (!friendsFriend.equals(this) && !friends.contains(friendsFriend) && !result.contains(friendsFriend)) {
                    result.add(friendsFriend);
                }
            }
        }
        return result;
    }

    public ArrayList<User> scanForVirus() {
        ArrayList<User> result = new ArrayList<>();
        for (User friend : this.friends) {
            if (!result.contains(friend)) result.add(friend);
            for (User ff : friend.friends) {
                if (!ff.equals(this) && !result.contains(ff)) result.add(ff);
            }
        }
        return result;
    }

    public void addedToGroup(Group g) { registeredGroups.add(g); }
}