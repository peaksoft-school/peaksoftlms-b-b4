package kg.peaksoft.peaksoftlmsbb4.mapper;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.dto.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.enums.Role;
import kg.peaksoft.peaksoftlmsbb4.model.Student;
import kg.peaksoft.peaksoftlmsbb4.model.User;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper implements Converter<Student, StudentRequest, StudentResponse> {
    @Override
    public Student convert(StudentRequest studentRequest) {
        Student student=new Student();
        student.setStudentName(studentRequest.getStudentName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setStudyFormat(studentRequest.getStudyFormat());

        User user=new User();
        user.setEmail(studentRequest.getEmail());
        user.setPassword(studentRequest.getPassword());
        user.setRole(Role.STUDENT);
        return student;
    }

    @Override
    public StudentResponse deConvert(Student student) {

        StudentResponse studentResponse=new StudentResponse();
        studentResponse.setStudentName(student.getStudentName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setPhoneNumber(student.getPhoneNumber());
        studentResponse.setStudyFormat(student.getStudyFormat());
        studentResponse.setEmail(student.getUser().getEmail());
        studentResponse.setPassword(student.getUser().getPassword());
        return studentResponse;
    }
}
