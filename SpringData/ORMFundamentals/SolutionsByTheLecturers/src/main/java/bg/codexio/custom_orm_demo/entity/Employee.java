package bg.codexio.custom_orm_demo.entity;

import orm_framework.annotation.Column;
import orm_framework.annotation.Entity;
import orm_framework.annotation.Id;

@Entity(tableName = "employees")
public class Employee {

    @Id
    private int employeeId;

    @Column(name = "employee_salary", columnDefinition = "DECIMAL(19,4)")
    private double salary;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}