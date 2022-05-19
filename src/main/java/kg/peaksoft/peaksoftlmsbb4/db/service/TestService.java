package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.test.TestRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.test.TestResponse;

import java.util.List;

public interface TestService {
    TestResponse saveTest(TestRequest testRequest);

    TestResponse findById(Long id);

    List<TestResponse> findAll();

    TestResponse update(Long id, TestRequest testRequest);

    void delete(Long id);

}
