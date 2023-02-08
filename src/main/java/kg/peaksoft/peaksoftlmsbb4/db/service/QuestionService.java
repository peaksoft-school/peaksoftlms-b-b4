package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.QuestionRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.QuestionResponse;

import java.util.List;

public interface QuestionService {

    QuestionResponse saveQuestion(QuestionRequest questionRequest);

    QuestionResponse findById(Long id);

    List<QuestionResponse> findAll();

    QuestionResponse update(Long id, QuestionRequest questionRequest);

    void delete(Long id);

}
