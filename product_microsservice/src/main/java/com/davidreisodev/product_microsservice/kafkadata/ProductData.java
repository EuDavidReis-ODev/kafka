package com.davidreisodev.product_microsservice.kafkadata;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductData {
    
    private Integer id;
    private String productName;
    private BigDecimal productValue;
    
}
