package kg.peaksoft.peaksoftlmsbb4.db.dto.task;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class TaskResponse {
    private Long id;
    private String text;
    private String file;
    private String name;
    private String image;
    private String link;
    private String code;
}
