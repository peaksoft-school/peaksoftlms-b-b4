package kg.peaksoft.peaksoftlmsbb4.db.service;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

@Service
public interface FileService {
    String uploadFile(MultipartFile file);
    String deleteFile(String file) throws UnsupportedEncodingException;
}
