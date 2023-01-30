package kg.peaksoft.peaksoftlmsbb4.controller.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Deque;

@Getter
@Setter
public class CoursePaginationResponse {

    private int pages;
    private int currentPage;
    private Deque<CourseResponse> courses;

}
