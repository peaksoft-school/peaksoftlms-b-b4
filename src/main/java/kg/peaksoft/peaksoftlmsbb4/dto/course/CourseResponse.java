package kg.peaksoft.peaksoftlmsbb4.dto.course;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CourseResponse {
    private Long id;
    private String courseName;
    private String image;
    private String description;
    private LocalDate dateOfStart;
}
