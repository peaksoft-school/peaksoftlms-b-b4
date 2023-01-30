package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.result.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.result.AnswerResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.result.TestResultResponse;

public interface ResultService {
    AnswerResponse saveResult(AnswerRequest answerRequest, String email);

    TestResultResponse getResults(Long id);
}
