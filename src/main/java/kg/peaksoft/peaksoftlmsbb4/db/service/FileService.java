package kg.peaksoft.peaksoftlmsbb4.db.service;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(MultipartFile file);
    S3Object downloadFile(String fileName);
    String deleteFile(String file);
}
