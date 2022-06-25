package uz.bob.pcmarket_with_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.bob.pcmarket_with_security.entity.AttachmentContent;
import uz.bob.pcmarket_with_security.projection.CustomAttachment;
import uz.bob.pcmarket_with_security.projection.CustomAttachmentContent;
import uz.bob.pcmarket_with_security.projection.CustomCategory;

@RepositoryRestResource(path = "attachmentContent",collectionResourceRel = "attachmentContentList",excerptProjection = CustomAttachmentContent.class)
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {

    AttachmentContent findByAttachmentId(Integer attachment_id);
}
