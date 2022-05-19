package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.result.ResultMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Test;
import kg.peaksoft.peaksoftlmsbb4.db.repository.ResultsRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.TestRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultMapper resultMapper;
    private final ResultsRepository resultsRepository;
    private final TestRepository testRepository;

    @Override
    public AnswerResponse saveResult(AnswerRequest answerRequest, String email) {
        return resultMapper.deConvert(resultsRepository.save(resultMapper.convert(answerRequest, email)));
    }

    @Override
    public List<ResultResponse> getResults(Long id) {
        Test test = testRepository.findById(id).orElseThrow();
        return resultsRepository.findAllByTest(test).stream().
                map(resultMapper::deConvertToResultResponse).
                collect(Collectors.toList());
    }


}
