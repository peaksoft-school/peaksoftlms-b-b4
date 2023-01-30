package kg.peaksoft.peaksoftlmsbb4.db.mapper.videolesson;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.videolesson.VideoLessonResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.VideoLesson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoLessonMapper implements Converter<VideoLesson, VideoLessonRequest, VideoLessonResponse> {
    @Override
    public VideoLesson convert(VideoLessonRequest videoLessonRequest) {
        VideoLesson videoLesson = new VideoLesson();
        videoLesson.setDescription(videoLessonRequest.getDescription());
        videoLesson.setLink(videoLessonRequest.getLink());
        videoLesson.setName(videoLessonRequest.getName());
        return videoLesson;
    }

    @Override
    public VideoLessonResponse deConvert(VideoLesson videoLesson) {
        VideoLessonResponse videoLessonResponse = new VideoLessonResponse();
        videoLessonResponse.setId(videoLesson.getId());
        videoLessonResponse.setLink(videoLesson.getLink());
        videoLessonResponse.setName(videoLesson.getName());
        videoLessonResponse.setDescription(videoLesson.getDescription());
        return videoLessonResponse;
    }


    public List<VideoLessonResponse> deConvert(List<VideoLesson> videoLessons) {
        List<VideoLessonResponse> responses = new ArrayList<>();
        for (VideoLesson videoLesson : videoLessons) {
            responses.add(deConvert(videoLesson));
        }
        return responses;
    }
}
