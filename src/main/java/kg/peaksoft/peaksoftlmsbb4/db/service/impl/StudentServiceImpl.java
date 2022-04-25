package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.student.StudentMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Course;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import kg.peaksoft.peaksoftlmsbb4.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.StudentService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CourseRepository courseRepository;

    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        String email = studentRequest.getEmail();
        if (studentRepository.existsByEmail((email))) {
            throw new BadRequestException(
                    String.format("There is such a = %s", email)
            );
        }
        Student student = studentMapper.convert(studentRequest);
        Student student1 = studentRepository.save(student);

        log.info("successful save this student:{}", student1);
        return studentMapper.deConvert(student1);

    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
        Student student = getById(id);
        if (!student.getStudentName().equals(studentRequest.getStudentName())) {
            student.setStudentName(studentRequest.getStudentName());
        }
        if (!student.getLastName().equals(studentRequest.getLastName())) {
            student.setLastName(studentRequest.getLastName());
        }
        if (!student.getPhoneNumber().equals(studentRequest.getPhoneNumber())) {
            student.setPhoneNumber(studentRequest.getPhoneNumber());
        }
        if (!student.getStudyFormat().equals(studentRequest.getStudyFormat())) {
            student.setStudyFormat(studentRequest.getStudyFormat());
        }
        if (!student.getEmail().equals(studentRequest.getEmail())) {
            student.setEmail(studentRequest.getEmail());
        }
        log.info("successful update this id:{}", id);
        return studentMapper.deConvert(student);
    }

    @Override
    public StudentResponse findById(Long id) {
        return studentMapper.deConvert(getById(id));
    }

    private Student getById(Long id) {
        log.info("successful find by this id:{}", id);
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException
                (String.format("student with id = %s does not exists ", id)));

    }

    @Override
    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new BadRequestException(String.format("Student with id = %s does not exists", id));
        }
        log.info("successful delete this id:{}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentResponse> findAllStudent(Pageable pageable) {
        log.info("successful find All");
        return studentRepository.findAll(pageable).getContent()
                .stream()
                .map(studentMapper::deConvert).collect(Collectors.toList());

    }

    @Override
    public List<StudentResponse> findByStudyFormat(StudyFormat studyFormat) {
        List<StudentResponse> studentResponse = new ArrayList<>();
        for (Student s : studentRepository.findByStudyFormat(studyFormat)) {
            studentResponse.add(studentMapper.deConvert(s));
        }
        log.info("successful find by Study format:{}", studyFormat);
        return studentResponse;
    }

    @Override
    public void assignStudentToCourse(Long courseId, Long studentId) {
        Course course1 = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format("Not found course with id=%s", courseId)));
        Student student1 = studentRepository.getById(studentId);
        course1.addStudent(student1);

    }
}
