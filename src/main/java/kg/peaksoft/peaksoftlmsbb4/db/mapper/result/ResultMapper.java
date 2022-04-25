package kg.peaksoft.peaksoftlmsbb4.db.mapper.result;

import kg.peaksoft.peaksoftlmsbb4.db.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Result;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResultMapper implements Converter<Result, ResultRequest, ResultResponse> {
    @Override
    public Result convert(ResultRequest resultRequest) {
        return new Result();
    }

    @Override
    public ResultResponse deConvert(Result result) {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setId(result.getId());
        resultResponse.setCorrect(result.getCorrect());
        return resultResponse;
    }

    public List<ResultResponse> deConvert(List<Result> results) {
        List<ResultResponse> resultResponses = new ArrayList<>();
        for (Result o : results) {
            resultResponses.add(deConvert(o));
        }
        return resultResponses;
    }
}
