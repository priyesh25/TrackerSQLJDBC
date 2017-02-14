package model;

import java.util.Date;

/**
 * Created by darryl on 10/12/2016.
 */

public class Employee {
    private int id;
    private String empName;
    private Double empSalary;

    public Employee() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return empName;
    }

    public void setName(String name) {
        this.empName = name;
    }

    public double getSalary() {
        return empSalary;
    }

    public void setSalary(double salary) {
        this.empSalary = salary;
    }

}
