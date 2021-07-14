package entities;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.util.Date;
import java.util.Objects;

@Entity(name = "users")
public class User {
    @Id
    private int id;
    @Column(name = "username", columnDefinition = "VARCHAR(30)")
    private String username;
    @Column(name = "password", columnDefinition = "VARCHAR(80)")
    private String password;
    @Column(name = "age", columnDefinition = "INT")
    private int age;
    @Column(name = "registration_date", columnDefinition = "DATE")
    private Date registrationDate;

    public User() {
    }

    public User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.registrationDate = new Date();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User:" + System.lineSeparator()
                + "  id = " + id + System.lineSeparator()
                + "  username = " + username + System.lineSeparator()
                + "  password = " + password + System.lineSeparator()
                + "  age = " + age + System.lineSeparator()
                + "  registration_date = " + registrationDate + System.lineSeparator();
    }
}