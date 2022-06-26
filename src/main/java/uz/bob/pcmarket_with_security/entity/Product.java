package uz.bob.pcmarket_with_security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.bob.pcmarket_with_security.entity.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Category category;

    @Column(nullable = false)
    private Double price;

    @OneToOne(optional = false)
    private Attachment photo;

    @OneToOne(optional = false)
    private Characteristic characteristic;
}
