package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.dto.test.TestRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.test.TestResponse;
import kg.peaksoft.peaksoftlmsbb4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.mapper.test.TestMapper;
import kg.peaksoft.peaksoftlmsbb4.model.Lessons;
import kg.peaksoft.peaksoftlmsbb4.model.Test;
import kg.peaksoft.peaksoftlmsbb4.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsbb4.repository.TestRepository;
import kg.peaksoft.peaksoftlmsbb4.service.TestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final LessonRepository lessonRepository;
    private final ModelMapper modelMapper;
    private final TestMapper testMapper;

    @Override
    public TestResponse saveTest(Long id, TestRequest testRequest) {
        Lessons byId = lessonRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("this id not found =%s ", id)
        ));
        Test test = testRepository.save(modelMapper.map(testRequest, Test.class));
        byId.setTests(test);
        log.info("successful test save :{}", test);
        return testMapper.deConvert(test);
    }

    @Override
    public TestResponse findById(Long id) {
        Test test = testRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("not found this id=%s", id)
        ));
        log.info("successful find by this id:{}", id);
        return testMapper.deConvert(test);
    }

    @Override
    public List<TestResponse> findAll() {
        log.info("successful find all");
        List<Test> all = testRepository.findAll();
        return testMapper.deConvert(all);
    }

    @Override
    public TestResponse update(Long id, TestRequest testRequest) {
        boolean exists = testRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException(
                    String.format("not found this id=%s", id)
            );
        }
        Test test = testRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("not found this id=%s", id)
        ));
        String currentTestName = test.getTestName();
        String newTestName = testRequest.getTestName();
        if (!currentTestName.equals(newTestName)) {
            test.setTestName(newTestName);
        }
        log.info("successful test update :{}", test);

        return testMapper.deConvert(test);
    }

    @Override
    public void delete(Long id) {
        testRepository.deleteById(id);
        log.info("successful delete this id:{}", id);
    }
}
