package uz.bob.pcmarket_with_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bob.pcmarket_with_security.entity.Category;
import uz.bob.pcmarket_with_security.payload.ApiResponse;
import uz.bob.pcmarket_with_security.payload.CategoryDto;
import uz.bob.pcmarket_with_security.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @GetMapping
    public HttpEntity<List<Category>> getAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size){
        List<Category> list = categoryService.getAll(page, size);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public HttpEntity<Category> getOneById(@PathVariable Integer id){
        Category category = categoryService.getCategory(id);
        return ResponseEntity.status(category!=null?200:404).body(category);
    }

    @PostMapping
    public HttpEntity<ApiResponse> add(@RequestBody CategoryDto categoryDto){
        ApiResponse add = categoryService.add(categoryDto);
        return ResponseEntity.status(add.isSuccess()?201:409).body(add);
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@PathVariable Integer id,@RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.edit(id, categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        boolean deleted = categoryService.deleted(id);
        if (deleted)
            return ResponseEntity.noContent().build();
        return ResponseEntity.noContent().build();
    }


}
