package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.dto.lessons.LessonRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.lessons.LessonResponse;
import kg.peaksoft.peaksoftlmsbb4.exception.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.mapper.lessons.LessonMapper;
import kg.peaksoft.peaksoftlmsbb4.model.Course;
import kg.peaksoft.peaksoftlmsbb4.model.Lesson;
import kg.peaksoft.peaksoftlmsbb4.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsbb4.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsbb4.service.LessonService;
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
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final CourseRepository courseRepository;


    @Override
    public LessonResponse saveLessons(Long id,LessonRequest lessonRequest) {
        Course course = courseRepository.findById(id).orElseThrow(()-> new BadRequestException(
                String.format("Course with id %s does not exists",id)
        ));
        Lesson lessons = lessonMapper.convert(lessonRequest);
        Lesson save = lessonRepository.save(lessons);
        course.setLesson(save);
        log.info("successfully save lessons:{}", lessons);
        return lessonMapper.deConvert(save);
    }

    @Override
    public LessonResponse findById(Long id) {
        return lessonMapper.deConvert(getLessonByI(id));
    }

    @Override
    public List<LessonResponse> findAll(Long id) {
        return lessonRepository.findAll().stream().map(lessonMapper::deConvert).collect(Collectors.toList());

    }

    @Override
    public LessonResponse update(Long id, LessonRequest lessonRequest) {
        boolean exist = lessonRepository.existsById(id);
        if (!exist) {
            throw new NotFoundException(String.format("Not found id=%s", id));
        }
        Lesson lessons = lessonRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Not found id=%s", id)));
        if (!lessons.getName().equals(lessonRequest.getName())) {
            lessons.setName(lessonRequest.getName());
        }
        log.info("Lesson successfully update id:{}", id);
        return lessonMapper.deConvert(lessons);
    }

    @Override
    public void delete(Long id) {
        boolean exits = lessonRepository.existsById(id);
        if (!exits) {
            throw new NotFoundException(String.format("Lesson is not found id=%s", id));
        }
        log.info("successfully delete id:{}", id);
        lessonRepository.deleteById(id);

    }

    private Lesson getLessonByI(Long id){
      return lessonRepository.findById(id).orElseThrow(()-> new BadRequestException(String.format("lesson with %s not found",id)));
    }
}
