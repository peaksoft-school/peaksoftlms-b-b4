package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.test.TestRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.test.TestResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.test.TestMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Lesson;
import kg.peaksoft.peaksoftlmsbb4.db.model.Test;
import kg.peaksoft.peaksoftlmsbb4.db.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.TestRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.TestService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final LessonRepository lessonRepository;
    private final TestMapper testMapper;

    @Override
    public TestResponse saveTest(TestRequest testRequest) {
        Lesson lesson = lessonRepository.findById(testRequest.getLessonsId()).orElseThrow(() -> new BadRequestException(
                String.format("Course with id %s does not exists", testRequest.getLessonsId())
        ));
        if (lesson.getTest() == null){
            String name = testRequest.getTestName();
            if (testRepository.existsByTestName((name))) {
                throw new BadRequestException(
                        String.format("There is such a = %s ", name)
                );
            }

            Test test = testMapper.convert(testRequest);
            test.setLessons(lesson);
            log.info("successful test save :{}", test);
            Test test1 = testRepository.save(test);
            return testMapper.deConvert(test1);
        }else {
            throw new BadRequestException("in this lesson test already exists");
        }
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
    public TestResponse findByLessonId(Long id) {
        return testMapper.deConvert(lessonRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("lesson with id = %s not found",id))).getTest());
    }


    @Override
    public List<TestResponse> findAll() {
        log.info("successful find all");
        List<Test> all = testRepository.findAll();
        return testMapper.deConvert(all);
    }

    @Override
    @Transactional
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
