package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.mapper.group.GroupMapper;
import kg.peaksoft.peaksoftlmsbb4.mapper.student.StudentMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Course;
import kg.peaksoft.peaksoftlmsbb4.db.model.Group;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import kg.peaksoft.peaksoftlmsbb4.repository.CourseRepository;
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
    private final CourseRepository courseRepository;

    @Override
    public GroupResponse saveGroup(GroupRequest groupRequest) {

        String name = groupRequest.getGroupName();
        if (groupRepository.existsByGroupName((name))) {
            throw new BadRequestException(
                    String.format("There is such a = %s", name)
            );
        }
        Group group = groupMapper.convert(groupRequest);
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
        String currentImagine = group.getImage();
        String newImagine = groupRequest.getImage();
        if (!currentImagine.equals(newImagine)) {
            group.setImage(newImagine);
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
        log.info("successful get All Student By Group Id this id:{}", id);
        return studentResponses;
    }
    @Transactional
    @Override
    public void assignGroupToCourse(Long courseId, Long groupId) {
        Course course1 = courseRepository.findById(courseId)
                .orElseThrow(()-> new BadRequestException(
                        String.format("Course with id %s not found",courseId)));
        Group group = groupRepository.getById(groupId);
        course1.setGroup(group);
    }
}

