package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.student.AssignStudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentPaginationResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.Role;
import kg.peaksoft.peaksoftlmsbb4.db.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.student.StudentMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Course;
import kg.peaksoft.peaksoftlmsbb4.db.model.Group;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import kg.peaksoft.peaksoftlmsbb4.db.model.User;
import kg.peaksoft.peaksoftlmsbb4.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.StudentService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private final UserRepository userRepository;
    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {
        String email = studentRequest.getEmail();
        if (studentRepository.existsStudentByUserEmail((email))) {
            log.error("there is such a student with email :{}", email);
            throw new BadRequestException(
                    String.format("There is such a student with email = %s", email)
            );
        }
        studentRequest.setPassword(passwordEncoder.encode(studentRequest.getPassword()));
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
        if (!student.getUser().getEmail().equals(studentRequest.getEmail())) {
            student.getUser().setEmail(studentRequest.getEmail());
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
    public StudentResponse deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            log.error(" student not found with id:{}", id);
            throw new BadRequestException(String.format("Student with id = %s does not exists", id));
        }
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Student with id = %s does not found", id)));
        log.info("successful delete student by id:{}", id);
        studentRepository.deleteById(id);
        return studentMapper.deConvert(student);
    }

    @Override
    public StudentPaginationResponse getAll(int page, int size, StudyFormat studyFormat) {
        Pageable pageable = PageRequest.of(page-1, size);
        StudentPaginationResponse studentPaginationResponse = new StudentPaginationResponse();
        if (studyFormat.equals(StudyFormat.ALL)) {
            studentPaginationResponse.setPages((studentRepository.findAll(pageable).getTotalPages()));
            studentPaginationResponse.setCurrentPage(pageable.getPageNumber());
            studentPaginationResponse.setStudents(studentMapper.deConvert(studentRepository.findAllByStudent(pageable).getContent()));
        } else {
            studentPaginationResponse.setPages((studentRepository.findStudentByStudyFormat(studyFormat, pageable).getTotalPages()));
            studentPaginationResponse.setCurrentPage(pageable.getPageNumber());
            studentPaginationResponse.setStudents(studentMapper.deConvert(studentRepository.findStudentByStudyFormat(studyFormat, pageable).getContent()));
        }
        return studentPaginationResponse;
    }

    @Override
    public String assignStudentToCourse(AssignStudentRequest assignStudentRequest) {
        Course course = courseRepository.findById(assignStudentRequest.getCourseId())
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format("Not found course with id=%s",
                                        assignStudentRequest.getCourseId())));
        Student student = studentRepository.getById(assignStudentRequest.getStudentId());
        course.addStudent(student);
        log.info("successfully assign student to course by student id:{}", assignStudentRequest.getStudentId());
        return String.format("%s added to %s course", student.getStudentName(), course.getCourseName());
    }

    @Override
    public List<StudentResponse> importExcelFile(MultipartFile files, Long id) throws IOException {


        Group group = groupRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Group with id = %s does not exists", id)));
        List<Student> students = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet wordSheet = workbook.getSheetAt(0);

        for (int index = 0; index<wordSheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                Student student = new Student();
                User user = new User();
                XSSFRow row = wordSheet.getRow(index);

                Cell studentName = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (studentName != null) {
                    student.setStudentName(row.getCell(0).getStringCellValue());
                } else {
                    throw new BadRequestException("Student name can't be null");
                }


                Cell lastName = row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (lastName != null) {
                    student.setLastName(row.getCell(1).getStringCellValue());
                } else {
                    throw new BadRequestException("Student last name can't be null");

                }

                Cell email = row.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (email != null) {
                    user.setEmail(row.getCell(2).getStringCellValue());
                } else {
                    throw new BadRequestException("Student email can't be null");

                }

                Cell phoneNumber = row.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (phoneNumber != null) {
                    student.setPhoneNumber(String.valueOf((int)row.getCell(3).getNumericCellValue()));
                }else {
                    throw new BadRequestException("Student phoneNumber can't be null");
                }

                Cell studyFormat = row.getCell(4, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (studyFormat != null) {
                    student.setStudyFormat(StudyFormat.valueOf(row.getCell(4).getStringCellValue()));
                } else {
                    throw new BadRequestException("Student studyFormat can't be null");
                }
                Cell password = row.getCell(5, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (password != null) {
                    user.setPassword(passwordEncoder.encode(String.valueOf(row.getCell(5).getNumericCellValue())));
                }else {
                    throw new BadRequestException( "Student password can't be null");
                }
                user.setRole(Role.STUDENT);
                student.setUser(user);
                students.add(student);
            }
        }

        for (Student student: students){
            student.setGroup(group);
            String email = student.getUser().getEmail();
            if (userRepository.existsByEmail((email))) {
                throw new BadRequestException(
                        String.format("There is such a = %s",email )
                );
            }
            studentRepository.save(student);
            log.info("successful import excel students:{}",student.getStudentName());

        }
        List<StudentResponse> studentResponses = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            studentResponses.add(studentMapper.deConvert(student));
        }
        return studentResponses;
    }

    @Override
    public List<StudentResponse> findByStudentName(String name) {
        log.info("successfully filter students by name:{}", name);
        return studentMapper.deConvert(studentRepository.findByStudentName(name));
    }


}
