package kg.peaksoft.peaksoftlmsbb4.db.dto.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CourseResponse {
    private Long id;
    private String courseName;
    private String image;
    private String description;
    private String duration;
    private LocalDate dateOfFinish;
}
