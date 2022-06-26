package uz.bob.pcmarket_with_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.bob.pcmarket_with_security.entity.AttachmentContent;

import java.util.Optional;

//@RepositoryRestResource(path = "attachmentContent",collectionResourceRel = "attachmentContentList",excerptProjection = CustomAttachmentContent.class)
@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {

    AttachmentContent findByAttachmentId(Integer attachment_id);

}
