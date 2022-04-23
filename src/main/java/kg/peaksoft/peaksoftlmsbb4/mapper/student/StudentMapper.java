package kg.peaksoft.peaksoftlmsbb4.mapper.student;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.Role;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import kg.peaksoft.peaksoftlmsbb4.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StudentMapper implements Converter<Student, StudentRequest, StudentResponse> {
    private final GroupService groupService;
    @Override
    public Student convert(StudentRequest studentRequest) {
        Student student = new Student();
        student.setStudentName(studentRequest.getStudentName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setStudyFormat(studentRequest.getStudyFormat());
        student.setRole(Role.STUDENT);
        student.setGroup(groupService.findBy(studentRequest.getGroupId()));
        student.setEmail(studentRequest.getEmail());
        return student;
    }

    @Override
    public StudentResponse deConvert(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setStudentName(student.getStudentName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setPhoneNumber(student.getPhoneNumber());
        studentResponse.setStudyFormat(student.getStudyFormat());
        studentResponse.setEmail(student.getEmail());
        return studentResponse;
    }
}
