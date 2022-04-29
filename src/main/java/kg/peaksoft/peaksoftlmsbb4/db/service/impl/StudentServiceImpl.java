package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.course.CourseResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.AssignStudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.course.CourseMapper;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.result.ResultMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Group;
import kg.peaksoft.peaksoftlmsbb4.db.model.Teacher;
import kg.peaksoft.peaksoftlmsbb4.db.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.StudentService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.student.StudentMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Course;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import kg.peaksoft.peaksoftlmsbb4.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
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
    private final GroupRepository groupRepository;
    private final PasswordEncoder passwordEncoder;
    private final CourseMapper courseMapper;
    private final ResultMapper resultMapper;

    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        String email = studentRequest.getEmail();
        if (studentRepository.existsByUser_Email((email))) {
            throw new BadRequestException(
                    String.format("There is such a = %s", email)
            );
        }
        String password=studentRequest.getPassword();
        studentRequest.setPassword(passwordEncoder.encode(password));
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
        if (!student.getUser().getEmail().equals(studentRequest.getEmail())) {
            student.getUser().setEmail(studentRequest.getEmail());
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
    public void assignStudentToCourse(AssignStudentRequest assignStudentRequest, Long studentId) {
        Course course1 = courseRepository.findById(assignStudentRequest.getCourseId())
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format("Not found course with id=%s", assignStudentRequest.getCourseId())));
        Student student1 = studentRepository.getById(studentId);
        course1.addStudent(student1);

    }

    @Override
    public List<StudentResponse> importExcelFile(MultipartFile files, Long id) throws IOException {
        Group group = groupRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Group with id = %s not found", id)
        ));
        List<StudentResponse> student1 = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {

                StudentRequest student = new StudentRequest();
                XSSFRow row = worksheet.getRow(index);
                student.setStudentName(row.getCell(0).getStringCellValue());
                student.setLastName(row.getCell(1).getStringCellValue());
                student.setEmail(row.getCell(2).getStringCellValue());
                student.setPhoneNumber(String.valueOf(row.getCell(3).getNumericCellValue()));
                student.setStudyFormat(StudyFormat.valueOf(row.getCell(4).getStringCellValue()));
                student.setGroupId(group.getId());
                Student s = studentMapper.convert(student);
                String email = s.getUser().getEmail();
                if (studentRepository.existsByUser_Email((email))) {
                    throw new BadRequestException(
                            String.format("There is such a = %s", email)
                    );
                }
                Student students = studentRepository.save(s);
                group.setStudent(students);

                log.info("successful import excel students:{}", student.getStudentName());

                student1.add(studentMapper.deConvert(students));
            }

        }

        return student1;
    }

    @Override
    public List<CourseResponse> studentCourses(String email) {
        Student student = studentRepository.findStudentByUserEmail(email);
        return courseMapper.deConvert(student.getCourses());
    }

    @Override
    public List<ResultResponse> studentResult(String email) {
        Student studentByUserEmail = studentRepository.findStudentByUserEmail(email);
        return resultMapper.deConvert(studentByUserEmail.getResults());
    }


    @Override
    public List<Student> findByStudentName(String name) {
        return studentRepository.findByStudentName(name);
    }

}
