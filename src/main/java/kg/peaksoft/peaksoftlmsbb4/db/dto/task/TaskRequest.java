package kg.peaksoft.peaksoftlmsbb4.db.dto.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TaskRequest {
    private String text;
    private String file;
    @NotBlank
    private String name;
    private String image;
    private String link;
    private String code;
    @JsonProperty("lesson_id")
    private Long lessonId;
}
