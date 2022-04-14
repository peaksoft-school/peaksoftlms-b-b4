package kg.peaksoft.peaksoftlmsbb4.dto.group;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GroupResponse {
    private Long id;
    private String groupName;
    private String description;
    private String image;
    private LocalDate dateOfStart;
}
