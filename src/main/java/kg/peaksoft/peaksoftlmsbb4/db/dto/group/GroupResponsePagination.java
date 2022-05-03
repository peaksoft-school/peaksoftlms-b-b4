package kg.peaksoft.peaksoftlmsbb4.db.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsbb4.db.model.Group;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupResponsePagination {
    private int pages;
    @JsonProperty("current_page")
    private int currentPage;
    private List<Group> groups;
}
