package com.studies.course.services;

import com.studies.course.entities.Product;
import com.studies.course.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll(){
        List list = productRepository.findAll();
        return list;
    }

    public Product findById(Long id){
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }
}
