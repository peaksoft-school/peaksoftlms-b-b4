package kg.peaksoft.peaksoftlmsbb4.mapper.lessons;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.dto.lessons.LessonRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.lessons.LessonResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Lessons;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper implements Converter<Lessons, LessonRequest, LessonResponse> {

    @Override
    public Lessons convert(LessonRequest lessonRequest) {
        Lessons lessons=new Lessons();
        lessons.setName(lessonRequest.getName());
        return lessons;
    }

    @Override
    public LessonResponse deConvert(Lessons lessons) {
        LessonResponse lessonResponse=new LessonResponse();
        lessonResponse.setId(lessons.getId());
        lessonResponse.setName(lessons.getName());
        return lessonResponse;
    }

}
