package uz.bob.pcmarket_with_security.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.bob.pcmarket_with_security.entity.Category;

@Projection(types = Category.class)
public interface CustomCategory {

    Integer getId();

    String getName();

    Category getParentCategory();


}
