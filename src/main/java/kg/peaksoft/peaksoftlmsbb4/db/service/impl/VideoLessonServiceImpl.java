package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.VideoLessonResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.VideoLessonMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Lesson;
import kg.peaksoft.peaksoftlmsbb4.db.model.VideoLesson;
import kg.peaksoft.peaksoftlmsbb4.db.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.VideoLessonRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.VideoLessonService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Transactional
@AllArgsConstructor
@Service
public class VideoLessonServiceImpl implements VideoLessonService {

    private final VideoLessonRepository videoLessonRepository;
    private final VideoLessonMapper videoLessonMapper;
    private final LessonRepository lessonRepository;

    @Override
    public VideoLessonResponse saveVideoLessons(VideoLessonRequest request) {
        Lesson lessons = lessonRepository.findById(request.getLessonId()).orElseThrow(() ->
                new NotFoundException(String.format("Lesson with id %s not found", request.getLessonId())));
        VideoLesson videoLesson = videoLessonMapper.convert(request);
        VideoLesson save = videoLessonRepository.save(videoLesson);
        lessons.setVideoLesson(save);
        log.info("successfully save video lesson:{}", videoLesson);
        return videoLessonMapper.deConvert(save);
    }

    @Override
    public VideoLessonResponse findById(Long id) {
        log.info("successfully find video lesson by id:{}", id);
        return videoLessonMapper.deConvert(findBy(id));
    }


    private VideoLesson findBy(Long id) {
        log.info("successfully find video lesson by id:{}", id);
        return videoLessonRepository.findById(id).orElseThrow(() -> {
            log.error("not found video lesson with id:{}", id);
            throw new NotFoundException(String.format("Not found video lesson with id=%s", id));
        });
    }

    @Override
    public VideoLessonResponse update(Long id, VideoLessonRequest request) {
        boolean exist = videoLessonRepository.existsById(id);
        if (!exist) {
            log.error("not found video lesson with id:{}", id);
            throw new NotFoundException(String.format("Not found video lesson with id=%s", id));
        }
        VideoLesson videoLesson = findBy(id);
        if (!videoLesson.getName().equals(request.getName())) {
            videoLesson.setName(request.getName());
        }
        if (!videoLesson.getLink().equals(request.getLink())) {
            videoLesson.setLink(request.getLink());
        }
        if (!videoLesson.getDescription().equals(request.getDescription())) {
            videoLesson.setDescription(request.getDescription());
        }
        log.info("successfully update video lesson  by id:{}", id);
        return videoLessonMapper.deConvert(videoLesson);
    }

    @Override
    public VideoLessonResponse delete(Long id) {
        boolean exist = videoLessonRepository.existsById(id);
        if (!exist) {
            log.error("not found video lesson with id:{}", id);
            throw new NotFoundException(String.format("Not found id=%s", id));
        }
        log.info("successfully delete video lesson by id:{}", id);
        VideoLesson videoLesson = videoLessonRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("student not found %s with id", id)));
        videoLessonRepository.deleteById(id);
        return videoLessonMapper.deConvert(videoLesson);
    }

    @Override
    public VideoLessonResponse findLessonByLessonId(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Lesson with id = %s not found", id)));
        return videoLessonMapper.deConvert(lesson.getVideoLesson());
    }

}
