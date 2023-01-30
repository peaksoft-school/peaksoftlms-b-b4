package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.LinkRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.LinkResponse;
import org.springframework.stereotype.Service;

@Service
public interface LinkService {
    LinkResponse saveLinks(LinkRequest linkRequest);

    LinkResponse findById(Long id);

    LinkResponse update(Long id, LinkRequest linkRequest);

    LinkResponse delete(Long id);

    LinkResponse findLinkByLessonId(Long id);
}
