package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.dto.lessons.LessonRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.lessons.LessonResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Lessons;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LessonService {
    LessonResponse saveLessons(Long id,LessonRequest lessonRequest);

    LessonResponse findById(Long id);

    List<LessonResponse> findAll();

    LessonResponse update(Long id, LessonRequest lessonRequest);

    void delete(Long id);

}
