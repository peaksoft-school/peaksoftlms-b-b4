package kg.peaksoft.peaksoftlmsbb4.db.mapper.result;

import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.QuestionType;
import kg.peaksoft.peaksoftlmsbb4.db.enums.Result;
import kg.peaksoft.peaksoftlmsbb4.db.model.Question;
import kg.peaksoft.peaksoftlmsbb4.db.model.Results;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import kg.peaksoft.peaksoftlmsbb4.db.model.Test;
import kg.peaksoft.peaksoftlmsbb4.db.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.TestRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.VariantRepository;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
@AllArgsConstructor
public class ResultMapper{
    private final TestRepository testRepository;
    private final VariantRepository variantRepository;
    private final StudentRepository studentRepository;

    public Results convert(AnswerRequest answerRequest,String email) {
        Results results = new Results();
        results.setDateOfPassed(LocalDateTime.now());
        results.setId(answerRequest.getTestId());
        results.setResult(Result.PASSED);
        Test test = testRepository.findById(answerRequest.getTestId()).orElseThrow(() ->
                new BadRequestException(String.format("test with id = %s ", answerRequest.getTestId())));
        int counter = 0;
        int incr = 0;
        for (Question q : test.getQuestions()) {
            counter += calculateGradeOfQuestion(q, answerRequest.getQuestionAnswers().get(incr).getVariantId(), test.getQuestions().size());
            incr++;
        }
        results.setGrade(counter);
        return results;
    }

    public ResultResponse deConvert(Results results) {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setResult(results.getResult());
        resultResponse.setGrade(results.getGrade());
        resultResponse.setId(results.getId());
        resultResponse.setDateOfPassed(results.getDateOfPassed());
        return resultResponse;
    }

    private int calculateGradeOfQuestion(Question question, List<Long> answerOfQuestion, int size) {
        int grade = 0;
        if (question.getQuestionType() == QuestionType.SINGLE) {
            for (Long aLong : answerOfQuestion) {
                if (variantRepository.getById(aLong).getAnswer()) {
                    grade = 100 / size;
                }
            }
        }else {
            int maxGrade = 100 / size;

        }
        return grade;
    }
}
