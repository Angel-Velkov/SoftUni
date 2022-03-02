package bg.softuni.mobilelele.user;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
    private boolean isLoggedIn;
    private String username;
    private String firstName;
    private String lastName;

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CurrentUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CurrentUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void clean() {
        this.setLoggedIn(false)
                .setFirstName(null)
                .setLastName(null)
                .setUsername(null);
    }
}
