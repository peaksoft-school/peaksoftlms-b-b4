package kg.peaksoft.peaksoftlmsbb4.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsbb4.db.service.impl.AWSS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/files")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
@Tag(name = "File API", description = "Aws S3 file endpoints")
public class FileApi {

    private AWSS3Service awsS3Service;

    @Operation(summary = "Upload file", description = "This endpoint for upload file to s3")
    @PostMapping("upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        String publicURL = awsS3Service.uploadFile(file);
        Map<String, String> response = new HashMap<>();
        response.put("URL", publicURL);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete the file ", description = "Delete file in s3")
    @DeleteMapping("delete")
    public String deleteFile(@RequestParam String file) {
        return awsS3Service.deleteFile(file);
    }

    @Operation(summary = "Download file", description = "Download file from aws s3 repository by file name")
    @GetMapping("download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable("filename") String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", MediaType.ALL_VALUE);
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        byte[] bytes = awsS3Service.downloadFile(filename);
        return ResponseEntity.status(HTTP_OK).headers(headers).body(bytes);
    }

}
