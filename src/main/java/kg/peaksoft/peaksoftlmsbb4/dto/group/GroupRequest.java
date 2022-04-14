package kg.peaksoft.peaksoftlmsbb4.dto.group;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class GroupRequest {
    @NotNull
    private String groupName;
    @NotBlank
    private String description;
    private String imagine;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfStart;

}
