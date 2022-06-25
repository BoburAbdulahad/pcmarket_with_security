package uz.bob.pcmarket_with_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.bob.pcmarket_with_security.entity.Attachment;
import uz.bob.pcmarket_with_security.projection.CustomAttachment;
import uz.bob.pcmarket_with_security.projection.CustomCategory;

@RepositoryRestResource(path = "attachment",collectionResourceRel = "attachmentList",excerptProjection = CustomAttachment.class)
public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {

}
