package kg.peaksoft.peaksoftlmsbb4.db.mapper.group;

import kg.peaksoft.peaksoftlmsbb4.db.dto.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.db.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Group;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;
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
        group.setDateOfStart(LocalDate.now());
        if (groupRequest.getDateOfFinish().isBefore(LocalDate.now())) {
            throw new BadRequestException("Date of finish can't after than date of start");
        } else {
            group.setDateOfFinish(groupRequest.getDateOfFinish());
        }
        return group;
    }

    @Override
    public GroupResponse deConvert(Group group) {
        GroupResponse groupResponse = new GroupResponse();
        groupResponse.setId(group.getId());
        groupResponse.setGroupName(group.getGroupName());
        groupResponse.setDescription(group.getDescription());
        groupResponse.setDuration(group.getDateOfStart().getYear() + "-" + group.getDateOfFinish().getYear());
        groupResponse.setImage(group.getImage());
        groupResponse.setDateOfFinish(group.getDateOfFinish());
        return groupResponse;
    }

    public Deque<GroupResponse> deConvert(List<Group> groups) {
        Deque<GroupResponse> groupResponses = new ArrayDeque<>();
        for (Group g : groups) {
            groupResponses.addFirst(deConvert(g));
        }
        return groupResponses;
    }
}
