package uz.bob.pcmarket_with_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bob.pcmarket_with_security.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


}
