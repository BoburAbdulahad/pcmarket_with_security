package uz.bob.pcmarket_with_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.bob.pcmarket_with_security.entity.Category;
import uz.bob.pcmarket_with_security.projection.CustomCategory;

@RepositoryRestResource(path = "category",collectionResourceRel = "categoryList",excerptProjection = CustomCategory.class)
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    boolean existsByParentCategory(Category parentCategory);

    boolean existsByNameAndParentCategoryId(String name, Integer parentCategory_id);
}
