package kg.peaksoft.peaksoftlmsbb4.mapper.test;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.dto.test.TestRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.test.TestResponse;
import kg.peaksoft.peaksoftlmsbb4.mapper.question.QuestionMapper;
import kg.peaksoft.peaksoftlmsbb4.model.Test;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class TestMapper implements Converter<Test, TestRequest, TestResponse> {

    private final QuestionMapper questionMapper;

    @Override
    public Test convert(TestRequest testRequest) {
        Test test = new Test();
        test.setTestName(testRequest.getTestName());
        return test;
    }

    @Override
    public TestResponse deConvert(Test test) {
        TestResponse testResponse = new TestResponse();
        testResponse.setId(test.getId());
        testResponse.setTestName(test.getTestName());
        if (test.getQuestions() != null) {
            testResponse.setQuestionResponses(questionMapper.deConvert(test.getQuestions()));
        }
        return testResponse;
    }

    public List<TestResponse> deConvert(List<Test> tests) {
        List<TestResponse> testResponses = new ArrayList<>();
        for (Test t : tests) {
            testResponses.add(deConvert(t));
        }
        return testResponses;
    }
}
