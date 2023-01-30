package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.lessons.LessonRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.lessons.LessonResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LessonService {
    LessonResponse saveLessons(LessonRequest lessonRequest);

    LessonResponse findById(Long id);

    List<LessonResponse> findAll(Long id);

    LessonResponse update(Long id, LessonRequest lessonRequest);

    LessonResponse delete(Long id);

}
