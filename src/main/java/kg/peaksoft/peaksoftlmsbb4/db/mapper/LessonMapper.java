package kg.peaksoft.peaksoftlmsbb4.db.mapper;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.LessonRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.LessonResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Lesson;
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
        if (lesson.getVideoLesson() != null){
            lessonResponse.setVideoLessonId(lesson.getVideoLesson().getId());
        }
        if (lesson.getPresentation() != null){
            lessonResponse.setPresentationId(lesson.getPresentation().getId());
        }
        if (lesson.getLink() != null){
            lessonResponse.setLinkId(lesson.getLink().getId());
        }
        if (lesson.getTask() != null){
            lessonResponse.setTaskId(lesson.getTask().getId());
        }
        if (lesson.getTest() != null){
            lessonResponse.setTestId(lesson.getTest().getId());
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
