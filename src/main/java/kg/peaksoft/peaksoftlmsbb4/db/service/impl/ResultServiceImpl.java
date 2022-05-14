package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.result.AnswerRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.result.ResultMapper;
import kg.peaksoft.peaksoftlmsbb4.db.repository.ResultsRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultMapper resultMapper;
    private final ResultsRepository resultsRepository;

    @Override
    public ResultResponse saveResult(AnswerRequest answerRequest) {
        return resultMapper.deConvert(resultsRepository.save(resultMapper.convert(answerRequest)));
    }
}
