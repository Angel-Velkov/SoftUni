package springdata.exercises.usersystem.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "users")
public class User {
    private long id;
    private String username;
    private String password;
    private String email;
    private Date registeredOn;
    private Date lastTimeLoggedIn;
    private byte age;
    private boolean isDeleted;

    private Town bornTown;
    private Town currentlyLiving;

    private Set<User> friends;

    public User() {
    }

    public User(String username, String password, String email, byte age) {
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setAge(age);

        this.registeredOn = new Date();

        this.friends = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true, length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.validateUsername(username);

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.validatePassword(password);

        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.validateEmail(email);

        this.email = email;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Date getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.validateAge(age);

        this.age = age;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Town getCurrentlyLiving() {
        return currentlyLiving;
    }

    public void setCurrentlyLiving(Town currentlyLiving) {
        this.currentlyLiving = currentlyLiving;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User user) {
        this.friends.add(user);
    }

    private void validateUsername(String username) {
        this.ensureNonNull(username, "username");

        if (4 > username.length() || username.length() > 30) {
            throw new IllegalArgumentException("The username length needs to be between 4 and 30 symbols.");
        }
    }

    private void validatePassword(String password) {
        this.ensureNonNull(password, "password");

        String regex = "^(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[!#$%^&*()_+<>?])" +
                "(?=\\S+$)" +
                ".{6,50}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.find()) {
            throw new IllegalArgumentException("The password must contain at least:" + System.lineSeparator() +
                    "   1 lowercase letter" + System.lineSeparator() +
                    "   1 uppercase letter" + System.lineSeparator() +
                    "   1 digit" + System.lineSeparator() +
                    "   1 special symbol (!, @, #, $, %, ^, &, *, (, ), _, +, <, >, ?)");
        }
    }

    private void validateEmail(String email) {
        this.ensureNonNull(email, "email");

        String regex = "^(?<user>[a-zA-Z0-9]+([.\\-_]*[a-zA-Z0-9])+)@" +
                "(?<host>[a-zA-Z]+([.-]?[a-zA-Z])\\.[a-zA-Z]+)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid email!");
        }
    }

    private void validateAge(byte age) {
        if (1 > age || age > 120) {
            throw new IllegalArgumentException("The age must be between [1, 120]");
        }
    }

    private void ensureNonNull(Object o, String entityType) {
        if (o == null) {
            throw new IllegalArgumentException("The " + entityType + " cannot be null!");
        }
    }
}
