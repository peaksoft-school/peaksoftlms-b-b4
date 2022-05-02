package kg.peaksoft.peaksoftlmsbb4.db.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(MultipartFile file);
    
}
