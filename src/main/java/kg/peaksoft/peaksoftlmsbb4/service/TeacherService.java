package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.dto.TeacherRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.TeacherResponse;

import java.util.Map;

public interface TeacherService {

    TeacherResponse register(TeacherRequest request);

    void deleteById(Long teacherId);

    TeacherResponse updateTeacher(Long id,TeacherRequest teacherRequest);


}
