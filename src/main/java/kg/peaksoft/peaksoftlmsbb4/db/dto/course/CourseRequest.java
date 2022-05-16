package kg.peaksoft.peaksoftlmsbb4.db.dto.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class CourseRequest {
    @NotBlank
    @Size(min = 2, max = 20, message = "course name should be between 2 and 20 characters")
    private String courseName;
    private String image;
    private String description;
    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfFinish;

    public CourseRequest(String courseName, String description, LocalDate dateOfStart) {
        this.courseName = courseName;
        this.description = description;
        this.dateOfFinish = dateOfStart;
    }
}
