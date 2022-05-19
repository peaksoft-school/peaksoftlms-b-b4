package kg.peaksoft.peaksoftlmsbb4.db.mapper.result;

import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.QuestionType;
import kg.peaksoft.peaksoftlmsbb4.db.enums.Result;
import kg.peaksoft.peaksoftlmsbb4.db.model.*;
import kg.peaksoft.peaksoftlmsbb4.db.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.TestRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.VariantRepository;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
@AllArgsConstructor
public class ResultMapper {
    private final TestRepository testRepository;
    private final VariantRepository variantRepository;
    private final StudentRepository studentRepository;

    public Results convert(AnswerRequest answerRequest, String email) {
        Results results = new Results();
        results.setDateOfPassed(LocalDateTime.now());
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
        List<Long> correctVariantsId = new ArrayList<>();
        for (Question q : results.getTest().getQuestions()) {
            for (Variant v : q.getVariants()) {
                if (v.getAnswer()){
                    correctVariantsId.add(v.getId());
                }
            }
        }
        answerResponse.setCorrectAnswer(correctVariantsId);
        return answerResponse;
    }

    public ResultResponse deConvertToResultResponse(Results result){
        ResultResponse response = new ResultResponse();
        response.setResult(result.getResult());
        response.setId(response.getId());
        response.setDateOfPassed(result.getDateOfPassed());
        response.setGrade(result.getGrade());
        response.setStudentFullName(result.getStudent().getStudentName()+" "+result.getStudent().getLastName());
        return response;
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
                grade = 0;
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
