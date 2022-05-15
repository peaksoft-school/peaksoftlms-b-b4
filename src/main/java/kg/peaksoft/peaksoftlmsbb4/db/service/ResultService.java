package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;

public interface ResultService {
    ResultResponse saveResult(AnswerRequest answerRequest,String email);
}
