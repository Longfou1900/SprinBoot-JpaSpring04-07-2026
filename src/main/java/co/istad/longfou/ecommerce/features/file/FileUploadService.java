package co.istad.longfou.ecommerce.features.file;

import co.istad.longfou.ecommerce.features.file.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    FileUploadResponse upload(MultipartFile file);

}
