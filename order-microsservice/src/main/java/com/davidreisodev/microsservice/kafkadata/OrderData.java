package com.davidreisodev.microsservice.kafkadata;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {
    
    private Integer id;
    
    private List<ProductData> products;

}
