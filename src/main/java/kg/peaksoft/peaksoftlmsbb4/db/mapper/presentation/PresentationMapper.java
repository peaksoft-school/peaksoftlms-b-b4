package kg.peaksoft.peaksoftlmsbb4.db.mapper.presentation;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.PresentationRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.presentation.PresentationResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Presentation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class PresentationMapper implements Converter<Presentation, PresentationRequest, PresentationResponse> {

    @Override
    public Presentation convert(PresentationRequest presentationRequest) {
        Presentation presentation = new Presentation();
        presentation.setDescription(presentationRequest.getDescription());
        presentation.setFile(presentationRequest.getFile());
        presentation.setName(presentationRequest.getName());
        return presentation;
    }

    @Override
    public PresentationResponse deConvert(Presentation presentation) {
        PresentationResponse presentationResponse = new PresentationResponse();
        presentationResponse.setId(presentation.getId());
        presentationResponse.setName(presentation.getName());
        presentationResponse.setDescription(presentation.getDescription());
        presentationResponse.setFile(presentation.getFile());
        return presentationResponse;
    }

    public List<PresentationResponse> deConvert(List<Presentation> presentations) {
        List<PresentationResponse> presentationResponsesResponses = new ArrayList<>();
        for (Presentation presentation : presentations) {
            presentationResponsesResponses.add(deConvert(presentation));
        }
        return presentationResponsesResponses;
    }
}
