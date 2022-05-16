package kg.peaksoft.peaksoftlmsbb4.db.dto.presentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PresentationRequest {
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String file;
    private Long lessonId;

}
