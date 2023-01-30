package kg.peaksoft.peaksoftlmsbb4.controller.payload.group;

import lombok.Getter;
import lombok.Setter;

import java.util.Deque;

@Getter
@Setter
public class GroupPaginationResponse {
    private int pages;
    private int currentPage;
    private Deque<GroupResponse> groups;
}
