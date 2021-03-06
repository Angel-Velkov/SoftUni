package course.springdata.hibernateintro.entity;

import java.util.Date;
import java.util.Objects;

public class Student {
    private static long idCounter = 1;
    private long id;
    private String name;
    private Date registrationDate;

    public Student() {
    }

    public Student(String name) {
        this(idCounter++, name);
    }

    public Student(long id, String name) {
        this.id = id;
        this.name = name;
        this.registrationDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student {" + System.lineSeparator() +
                "   id = " + id + System.lineSeparator() +
                "   name = " + name + System.lineSeparator() +
                "   registrationDate = " + registrationDate + System.lineSeparator() +
                '}';
    }
}