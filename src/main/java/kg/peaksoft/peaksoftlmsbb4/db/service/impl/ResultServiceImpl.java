package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.request.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.AnswerResponse;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.response.TestResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.ResultMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Test;
import kg.peaksoft.peaksoftlmsbb4.db.repository.ResultsRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.TestRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ResultServiceImpl implements ResultService {

    private final ResultMapper resultMapper;
    private final ResultsRepository resultsRepository;
    private final TestRepository testRepository;

    @Override
    public AnswerResponse saveResult(AnswerRequest request, String email) {
        return resultMapper.deConvert(resultsRepository.save(resultMapper.convert(request, email)));
    }

    @Override
    public TestResultResponse getResults(Long id) {
        Test test = testRepository.findById(id).orElseThrow();
        return resultMapper.deConvertToResultResponse(test);
    }

}
