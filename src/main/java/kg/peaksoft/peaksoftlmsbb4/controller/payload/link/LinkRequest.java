package kg.peaksoft.peaksoftlmsbb4.controller.payload.link;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LinkRequest {
    @NotBlank
    private String text;
    @NotBlank
    private String link;
    @NotBlank
    private Long lessonId;

}
