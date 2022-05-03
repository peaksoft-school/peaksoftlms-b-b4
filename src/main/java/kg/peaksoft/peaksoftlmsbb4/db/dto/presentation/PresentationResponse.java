package kg.peaksoft.peaksoftlmsbb4.db.dto.presentation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PresentationResponse {
    private Long id;
    private String name;
    private String description;
    private String file;


}
