package kg.peaksoft.peaksoftlmsbb4.db.mapper.test;

import kg.peaksoft.peaksoftlmsbb4.db.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.test.TestRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.test.TestResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.variant.VariantRequest;
import kg.peaksoft.peaksoftlmsbb4.db.enums.QuestionType;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.question.QuestionMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Question;
import kg.peaksoft.peaksoftlmsbb4.db.model.Test;
import kg.peaksoft.peaksoftlmsbb4.db.model.Variant;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
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
        List<Question> questions = new ArrayList<>();
        Test test = new Test();
        test.setTestName(testRequest.getTestName());
        test.setIsEnabled(testRequest.getIsEnabled());
        int counter = 0;
        for (QuestionRequest q : testRequest.getQuestionRequestList()) {
            questions.add(questionMapper.convert(q));
            if (q.getQuestionType() == QuestionType.ONE) {
                for (VariantRequest v : q.getVariantRequests()) {
                    if (v.getAnswer()) {
                        counter++;
                    }
                    if (v.getAnswer()) {
                        if (counter >= 0) {
                            throw new BadRequestException("You can't choose multiple variants");
                        }
                    }
                }

            }else {
                test.setQuestions1(questions);
                return test;
            }
        }
  return null;
    }

    @Override
    public TestResponse deConvert(Test test) {
        List<QuestionResponse> questionResponses = new ArrayList<>();
        TestResponse testResponse = new TestResponse();
        testResponse.setId(test.getId());
        testResponse.setTestName(test.getTestName());
        if (test.getQuestions() != null) {
            testResponse.setQuestionResponses(questionMapper.deConvert(test.getQuestions()));
        }
        assert test.getQuestions() != null;
        for (Question q: test.getQuestions()) {
            questionResponses.add(questionMapper.deConvert(q));
        }
        testResponse.setQuestionResponses(questionResponses);
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
