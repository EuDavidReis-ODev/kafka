package com.davidreisodev.microsservice.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davidreisodev.microsservice.models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel,String> {

    
}
