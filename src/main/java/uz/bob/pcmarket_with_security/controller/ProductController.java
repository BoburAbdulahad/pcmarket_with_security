package uz.bob.pcmarket_with_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.bob.pcmarket_with_security.entity.Product;
import uz.bob.pcmarket_with_security.payload.ApiResponse;
import uz.bob.pcmarket_with_security.payload.ProductDto;
import uz.bob.pcmarket_with_security.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    //ADMIN
//    @PreAuthorize("hasRole('SUPER_ADMIN')")
//    @PreAuthorize("hasAuthority('READ_ALL')")
    @GetMapping
    public HttpEntity<List<Product>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        return new HttpEntity<>(productService.getProducts(page, size));
    }

    //ADMIN,OPERATOR
//    @PreAuthorize("hasAnyRole('SUPER_ADMIN','OPERATOR')")
//    @PreAuthorize("hasAuthority('READ_ONE')")
    @GetMapping("/{id}")
    public HttpEntity<Product> getProduct(@PathVariable Integer id){
        Product product = productService.getProduct(id);
        return ResponseEntity.status(product!=null?200:404).body(product);
    }

    //ADMIN,MODERATOR
//    @PreAuthorize("hasAnyRole('SUPER_ADMIN','MODERATOR')")
//    @PreAuthorize("hasAuthority('ADD')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.add(productDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    //ADMIN,MODERATOR
//    @PreAuthorize("hasAnyRole('SUPER_ADMIN','MODERATOR')")
//    @PreAuthorize("hasAuthority('EDIT')")
    @PutMapping("/{num}")
    public HttpEntity<?> edit(@PathVariable(value = "num") Integer id,@RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.edit(id, productDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    //ADMIN
//    @PreAuthorize("hasRole('SUPER_ADMIN')")
//    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{number}")
    public HttpEntity<?> delete(@PathVariable(name = "number") Integer id){
        boolean b = productService.delete(id);
        return b?ResponseEntity.noContent().build():ResponseEntity.notFound().build();
    }

}
