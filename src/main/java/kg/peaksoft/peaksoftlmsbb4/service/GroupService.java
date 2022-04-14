package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Group;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface GroupService {
    GroupResponse saveGroup(GroupRequest groupRequest);
    List<GroupResponse> findAllGroup();
    GroupResponse findById(Long id);
    String deleteById(Long id);
    GroupResponse update(Long id,GroupRequest groupRequest);
    Group findBy(Long id);
}
