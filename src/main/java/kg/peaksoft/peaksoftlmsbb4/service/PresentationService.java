package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.dto.presentation.PresentationRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.presentation.PresentationResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Presentation;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PresentationService {
    PresentationResponse savePresentation(Long id,PresentationRequest presentationRequest);

    PresentationResponse findById(Long id);

    PresentationResponse update(Long id, PresentationRequest presentationRequest);

    List<PresentationResponse> findAll();

    void delete(Long id);
}
