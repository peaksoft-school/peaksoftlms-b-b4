package kg.peaksoft.peaksoftlmsbb4.db.dto.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseResponse {
    private Long id;
    @JsonProperty("course_name")
    private String courseName;
    private String image;
    private String description;
    @JsonProperty("date_of_start")
    private LocalDate dateOfStart;
}
