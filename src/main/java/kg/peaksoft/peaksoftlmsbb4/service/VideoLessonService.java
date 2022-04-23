package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.videoleson.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.videoleson.VideoLessonResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VideoLessonService {

    VideoLessonResponse saveVideoLessons(Long id,VideoLessonRequest videoLessonRequest);

    VideoLessonResponse findById(Long id);

    List<VideoLessonResponse> findAll();

    VideoLessonResponse update(Long id, VideoLessonRequest videoLessonRequest);

    void delete(Long id);

}
