package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.result.GetResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;

import java.util.List;

public interface ResultService {
    ResultResponse saveResult(String email, ResultRequest resultRequest);

    ResultResponse findById(Long id);

    List<ResultResponse> findAll();

    void delete(Long id);

    GetResultResponse getResults(String email);

    List<ResultResponse> saveResult1(String email, List<ResultRequest> resultRequest);



}
