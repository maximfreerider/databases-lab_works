package com.kopach.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kopach.DTO.EntityInterface;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customer", schema = "lab_8_m")
public class Customer implements EntityInterface {
    private Long id;
    private String name_of_customer;
    private String surname_of_customer;
    private Brand brandByBrand;
    Set<Testdrive> testdrives = new HashSet<>();


    public Customer(String name, String surname, Brand brandByBrand) {
        this.name_of_customer = name;
        this.surname_of_customer = surname;
        this.brandByBrand = brandByBrand;
    }

    public Customer(Long id, String name, String surname) {
        this.id = id;
        this.name_of_customer = name;
        this.surname_of_customer = surname;
    }

    public Customer() {
    }


    @Id
    @Column(name = "id_of_customer", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_of_customer", nullable = true, length = 50)
    public String getName_of_customer() {
        return name_of_customer;
    }

    public void setName_of_customer(String name_of_customer) {
        this.name_of_customer = name_of_customer;
    }

    @Basic
    @Column(name = "surname_of_customer", nullable = true, length = 50)
    public String getSurname_of_customer() {
        return surname_of_customer;
    }

    public void setSurname_of_customer(String surname_of_customer) {
        this.surname_of_customer = surname_of_customer;
    }

    @ManyToOne
    @JoinColumn(name = "id_of_brand", referencedColumnName = "id_of_brand", nullable = false)
    @JsonIgnore
    public Brand getBrandByBrand() {
        return brandByBrand;
    }

    public void setBrandByBrand(Brand brandByBrand) {
        this.brandByBrand = brandByBrand;
    }


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "instructor",
            joinColumns = { @JoinColumn(name = "id_of_customer", referencedColumnName = "id_of_customer", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id_of_testdrive", referencedColumnName = "id_of_testdrive", nullable = false), }
    )
    @JsonIgnore
    public Set<Testdrive> getTestdrives() {
        return testdrives;
    }

    public void setTestdrives(Set<Testdrive> testdrives) {
        this.testdrives = testdrives;
    }

    public void addInstructor(Testdrive customerEntity){
        if(!getTestdrives().contains(customerEntity)){
            getTestdrives().add(customerEntity);
        }
        if(!customerEntity.getMobileSet().contains(this)){
            customerEntity.getMobileSet().add(this);
        }
    }

    public void deleteTestdriveEntity(Testdrive customer){
        if(getTestdrives().contains(customer)){
            getTestdrives().remove(customer);
        }
        if(customer.getMobileSet().contains(this)){
            customer.getMobileSet().remove(this);
        }
    }



    @Override
    public String toString() {
        return "MobileEntity{" +
                "id=" + id +
                ", name_of_customer='" + name_of_customer + '\'' +
                ", surname_of_customer='" + surname_of_customer + '\'' +
                ", testdrives=" + testdrives +
                '}';
    }

    public String toStringJoinTable(){
        return "MobileEntity{" +
                "id=" + id +
                " testdrives=" + testdrives +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;
        return id == that.id &&
                Objects.equals(name_of_customer, that.name_of_customer) &&
                Objects.equals(surname_of_customer, that.surname_of_customer);}


    @Override
    public int hashCode() {
        return Objects.hash(id, name_of_customer, surname_of_customer);
    }



}
