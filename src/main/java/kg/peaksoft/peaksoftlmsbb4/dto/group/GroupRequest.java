package kg.peaksoft.peaksoftlmsbb4.dto.group;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class GroupRequest {
    @NotNull
    private String groupName;
    private String description;
    private String image;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfStart;

}
