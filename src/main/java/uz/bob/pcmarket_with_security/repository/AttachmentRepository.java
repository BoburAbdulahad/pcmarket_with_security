package uz.bob.pcmarket_with_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.pcmarket_with_security.entity.Attachment;

//@RepositoryRestResource(path = "attachment",collectionResourceRel = "attachmentList",excerptProjection = CustomAttachment.class)
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {

}
