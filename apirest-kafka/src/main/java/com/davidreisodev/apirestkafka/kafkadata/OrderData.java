package com.davidreisodev.apirestkafka.kafkadata;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {
    
    private String id;
    private String productName;
    private BigDecimal value;

}
