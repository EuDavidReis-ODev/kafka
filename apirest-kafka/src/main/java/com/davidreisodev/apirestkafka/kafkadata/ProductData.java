package com.davidreisodev.apirestkafka.kafkadata;

import java.math.BigDecimal;
import java.util.UUID;

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
