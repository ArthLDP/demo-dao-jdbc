package model.entities;

import java.io.Serializable;

public class Department implements Serializable {
    private Integer id;
    private String name;

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Department department = (Department) object;

        return getId().equals(department.getId());
    }

    @Override
    public String toString() {
        return String.format("Department -> id: %d, name: %s", getId(), getName());
    }
}
