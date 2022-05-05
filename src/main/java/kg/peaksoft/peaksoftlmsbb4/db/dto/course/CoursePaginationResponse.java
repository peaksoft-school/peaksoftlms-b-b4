package kg.peaksoft.peaksoftlmsbb4.db.dto.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoursePaginationResponse {
    private int pages;
    @JsonProperty("current_page")
    private int currentPage;
    private List<CourseResponse> courses;
}
