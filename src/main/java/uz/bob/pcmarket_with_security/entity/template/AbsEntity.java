package uz.bob.pcmarket_with_security.entity.template;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class AbsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private boolean active=true;
}
