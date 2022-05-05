package kg.peaksoft.peaksoftlmsbb4.db.mapper.group;

import kg.peaksoft.peaksoftlmsbb4.db.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.db.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Group;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class GroupMapper implements Converter<Group, GroupRequest, GroupResponse> {

    @Override
    public Group convert(GroupRequest groupRequest) {
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setDescription(groupRequest.getDescription());
        group.setImage(groupRequest.getImage());
        group.setDateOfStart(groupRequest.getDateOfStart());
        return group;
    }

    @Override
    public GroupResponse deConvert(Group group) {
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setId(group.getId());
        groupResponse.setGroupName(group.getGroupName());
        groupResponse.setDescription(group.getDescription());
        groupResponse.setDateOfStart(group.getDateOfStart());
        groupResponse.setImage(group.getImage());
        return groupResponse;
    }

    public List<GroupResponse> deConvert(List<Group> groups){
        List<GroupResponse> groupResponses = new ArrayList<>();
        for (Group g:groups) {
            groupResponses.add(deConvert(g));
        }
        return groupResponses;
    }
}
