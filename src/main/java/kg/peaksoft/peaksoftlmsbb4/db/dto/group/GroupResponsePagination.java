package kg.peaksoft.peaksoftlmsbb4.db.dto.group;

import lombok.Getter;
import lombok.Setter;

import java.util.Deque;

@Getter
@Setter
public class GroupResponsePagination {
    private int pages;
    private int currentPage;
    private Deque<GroupResponse> groups;
}
