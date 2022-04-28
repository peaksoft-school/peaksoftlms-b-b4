package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;

import java.util.List;

public interface ResultService {
    ResultResponse saveResult( ResultRequest resultRequest);

    ResultResponse findById(Long id);

    List<ResultResponse> findAll();

    ResultResponse update(Long id, ResultRequest resultRequest);

    void delete(Long id);

    List<Long>countAllByIsTrueTrue();

    long results();
}
