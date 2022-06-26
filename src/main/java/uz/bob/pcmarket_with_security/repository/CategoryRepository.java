package uz.bob.pcmarket_with_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.pcmarket_with_security.entity.Category;

//@RepositoryRestResource(path = "category",collectionResourceRel = "categoryList",excerptProjection = CustomCategory.class)
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {


    boolean existsByNameAndParentCategoryId(String name, Integer parentCategory_id);

    boolean existsByNameAndParentCategoryIdAndIdNot(String name, Integer parentCategory_id, Integer id);
}
