package com.kopach.domain;

import com.kopach.DTO.EntityInterface;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "testdrive", schema = "lab_8_m")
public class Testdrive implements EntityInterface {
    private Long id;
    private String name_of_testdrive;
    private String surname_of_testdrive;
    private Set<Customer> customers = new HashSet<>();
    @Id
    @Column(name = "id_of_testdrive", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_of_testdrive", nullable = false, length = 50)
    public String getName_of_testdrive() {
        return name_of_testdrive;
    }

    public void setName_of_testdrive(String name_of_testdrive) {
        this.name_of_testdrive = name_of_testdrive;
    }

    @Basic
    @Column(name = "surname_of_testdrive", nullable = true, length = 50)
    public String getSurname_of_testdrive() {
        return surname_of_testdrive;
    }

    public void setSurname_of_testdrive(String surname_of_testdrive) {
        this.surname_of_testdrive = surname_of_testdrive;
    }


    @ManyToMany(targetEntity = Customer.class, mappedBy="testdrives")
    public Set<Customer> getMobileSet() {
        return customers;
    }

    public void setMobileSet(Set<Customer> mobiles) {
        this.customers = mobiles;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Testdrive that = (Testdrive) o;
        return id == that.id &&
                Objects.equals(name_of_testdrive, that.name_of_testdrive) &&
                Objects.equals(surname_of_testdrive, that.surname_of_testdrive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name_of_testdrive, surname_of_testdrive);
    }
    @Override
    public String toString(){
        return "Id= " + id + ", name_of_testdrive= " + name_of_testdrive
                + ", surname_of_testdrive= " + surname_of_testdrive;
    }
}
