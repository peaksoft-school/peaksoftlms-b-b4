package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.switcher.SwitcherRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.test.TestRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.test.TestResponse;

import java.util.List;

public interface TestService {
    TestResponse saveTest(TestRequest testRequest);

    TestResponse findById(Long id);

    TestResponse findByLessonId(Long id);

    List<TestResponse> findAll();

    TestResponse update(Long id, TestRequest testRequest);

    void delete(Long id);

    boolean switcher(Long id, SwitcherRequest switcherRequest);

}
