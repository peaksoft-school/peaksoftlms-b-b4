package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.TestResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Test;

import java.util.List;

public interface ResultService {
    AnswerResponse saveResult(AnswerRequest answerRequest, String email);

    TestResultResponse getResults(Long id);
}
