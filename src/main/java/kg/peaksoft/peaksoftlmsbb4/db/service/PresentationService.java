package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.PresentationRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.presentation.PresentationResponse;
import org.springframework.stereotype.Service;

@Service
public interface PresentationService {
    PresentationResponse savePresentation(PresentationRequest presentationRequest);

    PresentationResponse findById(Long id);

    PresentationResponse update(Long id, PresentationRequest presentationRequest);


    PresentationResponse delete(Long id);

    PresentationResponse findPresentationByLessonId(Long id);
}
