package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.AnswerResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TestResultResponse;

public interface ResultService {

    AnswerResponse saveResult(AnswerRequest answerRequest, String email);

    TestResultResponse getResults(Long id);

}
