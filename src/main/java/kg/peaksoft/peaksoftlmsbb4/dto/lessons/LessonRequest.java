package kg.peaksoft.peaksoftlmsbb4.dto.lessons;

import kg.peaksoft.peaksoftlmsbb4.model.Course;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LessonRequest {
    @NotBlank
    private String name;

    private Long courseId;

    public void setCourseId(Course byId) {

    }
}
