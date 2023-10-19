package com.davidreisodev.microsservice.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davidreisodev.microsservice.models.OrderModel;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel,String> {

    
}
