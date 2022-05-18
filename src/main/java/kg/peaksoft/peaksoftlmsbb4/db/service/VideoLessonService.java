package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.videoleson.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.videoleson.VideoLessonResponse;
import org.springframework.stereotype.Service;

@Service
public interface VideoLessonService {

    VideoLessonResponse saveVideoLessons(VideoLessonRequest videoLessonRequest);

    VideoLessonResponse findById(Long id);

    VideoLessonResponse update(Long id, VideoLessonRequest videoLessonRequest);

    VideoLessonResponse delete(Long id);

    VideoLessonResponse findLessonByLessonId(Long id);

}
