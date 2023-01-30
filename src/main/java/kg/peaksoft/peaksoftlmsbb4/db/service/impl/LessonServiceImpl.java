package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.lessons.LessonRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.lessons.LessonResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.lessons.LessonMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Course;
import kg.peaksoft.peaksoftlmsbb4.db.model.Lesson;
import kg.peaksoft.peaksoftlmsbb4.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.LessonService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final CourseRepository courseRepository;


    @Override
    public LessonResponse saveLessons(LessonRequest lessonRequest) {
        Course course = courseRepository.findById(lessonRequest.getCourseId()).orElseThrow(() -> new BadRequestException(
                String.format("Course with id %s does not exists", lessonRequest.getCourseId())
        ));
        Lesson lessons = lessonMapper.convert(lessonRequest);
        Lesson save = lessonRepository.save(lessons);
        course.setLesson(save);
        log.info("successfully save lessons:{}", lessons);
        return lessonMapper.deConvert(save);
    }

    @Override
    public LessonResponse findById(Long id) {
        log.info("successfully find lesson by id:{}", id);
        return lessonMapper.deConvert(getLessonById(id));
    }

    @Override
    public List<LessonResponse> findAll(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new BadRequestException(String.format("course with id %s does not exists", id)));
        log.info("successfully find all lessons by id:{}", id);
        return lessonMapper.deConvert(course.getLessons());
    }

    @Override
    public LessonResponse update(Long id, LessonRequest lessonRequest) {
        boolean exist = lessonRepository.existsById(id);
        if (!exist) {
            log.error("not found  lesson with id:{}", id);
            throw new NotFoundException(String.format("Not found lesson with id=%s", id));
        }
        Lesson lessons = lessonRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Not found id=%s", id)));
        if (!lessons.getName().equals(lessonRequest.getName())) {
            lessons.setName(lessonRequest.getName());
        }
        log.info("lesson successfully update with id:{}", id);
        return lessonMapper.deConvert(lessons);
    }

    @Override
    public LessonResponse delete(Long id) {
        boolean exits = lessonRepository.existsById(id);
        if (!exits) {
            log.error("not found lesson with  id:{}", id);
            throw new NotFoundException(String.format("Lesson is not found id=%s", id));
        }
        log.info("successfully delete lesson with id:{}", id);
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Lesson with id %s not found", id)));
        lessonRepository.deleteById(id);
        return lessonMapper.deConvert(lesson);
    }

    private Lesson getLessonById(Long id) {
        log.info("successfully get lesson by id:{}", id);
        return lessonRepository.findById(id).orElseThrow(() -> new BadRequestException(String.format("lesson with %s not found", id)));
    }
}
