package kg.peaksoft.peaksoftlmsbb4.controller.payload.lessons;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LessonRequest {
    @NotBlank
    private String name;
    @NotBlank
    private Long courseId;
}
