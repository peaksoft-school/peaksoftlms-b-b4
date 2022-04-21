package kg.peaksoft.peaksoftlmsbb4.dto.group;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class GroupRequest {
    @NotNull
    @JsonProperty("group_name")
    private String groupName;
    private String description;
    private String image;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("date_of_start")
    private LocalDate dateOfStart;

}
