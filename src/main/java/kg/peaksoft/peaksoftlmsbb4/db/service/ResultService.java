package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.dto.result.ResultRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.result.ResultResponse;

import java.util.List;

public interface ResultService {
    ResultResponse saveResult(Long id, ResultRequest resultRequest);

    ResultResponse findById(Long id);

    List<ResultResponse> findAll();

    ResultResponse update(Long id, ResultRequest resultRequest);

    void delete(Long id);

     List<Long> findAllByResultFalseOrderByResult();
}
