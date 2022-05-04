package kg.peaksoft.peaksoftlmsbb4.db.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GroupResponse {
    private Long id;
    @JsonProperty("group_name")
    private String groupName;
    private String description;
    private String image;
    @JsonProperty("date_of_start")
    private LocalDate dateOfStart;
}
