package kg.peaksoft.peaksoftlmsbb4.mapper;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.dto.StudentRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.StudentResponce;
import kg.peaksoft.peaksoftlmsbb4.model.Student;
import org.springframework.stereotype.Component;
@Component
public class StudentMapper implements Converter<Student, StudentRequest, StudentResponce> {

    @Override
    public Student convert(StudentRequest studentRequest) {

        Student student=new Student();
        student.setStudentName(studentRequest.getStudentName());
        student.setLastName(studentRequest.getLastName());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setStudyFormat(studentRequest.getStudyFormat());
        return student;
    }

    @Override
    public StudentResponce deConvert(Student student) {

        StudentResponce studentResponce=new StudentResponce();
        if (studentResponce!=null){
            studentResponce.setId(String.valueOf(student.getId()));
        }
        studentResponce.setStudentName(student.getStudentName());
        studentResponce.setPhoneNumber(student.getPhoneNumber());
        studentResponce.setStudyFormat(student.getStudyFormat());
        return studentResponce;
    }
}
