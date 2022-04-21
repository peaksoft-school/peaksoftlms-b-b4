package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.dto.question.QuestionRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.question.QuestionResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Question;

import java.util.List;

public interface QuestionService {
    QuestionResponse saveQuestion(Long id, QuestionRequest questionRequest);

    QuestionResponse findById(Long id);

    List<QuestionResponse> findAll();

    QuestionResponse update(Long id, QuestionRequest questionRequest);

    void delete(Long id);
}
