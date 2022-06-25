package uz.bob.pcmarket_with_security.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.bob.pcmarket_with_security.entity.Attachment;
import uz.bob.pcmarket_with_security.entity.Category;
import uz.bob.pcmarket_with_security.entity.Characteristic;
import uz.bob.pcmarket_with_security.entity.Product;

@Projection(types = Product.class)
public interface CustomProduct {

    Integer getId();

    String getName();

    boolean isActive();

    Category getCategory();

    Double getPrice();

    Attachment getPhoto();

    Characteristic getCharacteristic();



}
