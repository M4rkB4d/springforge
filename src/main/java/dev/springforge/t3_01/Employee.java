package dev.springforge.t3_01;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Exercise 1: JPA Entity for Azure SQL
 *
 * This entity works with Azure SQL (MS SQL Server) via Flyway migrations.
 * The schema is created by Flyway, not Hibernate ddl-auto.
 *
 * YOUR TASKS:
 * 1. Add @Table(name = "employees", schema = "dbo")
 * 2. Add @Column(nullable = false, length = 100) to firstName
 * 3. Add @Column(nullable = false, length = 100) to lastName
 * 4. Add @Column(unique = true, nullable = false, length = 150) to email
 * 5. Add @Column(precision = 10, scale = 2) to salary
 *
 * The @Entity, @Id, and @GeneratedValue are already set up.
 * Note: SQL Server uses IDENTITY instead of PostgreSQL's SERIAL.
 */
@Entity
@Table(name = "employees", schema = "dbo")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(unique = true, nullable = false, length = 150)
    private String email;

    private String department;

    @Column(precision = 10, scale = 2)
    private BigDecimal salary;

    private LocalDate hireDate;

    @Column(nullable = false)
    private boolean active = true;

    protected Employee() {}

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters and setters
    public Long getId() { return id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
