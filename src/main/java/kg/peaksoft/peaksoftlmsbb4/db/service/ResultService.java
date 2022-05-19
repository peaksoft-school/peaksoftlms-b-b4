package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;

import java.util.List;

public interface ResultService {
    AnswerResponse saveResult(AnswerRequest answerRequest, String email);
    List<ResultResponse> getResults(Long id);
}
