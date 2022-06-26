package uz.bob.pcmarket_with_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.bob.pcmarket_with_security.entity.Category;
import uz.bob.pcmarket_with_security.payload.ApiResponse;
import uz.bob.pcmarket_with_security.payload.CategoryDto;
import uz.bob.pcmarket_with_security.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    public List<Category> getAll(int page,int size){
        Pageable pageable= PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.getContent();
    }

    public Category getCategory(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    public ApiResponse add(CategoryDto categoryDto){
        boolean b = categoryRepository.existsByNameAndParentCategoryId(categoryDto.getName(), categoryDto.getParentCategoryId());
        if (b)
            return new ApiResponse("This type category already exist its id",false);
        Category category=new Category();
        if (categoryDto.getParentCategoryId()!=null) {
            if (!categoryRepository.existsById(categoryDto.getParentCategoryId())) {
                return new ApiResponse("Parent category not found",false);
            }
            category.setParentCategory(categoryRepository.getReferenceById(categoryDto.getParentCategoryId()));
        }
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return new ApiResponse("Category added",true);
    }

    public ApiResponse edit(Integer id,CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new ApiResponse("Category not found",false);
        }
        boolean b = categoryRepository.existsByNameAndParentCategoryIdAndIdNot(categoryDto.getName(), categoryDto.getParentCategoryId(), id);
        if (b)
            return new ApiResponse("This category and parentId already exist at the other id",false);
        Category editingCategory = optionalCategory.get();
        if (categoryDto.getParentCategoryId()!=null) {
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalParentCategory.isPresent()) {
                return new ApiResponse("Parent category not found",false);
            }
            editingCategory.setParentCategory(optionalParentCategory.get());
        }
        editingCategory.setName(categoryDto.getName());
        Category savedCategory = categoryRepository.save(editingCategory);
        return new ApiResponse("Category edited",true,savedCategory);
    }

    public boolean deleted(Integer id){
        try {
            categoryRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
