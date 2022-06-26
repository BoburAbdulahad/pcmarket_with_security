package uz.bob.pcmarket_with_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.pcmarket_with_security.entity.Product;

//@RepositoryRestResource(path = "product",collectionResourceRel = "productList",excerptProjection = CustomProduct.class)
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    boolean existsByNameAndCategoryId(String name, Integer category_id);

    boolean existsByNameAndCategoryIdAndIdNot(String name, Integer category_id, Integer id);

}
