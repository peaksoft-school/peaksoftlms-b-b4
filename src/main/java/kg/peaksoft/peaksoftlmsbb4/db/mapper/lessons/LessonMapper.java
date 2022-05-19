package kg.peaksoft.peaksoftlmsbb4.db.mapper.lessons;

import kg.peaksoft.peaksoftlmsbb4.db.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.db.dto.lessons.LessonRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.lessons.LessonResponse;
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
