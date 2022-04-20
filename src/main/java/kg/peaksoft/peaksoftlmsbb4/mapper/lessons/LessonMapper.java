package kg.peaksoft.peaksoftlmsbb4.mapper.lessons;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.dto.lessons.LessonRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.lessons.LessonResponse;
import kg.peaksoft.peaksoftlmsbb4.mapper.link.LinkMapper;
import kg.peaksoft.peaksoftlmsbb4.mapper.presentation.PresentationMapper;
import kg.peaksoft.peaksoftlmsbb4.mapper.task.TaskMapper;
import kg.peaksoft.peaksoftlmsbb4.mapper.videolesson.VideoLessonMapper;
import kg.peaksoft.peaksoftlmsbb4.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LessonMapper implements Converter<Lessons, LessonRequest, LessonResponse> {

    private final LinkMapper linkMapper;
    private final VideoLessonMapper videoLessonMapper;
    private final PresentationMapper presentationMapper;
    private final TaskMapper taskMapper;

    @Override
    public Lessons convert(LessonRequest lessonRequest) {
        Lessons lessons = new Lessons();
        lessons.setName(lessonRequest.getName());
        return lessons;
    }

    @Override
    public LessonResponse deConvert(Lessons lessons) {
        LessonResponse lessonResponse = new LessonResponse();
        lessonResponse.setId(lessons.getId());
        lessonResponse.setName(lessons.getName());
        lessonResponse.setLinkResponse(linkMapper.deConvert(lessons.getLinks()));
        lessonResponse.setVideoLessonResponse(videoLessonMapper.deConvert(lessons.getVideoLessons()));
        lessonResponse.setPresentationResponse(presentationMapper.deConvert(lessons.getPresentations()));
        lessonResponse.setTaskResponse(taskMapper.deConvert(lessons.getTasks()));
        return lessonResponse;
    }

}
