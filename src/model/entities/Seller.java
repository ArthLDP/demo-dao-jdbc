package model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Seller implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private LocalDateTime birthdate;
    private Double baseSalary;
    private Department department;
    private static final DateTimeFormatter birthdateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Seller(
            Integer id,
            String name,
            String email,
            LocalDateTime birthdate,
            Double baseSalary,
            Department department
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.baseSalary = baseSalary;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDateTime birthdate) {
        this.birthdate = birthdate;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Seller seller = (Seller) object;

        return getId().equals(seller.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return String.format("Seller -> id: %d, name: %s, email: %s, birthdate: %s, base salary: %.2f, %s",
                getId(),
                getName(),
                getEmail(),
                getBirthdate().format(birthdateFormatter),
                getBaseSalary(),
                getDepartment());
    }
}
