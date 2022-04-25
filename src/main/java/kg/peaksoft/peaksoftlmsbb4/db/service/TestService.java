package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.dto.test.TestRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.test.TestResponse;

import java.util.List;

public interface TestService {
    TestResponse saveTest(Long id, TestRequest testRequest);

    TestResponse findById(Long id);

    List<TestResponse> findAll();

    TestResponse update(Long id, TestRequest testRequest);

    void delete(Long id);
}
