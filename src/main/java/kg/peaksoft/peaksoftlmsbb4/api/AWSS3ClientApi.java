package kg.peaksoft.peaksoftlmsbb4.api;

import com.amazonaws.services.s3.model.S3Object;
import kg.peaksoft.peaksoftlmsbb4.db.service.impl.AWSS3Service;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
@RestController
@AllArgsConstructor
@RequestMapping("api/s3")
public class AWSS3ClientApi {
    private AWSS3Service awsS3Service;

    @PostMapping
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        String publicURL = awsS3Service.uploadFile(file);
        Map<String, String> response = new HashMap<>();
        response.put("publicURL", publicURL);
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping
    public String deleteFile(@RequestParam String file){
       return awsS3Service.deleteFile(file);
    }

    @GetMapping
    public S3Object download(@RequestParam String file){
        return awsS3Service.downloadFile(file);
    }
}
