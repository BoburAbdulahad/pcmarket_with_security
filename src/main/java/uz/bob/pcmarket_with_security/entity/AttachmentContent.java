package uz.bob.pcmarket_with_security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AttachmentContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private byte[] mainContent;

    @OneToOne(optional = false)
    private Attachment attachment;
}
