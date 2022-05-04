package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.student.AssignStudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentPaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.student.StudentMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Course;
import kg.peaksoft.peaksoftlmsbb4.db.model.Group;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import kg.peaksoft.peaksoftlmsbb4.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.StudentService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        String email = studentRequest.getEmail();
        if (studentRepository.existsByEmail((email))) {
            log.error("there is such a student with email :{}", email);
            throw new BadRequestException(
                    String.format("There is such a student with email = %s", email)
            );
        }
        Student student = studentMapper.convert(studentRequest);
        Student student1 = studentRepository.save(student);

        log.info("successful save  student:{}", student1);
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
        log.info("successful update student with id:{}", id);
        return studentMapper.deConvert(student);
    }

    @Override
    public StudentResponse findById(Long id) {
        log.info("successfully find student by id:{}", id);
        return studentMapper.deConvert(getById(id));
    }

    private Student getById(Long id) {
        log.info("successful get student by id:{}", id);
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException
                (String.format("student with id = %s does not exists ", id)));

    }

    @Override
    public String deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            log.error(" student not found with id:{}", id);
            throw new BadRequestException(String.format("Student with id = %s does not exists", id));
        }
        log.info("successful delete student by id:{}", id);
        studentRepository.deleteById(id);
        return "Student deleted";
    }

    @Override
    public List<StudentResponse> findAllStudent() {
        log.info("successful find All");
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::deConvert).collect(Collectors.toList());

    }

    @Override
    public StudentPaginationResponse getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("studentName"));
        StudentPaginationResponse studentPaginationResponse = new StudentPaginationResponse();
        studentPaginationResponse.setPages((studentRepository.findAll(pageable).getTotalPages()));
        studentPaginationResponse.setCurrentPage(pageable.getPageNumber());
        studentPaginationResponse.setStudents(studentMapper.deConvert(studentRepository.findAll(pageable).getContent()));
        return studentPaginationResponse;
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
    public String assignStudentToCourse(AssignStudentRequest assignStudentRequest) {
        Course course = courseRepository.findById(assignStudentRequest.getCourseId())
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format("Not found course with id=%s", assignStudentRequest.getCourseId())));
        Student student = studentRepository.getById(assignStudentRequest.getStudentId());
        course.addStudent(student);
        log.info("successfully assign student to course by student id:{}", assignStudentRequest.getStudentId());
        return String.format("%s added to %s course", student.getStudentName(), course.getCourseName());
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
                String email = s.getEmail();
                if (studentRepository.existsByEmail((email))) {
                    log.error("there is such a student with email:{}", email);
                    throw new BadRequestException(
                            String.format("There is such a student with email= %s", email)
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
    public List<Student> findByStudentName(String name) {
        log.info("successfully filter students by name:{}", name);
        return studentRepository.findByStudentName(name);
    }

}
