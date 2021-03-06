package uz.bob.pcmarket_with_security.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.bob.pcmarket_with_security.entity.Characteristic;

@Projection(types = Characteristic.class)
public interface CustomCharacteristic {

    Integer getId();

    String getBrand();

    String getRam();

    String getCpu();

    String getScreen();

    String getSsd();

    String getVc();

    String getHdd();

}
