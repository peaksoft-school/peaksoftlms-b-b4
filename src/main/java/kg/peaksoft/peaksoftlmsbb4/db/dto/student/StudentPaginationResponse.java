package kg.peaksoft.peaksoftlmsbb4.db.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class StudentPaginationResponse {
    private int pages;
    @JsonProperty("current_page")
    private int currentPage;
    private List<Student> students;
}
