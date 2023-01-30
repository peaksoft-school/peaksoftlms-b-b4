package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.group.AssignGroupRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.group.GroupRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.group.GroupResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.group.GroupResponsePagination;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {
    GroupResponse saveGroup(GroupRequest groupRequest);

    GroupResponsePagination getAllForPagination(int page, int size);

    GroupResponse findById(Long id);

    GroupResponse deleteById(Long id);

    GroupResponse update(Long id, GroupRequest groupRequest);

    Group findBy(Long id);

    List<StudentResponse> getAllStudentByGroupId(Long id);

    String assignGroupToCourse(AssignGroupRequest assignGroupRequest);

}
