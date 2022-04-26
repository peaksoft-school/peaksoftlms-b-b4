package kg.peaksoft.peaksoftlmsbb4.db.mapper.lessons;

import kg.peaksoft.peaksoftlmsbb4.db.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.db.dto.lessons.LessonRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.lessons.LessonResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.*;
import kg.peaksoft.peaksoftlmsbb4.db.repository.CourseRepository;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class LessonMapper implements Converter<Lesson, LessonRequest, LessonResponse> {

    @Override
    public Lesson convert(LessonRequest lessonRequest) {
        Lesson lessons = new Lesson();
        lessons.setName(lessonRequest.getName());
        return lessons;
    }

    @Override
    public LessonResponse deConvert(Lesson lesson) {
        LessonResponse lessonResponse = new LessonResponse();
        lessonResponse.setId(lesson.getId());
        lessonResponse.setName(lesson.getName());
        if (lesson.getVideoLessons() != null) {
            List<Long> videoLessonId = new ArrayList<>();

            for (VideoLesson v : lesson.getVideoLessons()) {
                videoLessonId.add(v.getId());
            }
            lessonResponse.setVideoLessonId(videoLessonId);
        }

        if (lesson.getLinks() != null) {
            List<Long> linkId = new ArrayList<>();
            for (Link l : lesson.getLinks()) {
                linkId.add(l.getId());
            }
            lessonResponse.setLinkId(linkId);
        }

        if (lesson.getPresentations() != null) {
            List<Long> presentationId = new ArrayList<>();
            for (Presentation p : lesson.getPresentations()) {
                presentationId.add(p.getId());
            }
            lessonResponse.setPresentationId(presentationId);
        }

        if (lesson.getTasks() != null) {
            List<Long> taskId = new ArrayList<>();
            for (Task t : lesson.getTasks()) {
                taskId.add(t.getId());
            }
            lessonResponse.setTaskId(taskId);
        }
        return lessonResponse;
    }

    public List<LessonResponse> deConvert(List<Lesson> lessons) {
        List<LessonResponse> lessonResponses = new ArrayList<>();
        for (Lesson l : lessons) {
            lessonResponses.add(deConvert(l));
        }
        return lessonResponses;
    }


}
