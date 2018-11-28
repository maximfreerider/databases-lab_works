package com.kopach.controller;

import com.kopach.DTO.impl.BrandDTO;
import com.kopach.domain.Brand;

import com.kopach.exceptions.*;
import com.kopach.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class BrandController {
    @Autowired
    BrandService brandService;

    @GetMapping(value = "/api/Brand")
    public ResponseEntity<Set<BrandDTO>> getAllBrands() throws NoSuchCustomerException, NoSuchTestdriveException, NoSuchBrandException {
        List<Brand> brands = brandService.getAllBrands();
        Link link = linkTo(methodOn(BrandController.class).getAllBrands()).withSelfRel();

        Set<BrandDTO> brandDTOS = new HashSet<>();
        for (Brand entity : brands) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            BrandDTO dto = new BrandDTO(entity, selfLink);
            brandDTOS.add(dto);
        }

        return new ResponseEntity<>(brandDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/api/Brand/{brand_id}")
    public ResponseEntity<BrandDTO> getBrand(@PathVariable Long brand_id) throws NoSuchBrandException, NoSuchCustomerException, NoSuchTestdriveException {
        Brand brand = brandService.getBrand(brand_id);
        Link link = linkTo(methodOn(BrandController.class).getBrand(brand_id)).withSelfRel();
        System.out.println(brand);
        BrandDTO brandDTO = new BrandDTO(brand, link);

        return new ResponseEntity<>(brandDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/Brand/{brand_id}")
    public  ResponseEntity<BrandDTO> addBrand(@RequestBody Brand brand) throws NoSuchBrandException, NoSuchCustomerException, NoSuchTestdriveException {
        brandService.createBrand(brand);
        Link link = linkTo(methodOn(BrandController.class).getBrand(brand.getId())).withSelfRel();

        BrandDTO brandDTO = new BrandDTO(brand, link);

        return new ResponseEntity<>(brandDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/Brand/{brand_id}")
    public  ResponseEntity<BrandDTO> updateBrand(@RequestBody Brand uBrand, @PathVariable Long group_id) throws NoSuchBrandException, NoSuchCustomerException, NoSuchTestdriveException {
        brandService.updateBrand(uBrand, group_id);
        Brand brand = brandService.getBrand(group_id);
        Link link = linkTo(methodOn(BrandController.class).getBrand(group_id)).withSelfRel();

        BrandDTO brandDTO = new BrandDTO(brand, link);

        return new ResponseEntity<>(brandDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/Brand/{brand_id}")
    public  ResponseEntity deleteBrand(@PathVariable Long brand_id) throws NoSuchBrandException, ExistsCustomerForTestdriveException, ExistsCustomersForGroupOfCustomerException {
        brandService.updateBrand(brand_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
