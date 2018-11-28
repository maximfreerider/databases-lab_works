package com.kopach.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kopach.DTO.EntityInterface;

import javax.persistence.*;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "brand", schema = "lab_8_m")
public class Brand implements EntityInterface {
    private Long id;
    private int brand;

    public Brand(Long id, int brand) {
        this.id = id;
        this.brand = brand;
    }

    private Set<Customer> customerByBrand;

    @Id
    @Column(name = "id_of_brand", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Brand(Long id) {
        this.id = id;
    }

    public Brand() {
    }

    @Basic
    @Column(name = "name_of_brand", nullable = true)
    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }


    @OneToMany(
            mappedBy = "brandByBrand",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<Customer> getCustomer() {
        return customerByBrand;
    }
    public void setCustomer(Set<Customer> customerByBrand)
    {
        this.customerByBrand = customerByBrand;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand that = (Brand) o;
        return id == that.id &&
                Objects.equals(brand, that.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand);
    }
    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                '}';
    }

}
