package kg.peaksoft.peaksoftlmsbb4.mapper.group;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.converter.MyConverter;
import kg.peaksoft.peaksoftlmsbb4.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Group;
import kg.peaksoft.peaksoftlmsbb4.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.checkerframework.common.value.qual.ArrayLen;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GroupMapper implements MyConverter<Group, GroupRequest, GroupResponse> {
    private final CourseRepository courseRepository;
    @Override
    public Group convert(Long id,GroupRequest groupRequest) {
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setDescription(groupRequest.getDescription());
        group.setImagine(groupRequest.getImagine());
        group.setDateOfStart(groupRequest.getDateOfStart());
        group.setCourse(courseRepository.getById(id));
        return group;
    }
    @Override
    public GroupResponse deConvert(Group group) {
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setId(group.getId());
        groupResponse.setGroupName(group.getGroupName());
        groupResponse.setDescription(group.getDescription());
        groupResponse.setDateOfStart(group.getDateOfStart());
        groupResponse.setImage(group.getImagine());
        return groupResponse;
    }
}
