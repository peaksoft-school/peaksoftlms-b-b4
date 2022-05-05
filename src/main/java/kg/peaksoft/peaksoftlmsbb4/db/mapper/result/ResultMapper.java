package kg.peaksoft.peaksoftlmsbb4.db.mapper.result;

import kg.peaksoft.peaksoftlmsbb4.db.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Result;
import kg.peaksoft.peaksoftlmsbb4.db.model.Test;
import kg.peaksoft.peaksoftlmsbb4.db.repository.TestRepository;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component

public class ResultMapper implements Converter<Result, ResultRequest, ResultResponse> {
    private final TestRepository testRepository;

    @Override
    public Result convert(ResultRequest resultRequest) {
        Result result = new Result();
        Test test = testRepository.findById(resultRequest.getTestId())
                .orElseThrow(() -> new NotFoundException("Test not found"));

        return result;
    }

    @Override
    public ResultResponse deConvert(Result result) {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setId(result.getId());
//        resultResponse.setStudentAnswer(result.getStudentAnswers());
//        resultResponse.setIsTrue(result.getIsTrue());
        return resultResponse;
    }

    public List<ResultResponse> deConvert(List<Result> results) {
        List<ResultResponse> resultResponses = new ArrayList<>();
        for (Result q : results) {
            resultResponses.add(deConvert(q));
        }
        return resultResponses;
    }

    public List<Result> convert1(List<ResultRequest> resultRequests) {
        List<Result> results = new ArrayList<>();
        for (Result q : results) {
//            q.setStudentAnswers(q.getStudentAnswers());
//            q.setIsTrue(q.getIsTrue());
        }
        return results;
    }
}
