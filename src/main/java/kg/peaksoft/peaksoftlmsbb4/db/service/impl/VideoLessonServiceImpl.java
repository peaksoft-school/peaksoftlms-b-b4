package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.videoleson.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.videoleson.VideoLessonResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.VideoLessonService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.videolesson.VideoLessonMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Lesson;
import kg.peaksoft.peaksoftlmsbb4.db.model.VideoLesson;
import kg.peaksoft.peaksoftlmsbb4.db.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.VideoLessonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class VideoLessonServiceImpl implements VideoLessonService {
    private final VideoLessonRepository videoLessonRepository;
    private final VideoLessonMapper videoLessonMapper;
    private final LessonRepository lessonRepository;

    @Override
    public VideoLessonResponse saveVideoLessons(Long id,VideoLessonRequest videoLessonRequest) {
        Lesson lessons = lessonRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Lesson with id %s not found", id)
        ));
        VideoLesson videoLesson = videoLessonMapper.convert(videoLessonRequest);
        VideoLesson save = videoLessonRepository.save(videoLesson);
        lessons.setVideoLesson(save);
        log.info("successfully save video lesson:{}", videoLesson);
        return videoLessonMapper.deConvert(save);
    }

    @Override
    public VideoLessonResponse findById(Long id) {
        return videoLessonMapper.deConvert(findBy(id));
    }


    private VideoLesson findBy(Long id) {
        log.info("successfully find by id:{}", id);
        return videoLessonRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(String.format("Not found id=%s", id));
        });
    }

    @Override
    public List<VideoLessonResponse> findAll() {
        log.info("successfully find all video lessons");
        return videoLessonRepository.findAll().stream().map(videoLessonMapper::deConvert).collect(Collectors.toList());
    }

    @Override
    public VideoLessonResponse update(Long id, VideoLessonRequest videoLessonRequest) {
        boolean exist = videoLessonRepository.existsById(id);
        if (!exist) {
            throw new NotFoundException(String.format("Not found id=%s", id));
        }
        VideoLesson videoLesson = findBy(id);
        if (!videoLesson.getName().equals(videoLessonRequest.getName())) {
            videoLesson.setName(videoLessonRequest.getName());
        }
        if (!videoLesson.getLink().equals(videoLessonRequest.getLink())) {
            videoLesson.setLink(videoLessonRequest.getLink());
        }
        if (!videoLesson.getDescription().equals(videoLessonRequest.getDescription())) {
            videoLesson.setDescription(videoLessonRequest.getDescription());
        }
        log.info("successfully update id:{}", id);
        return videoLessonMapper.deConvert(videoLesson);
    }

    @Override
    public void delete(Long id) {
        boolean exist = videoLessonRepository.existsById(id);
        if (!exist) {
            throw new NotFoundException(String.format("Not found id=%s", id));
        }
        log.info("successfully delete by id:{}", id);
        videoLessonRepository.deleteById(id);
    }
}
