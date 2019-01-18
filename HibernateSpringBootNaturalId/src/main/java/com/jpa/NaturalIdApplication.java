package com.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NaturalIdApplication {

    private static final Logger logger = Logger.getLogger(NaturalIdApplication.class.getName());
        
    private final ProductNaturalRepository productNaturalRepository;
    private final ProductRepository productRepository;

    public NaturalIdApplication(ProductNaturalRepository productNaturalRepository, 
            ProductRepository productRepository) {
        this.productNaturalRepository = productNaturalRepository;
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(NaturalIdApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            // persist two products
            Product tshirt = new Product();
            tshirt.setName("T-Shirt");
            tshirt.setCode("014-tshirt-2019");
            // tshirt.setSku(1L);

            Product socks = new Product();
            socks.setName("Socks");
            socks.setCode("012-socks-2018");
            // socks.setSku(2L);

            productRepository.save(tshirt);
            productRepository.save(socks);
            
            // find the first product by a single natural id
            Optional<Product> foundTshirt = 
                    productNaturalRepository.findBySimpleNaturalId("014-tshirt-2019");
         
            // find first product two natural ids (for running this code simply
            // uncomment the "sku" field in the Product entity
            // Map<String, Object> ids = new HashMap<>();
            // ids.put("sku", 1L);
            // ids.put("code", "014-tshirt-2019");            
            // Optional<Product> foundTshirt = productNaturalRepository.findByNaturalId(ids);
                                                
            logger.log(Level.INFO, "Product name: {0}", foundTshirt);
            
        };
    }
}
