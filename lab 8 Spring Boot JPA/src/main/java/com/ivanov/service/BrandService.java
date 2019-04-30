package com.kopach.service;

import com.kopach.Repository.BrandRepository;
import com.kopach.Repository.CustomerRepository;
import com.kopach.domain.Brand;
import com.kopach.exceptions.ExistsCustomersForGroupOfCustomerException;
import com.kopach.exceptions.NoSuchBrandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;
    private boolean ascending;

    @Autowired
    CustomerRepository customerRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrand(Long brand_id) throws NoSuchBrandException {
        Brand brand = brandRepository.findById(brand_id).get();//2.0.0.M7
        System.out.println(brand);
        if (brand == null) throw new NoSuchBrandException();
        return brand;
    }

    @Transactional
    public void createBrand(Brand brand) {
        brandRepository.save(brand);
    }

    @Transactional
    public void updateBrand(Brand groupOfStudents, Long brand_id) throws NoSuchBrandException {
        Brand brand = brandRepository.findById(brand_id).get();//2.0.0.M7

        if (brand == null) throw new NoSuchBrandException();
        brand.setCustomer(groupOfStudents.getCustomer());
        brandRepository.save(brand);
    }

    @Transactional
    public void updateBrand(Long student_id) throws NoSuchBrandException, ExistsCustomersForGroupOfCustomerException {
        Brand brand = brandRepository.findById(student_id).get();//2.0.0.M7
        if (brand == null) throw new NoSuchBrandException();
        if (brand.getCustomer().size() != 0) throw new ExistsCustomersForGroupOfCustomerException();
        brandRepository.delete(brand);
    }


}
