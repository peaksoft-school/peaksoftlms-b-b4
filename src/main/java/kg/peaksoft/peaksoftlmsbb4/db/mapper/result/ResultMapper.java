package kg.peaksoft.peaksoftlmsbb4.db.mapper.result;

import kg.peaksoft.peaksoftlmsbb4.db.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.GetResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Question;
import kg.peaksoft.peaksoftlmsbb4.db.model.Result;
import kg.peaksoft.peaksoftlmsbb4.db.repository.QuestionRepository;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class ResultMapper implements Converter<Result, ResultRequest, ResultResponse> {
    private final QuestionRepository questionRepository;
    @Override
    public Result convert(ResultRequest resultRequest) {
        Result result = new Result();
        result.setStudentAnswers(resultRequest.getStudentAnswer());
        result.setIsTrue(resultRequest.getIsTrue());
        result.setQuestion(questionRepository.findById(resultRequest.getQuestionId())
                .orElseThrow(()-> new NotFoundException("Question with this id not found found")));
        return result;
    }

    @Override
    public ResultResponse deConvert(Result result) {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setId(result.getId());
        resultResponse.setStudentAnswer(result.getStudentAnswers());
        return resultResponse;
    }

    public List<ResultResponse> deConvert(List<Result> results) {
        List<ResultResponse> resultResponses = new ArrayList<>();
        for (Result q : results) {
            resultResponses.add(deConvert(q));
        }
        return resultResponses;
    }

}
