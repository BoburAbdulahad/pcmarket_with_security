package uz.bob.pcmarket_with_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.bob.pcmarket_with_security.entity.Characteristic;
import uz.bob.pcmarket_with_security.projection.CustomCategory;
import uz.bob.pcmarket_with_security.projection.CustomCharacteristic;

@RepositoryRestResource(path = "characteristic",collectionResourceRel = "characteristicList",excerptProjection = CustomCharacteristic.class)
public interface CharacteristicRepository extends JpaRepository<Characteristic,Integer> {

}
