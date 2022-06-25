package uz.bob.pcmarket_with_security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.bob.pcmarket_with_security.entity.Attachment;
import uz.bob.pcmarket_with_security.payload.ApiResponse;
import uz.bob.pcmarket_with_security.service.AttachmentService;

import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @GetMapping
    public HttpEntity<List<Attachment>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        List<Attachment> all = attachmentService.getAll(page, size);
        return new HttpEntity<>(all);
    }

    @GetMapping("/id")
    public HttpEntity<?> getOneById(Integer id) {
        Attachment attachment = attachmentService.getOneById(id);
        if (attachment == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.status(200).body(attachment);

    }

    @PostMapping
    public HttpEntity<ApiResponse> add(MultipartHttpServletRequest request) {
        ApiResponse apiResponse = attachmentService.add(request);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@PathVariable Integer id, MultipartHttpServletRequest request) {
        ApiResponse apiResponse = attachmentService.edit(id, request);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        boolean delete = attachmentService.delete(id);
        return delete?ResponseEntity.noContent().build():ResponseEntity.notFound().build();

    }
}
