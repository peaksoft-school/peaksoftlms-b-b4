package kg.peaksoft.peaksoftlmsbb4.dto.task;

import lombok.Getter;
import lombok.Setter;

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
}
