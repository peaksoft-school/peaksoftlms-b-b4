package kg.peaksoft.peaksoftlmsbb4.db.mapper.result;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.*;
import kg.peaksoft.peaksoftlmsbb4.db.enums.QuestionType;
import kg.peaksoft.peaksoftlmsbb4.db.enums.Result;
import kg.peaksoft.peaksoftlmsbb4.db.model.*;
import kg.peaksoft.peaksoftlmsbb4.db.repository.ResultsRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.TestRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.VariantRepository;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
@AllArgsConstructor
public class ResultMapper {
    private final TestRepository testRepository;
    private final VariantRepository variantRepository;
    private final StudentRepository studentRepository;
    private final ResultsRepository resultsRepository;

    public Results convert(AnswerRequest answerRequest, String email) {
        Results results = new Results();
        results.setDateOfPassed(LocalDate.now());
        results.setId(answerRequest.getTestId());
        results.setResult(Result.PASSED);
        Test test = testRepository.findById(answerRequest.getTestId()).orElseThrow(() ->
                new BadRequestException(String.format("test with id = %s ", answerRequest.getTestId())));
        results.setTest(test);
        Student student = studentRepository.findStudentByUser_Email(email);
        results.setStudent(student);
        int counter = 0;
        int incr = 0;
        for (Question q : test.getQuestions()) {
            counter += calculateGradeOfQuestion(q, answerRequest.getQuestionAnswers().get(incr).getChoiceId(), test.getQuestions().size());
            incr++;
        }
        results.setGrade(counter);
        return results;
    }

    public AnswerResponse deConvert(Results results) {
        AnswerResponse answerResponse = new AnswerResponse();
        answerResponse.setGrade(results.getGrade());
        answerResponse.setId(results.getId());
        List<AnswerResultResponse> answerResultResponses = new ArrayList<>();
        for (Question q : results.getTest().getQuestions()) {
            List<OptionResponse> optionResponses = new ArrayList<>();
            for (Variant v : q.getVariants()) {
                if (v.getAnswer()) {
                    optionResponses.add(new OptionResponse(v.getId(), v.getOption(), true));
                } else {
                    optionResponses.add(new OptionResponse(v.getId(), v.getOption(), false));
                }
            }
            answerResultResponses.add(new AnswerResultResponse(q.getId(), q.getQuestion(), q.getQuestionType(), optionResponses));
        }
        answerResponse.setAnswerResultResponses(answerResultResponses);
        return answerResponse;
    }

    public TestResultResponse deConvertToResultResponse(Test test) {
        TestResultResponse testResultResponse = new TestResultResponse();
        testResultResponse.setName(test.getTestName());
        testResultResponse.setTestIsEnabled(test.getIsEnabled());
        List<ResultResponse> response = new ArrayList<>();
        for (Results r : resultsRepository.findAllByTest(test)) {
            response.add(new ResultResponse(r.getId(), r.getResult(), r.getDateOfPassed(), r.getGrade(), r.getStudent().getStudentName()));
        }
        testResultResponse.setResultResponses(response);
        return testResultResponse;
    }

    private int calculateGradeOfQuestion(Question question,
                                         List<Long> answerOfQuestion,
                                         int size) {
        int grade = 0;
        if (question.getQuestionType() == QuestionType.SINGLE) {
            for (Long aLong : answerOfQuestion) {
                if (variantRepository.getById(aLong).getAnswer()) {
                    grade = 100 / size;
                }
            }
        } else {
            int maxGrade = 100 / size;
            int counterOfWrongAnswer = 0;
            int counterOfCorrectAnswer = 0;
            int counterOfCorrectVariant = 0;
            for (Variant v : question.getVariants()) {
                if (v.getAnswer()) {
                    counterOfCorrectVariant++;
                }
            }
            for (Long aLong : answerOfQuestion) {
                if (variantRepository.getById(aLong).getAnswer()) {
                    counterOfCorrectAnswer++;
                } else {
                    counterOfWrongAnswer++;
                }
            }
            if (counterOfCorrectAnswer < counterOfWrongAnswer) {
            } else if (counterOfCorrectAnswer == 0) {
                grade = 0;
            } else if (counterOfWrongAnswer == counterOfCorrectAnswer) {
                grade = 0;
            } else if (counterOfCorrectVariant == counterOfCorrectAnswer) {
                grade = maxGrade;
            } else if (counterOfCorrectAnswer + 1 == counterOfCorrectVariant) {
                grade = (maxGrade / counterOfCorrectVariant) * counterOfCorrectAnswer;
            }
        }
        return grade;
    }
}
