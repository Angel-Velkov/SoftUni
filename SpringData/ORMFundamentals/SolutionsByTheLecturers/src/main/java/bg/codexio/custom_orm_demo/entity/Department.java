package bg.codexio.custom_orm_demo.entity;

import orm_framework.annotation.Column;
import orm_framework.annotation.Entity;
import orm_framework.annotation.Id;

@Entity(tableName = "departments")
public class Department {

    @Id
    private int id;

    @Column(name = "name", columnDefinition = "VARCHAR(211)")
    private String name; // "get" + "String"

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}