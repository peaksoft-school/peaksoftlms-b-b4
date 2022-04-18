package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.mapper.group.GroupMapper;
import kg.peaksoft.peaksoftlmsbb4.mapper.student.StudentMapper;
import kg.peaksoft.peaksoftlmsbb4.model.Course;
import kg.peaksoft.peaksoftlmsbb4.model.Group;
import kg.peaksoft.peaksoftlmsbb4.model.Student;
import kg.peaksoft.peaksoftlmsbb4.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsbb4.service.GroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService {
    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;
    private final StudentMapper studentMapper;

    @Override
    public GroupResponse saveGroup(Long id,GroupRequest groupRequest) {

        String name=groupRequest.getGroupName();
        if (groupRepository.existsByGroupName((name))) {
            throw new BadRequestException(
                    String.format("There is such a = %s", name)
            );
        }
        Group group = groupMapper.convert(id,groupRequest);
        Group save = groupRepository.save(group);
        log.info("successful save group:{}", group);
        return groupMapper.deConvert(save);
    }

    @Override
    public List<GroupResponse> findAllGroup() {
        log.info("successful find all group:{}", groupRepository.findAll());
        return groupRepository.findAll().stream()
                .map(groupMapper::deConvert).collect(Collectors.toList());
    }

    @Override
    public GroupResponse findById(Long id) {
        if (id != null) {
            Group group = findBy(id);
            log.info("successful find by id:{}", id);
            return groupMapper.deConvert(group);
        } else {
            throw new NotFoundException(
                    String.format("not found=%s id", id)
            );
        }
    }

    @Override
    public String deleteById(Long id) {
        boolean exists = groupRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException(
                    String.format("student with id = %s does not exists", id)
            );
        }
        groupRepository.deleteById(id);
        log.info("successful delete group by id:{}", id);
        return String.format("successful delete by id=%s", id);
    }

    @Override
    @Transactional
    public GroupResponse update(Long id, GroupRequest groupRequest) {
        Group group = findBy(id);
        String currentGroupName = group.getGroupName();
        String newGroupName = groupRequest.getGroupName();
        if (!currentGroupName.equals(newGroupName)) {
            group.setGroupName(newGroupName);
        }
        String currentDescription = group.getDescription();
        String newDescription = groupRequest.getDescription();
        if (!currentDescription.equals(newDescription)) {
            group.setDescription(newDescription);
        }
        String currentImagine = group.getImagine();
        String newImagine = groupRequest.getImagine();
        if (!currentImagine.equals(newImagine)) {
            group.setImagine(newImagine);
        }
        log.info("successful update courseId:{}", id);
        return groupMapper.deConvert(group);
    }

    @Override
    public Group findBy(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("client with id = %s does not exists", id)
                ));
    }
    @Override
    public List<StudentResponse> getAllStudentByGroupId(Long id) {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student t : findBy(id).getStudents()) {
            studentResponses.add(studentMapper.deConvert(t));
        }
        return studentResponses;
    }
}
