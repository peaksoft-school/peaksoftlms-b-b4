package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.dto.videoleson.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.videoleson.VideoLessonResponse;
import kg.peaksoft.peaksoftlmsbb4.model.VideoLesson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VideoLessonService {

    VideoLessonResponse saveVideoLessons(Long id, VideoLessonRequest videoLessonRequest);

    VideoLesson findById(Long id);

    List<VideoLessonResponse> findAll();

    VideoLessonResponse update(Long id, VideoLessonRequest videoLessonRequest);

    void delete(Long id);

}
