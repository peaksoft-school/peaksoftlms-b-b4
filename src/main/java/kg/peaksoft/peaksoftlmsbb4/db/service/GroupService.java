package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.AssignGroupRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.GroupRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.group.GroupResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.group.GroupPaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {
    GroupResponse saveGroup(GroupRequest groupRequest);

    GroupPaginationResponse getAllForPagination(int page, int size);

    GroupResponse findById(Long id);

    GroupResponse deleteById(Long id);

    GroupResponse update(Long id, GroupRequest groupRequest);

    Group findBy(Long id);

    List<StudentResponse> getAllStudentByGroupId(Long id);

    String assignGroupToCourse(AssignGroupRequest assignGroupRequest);

}
