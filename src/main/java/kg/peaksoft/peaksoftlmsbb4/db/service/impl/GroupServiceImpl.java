package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.group.AssignGroupRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.group.GroupResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.group.GroupResponsePagination;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.group.GroupMapper;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.student.StudentMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Course;
import kg.peaksoft.peaksoftlmsbb4.db.model.Group;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import kg.peaksoft.peaksoftlmsbb4.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.GroupService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class GroupServiceImpl implements GroupService {
    private final GroupMapper groupMapper;
    private final GroupRepository groupRepository;
    private final StudentMapper studentMapper;
    private final CourseRepository courseRepository;
    private final AWSS3Service awss3Service;

    @Override
    public GroupResponse saveGroup(GroupRequest groupRequest) {
        String name = groupRequest.getGroupName();
        if (groupRepository.existsByGroupName((name))) {
            log.error("there is such a group name:{}", name);
            throw new BadRequestException(
                    String.format("There is such a group name = %s", name)
            );
        }
        Group group = groupMapper.convert(groupRequest);
        Group save = groupRepository.save(group);
        log.info("successful save group:{}", group);
        return groupMapper.deConvert(save);
    }

    @Override
    public GroupResponsePagination getAllForPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        GroupResponsePagination groupResponsePagination = new GroupResponsePagination();
        groupResponsePagination.setGroups(groupMapper.deConvert(groupRepository.findAll(pageable).getContent()));
        groupResponsePagination.setPages(groupRepository.findAll(pageable).getTotalPages());
        groupResponsePagination.setCurrentPage(pageable.getPageNumber());
        return groupResponsePagination;
    }

    @Override
    public GroupResponse findById(Long id) {
        if (id != null) {
            Group group = findBy(id);
            log.info("group successful find by id:{}", id);
            return groupMapper.deConvert(group);
        } else {
            log.error("not found group with id:{}", id);
            throw new NotFoundException(
                    String.format("not found group =%s id", id)
            );
        }
    }

    @Override
    public GroupResponse deleteById(Long id) {
        boolean exists = groupRepository.existsById(id);
        if (!exists) {
            log.error("not found group with id:{}", id);
            throw new NotFoundException(
                    String.format("group with id = %s does not exists", id)
            );
        }
        if(!groupRepository.getById(id).getImage().equals(" ")){
            awss3Service.deleteFile(groupRepository.getById(id).getImage());
        }
        Group byId = groupRepository.findById(id).orElseThrow(()->new NotFoundException(String.format("Course with id %s not found",id)));
        groupRepository.deleteById(id);
        log.info("successful delete group by id:{}", id);
        return groupMapper.deConvert(byId);
    }

    @Override
    @Transactional
    public GroupResponse update(Long id, GroupRequest groupRequest) {
        Group group = groupRepository.findById(id).orElseThrow(
                ()-> new NotFoundException(String.format("Group with id=%s not found",id))
        );
        if (!group.getGroupName().equals(groupRequest.getGroupName())) {
            group.setGroupName(groupRequest.getGroupName());
        }
        if (!group.getDescription().equals(groupRequest.getDescription())) {
            group.setDescription(groupRequest.getDescription());
        }
        if (!group.getImage().equals(groupRequest.getImage())){
            group.setImage(groupRequest.getImage());
        }
        if (!group.getDateOfFinish().isEqual(groupRequest.getDateOfFinish())){
            group.setDateOfFinish(groupRequest.getDateOfFinish());
        }
        log.info("successful update group by Id:{}", id);
        return groupMapper.deConvert(group);
    }

    @Override
    public Group findBy(Long id) {
        log.info("successful find group by id:{}", id);
        return groupRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format("group with id = %s does not exists", id)
                        ));
    }

    @Override
    public List<StudentResponse> getAllStudentByGroupId(Long id) {
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student t : findBy(id).getStudents()) {
            studentResponses.add(studentMapper.deConvert(t));
        }
        log.info("successful get all student By Group Id this id:{}", id);
        return studentResponses;
    }


    @Override
    public String assignGroupToCourse(AssignGroupRequest assignGroupRequest) {
        Course course = courseRepository.findById(assignGroupRequest.getCourseId())
                .orElseThrow(() -> new BadRequestException(
                        String.format("Course with id %s not found", assignGroupRequest.getCourseId())));
        Group group = groupRepository.getById(assignGroupRequest.getGroupId());
        course.setGroup(group);
        log.info("successfully assign group to course by group id:{}", assignGroupRequest.getGroupId());
        return String.format("Group %s added to %s course", group.getGroupName(), course.getCourseName());
    }
}

