package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionResponse;

import java.util.List;

public interface QuestionService {
    QuestionResponse saveQuestion(Long id, QuestionRequest questionRequest);

    QuestionResponse findById(Long id);

    List<QuestionResponse> findAll();

    QuestionResponse update(Long id, QuestionRequest questionRequest);

    void delete(Long id);
}
