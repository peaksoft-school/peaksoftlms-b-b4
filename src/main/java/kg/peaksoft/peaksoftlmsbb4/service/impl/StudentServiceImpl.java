package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.mapper.StudentMapper;
import kg.peaksoft.peaksoftlmsbb4.model.Student;
import kg.peaksoft.peaksoftlmsbb4.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsbb4.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponse saveStudent(StudentRequest studentRequest) {

        Student  student= studentMapper.convert(studentRequest);

        Student student1 = studentRepository.save(student);

        log.info("save ok");
        return studentMapper.deConvert(student1);

    }
    @Override
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
        Student student = findById(id);
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
        log.info("update ok");
        return studentMapper.deConvert(student);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(()->new NotFoundException
                (String.format("student with id = %s does not exists ",id)));

    }

    @Override
    public void deleteStudent(Long id) {

        boolean exists =studentRepository.existsById(id);

        if(!exists){
            throw new BadRequestException(String.format("student with id = %s does not exists",id));
        }
        log.info("delete ok");
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentResponse> findAllStudent() {
        log.info("findAll ok");
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::deConvert)
                .toList();
    }
}
