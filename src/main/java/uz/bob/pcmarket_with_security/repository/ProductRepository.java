package uz.bob.pcmarket_with_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.bob.pcmarket_with_security.entity.Product;
import uz.bob.pcmarket_with_security.projection.CustomCategory;
import uz.bob.pcmarket_with_security.projection.CustomProduct;

@RepositoryRestResource(path = "product",collectionResourceRel = "productList",excerptProjection = CustomProduct.class)
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
