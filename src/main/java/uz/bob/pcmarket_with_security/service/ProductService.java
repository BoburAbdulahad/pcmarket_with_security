package uz.bob.pcmarket_with_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.bob.pcmarket_with_security.entity.Attachment;
import uz.bob.pcmarket_with_security.entity.Category;
import uz.bob.pcmarket_with_security.entity.Characteristic;
import uz.bob.pcmarket_with_security.entity.Product;
import uz.bob.pcmarket_with_security.payload.ApiResponse;
import uz.bob.pcmarket_with_security.payload.ProductDto;
import uz.bob.pcmarket_with_security.repository.AttachmentRepository;
import uz.bob.pcmarket_with_security.repository.CategoryRepository;
import uz.bob.pcmarket_with_security.repository.CharacteristicRepository;
import uz.bob.pcmarket_with_security.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CharacteristicRepository characteristicRepository;
    @Autowired
    AttachmentRepository attachmentRepository;


    public List<Product> getProducts(int page,int size){
        Pageable pageable= PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.getContent();
    }

    public Product getProduct(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    public ApiResponse add(ProductDto productDto){
        boolean b = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (b)
            return new ApiResponse("Its product already exist in this category",false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ApiResponse("Category not found",false);
        }
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent()) {
            return new ApiResponse("Photo not found",false);
        }
        Optional<Characteristic> optionalCharacteristic = characteristicRepository.findById(productDto.getCharacteristicId());
        if (!optionalCharacteristic.isPresent()) {
            return new ApiResponse("Characteristics not found",false);
        }

        Product product=new Product(
                null,
                productDto.getName(),
                optionalCategory.get(),
                productDto.getPrice(),
                optionalAttachment.get(),
                optionalCharacteristic.get()
        );
        productRepository.save(product);
        return new ApiResponse("Product saved",true);
    }

    public ApiResponse edit(Integer id,ProductDto productDto){
        boolean b = productRepository.existsByNameAndCategoryIdAndIdNot(productDto.getName(), productDto.getCategoryId(), id);
        if (b)
            return new ApiResponse("Its product name and this category already exist in other id",false);
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return new ApiResponse("Product not found",false);
        }
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setCategory(categoryRepository.getOne(productDto.getCategoryId()));
        product.setPhoto(attachmentRepository.getOne(productDto.getPhotoId()));
        product.setCharacteristic(characteristicRepository.getOne(productDto.getCharacteristicId()));
        product.setPrice(productDto.getPrice());

        Product savedProduct = productRepository.save(product);
        return new ApiResponse("Product edited",true,savedProduct);
    }

    public boolean delete(Integer id){
        try {
            productRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
