package kg.peaksoft.peaksoftlmsbb4.dto.course;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseResponse {
    private Long id;
    private String courseName;
    private String image;
    private String description;
    private LocalDate dateOfStart;
}
