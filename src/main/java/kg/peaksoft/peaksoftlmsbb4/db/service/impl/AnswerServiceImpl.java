package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.result.GetResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.result.ResultMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Result;
import kg.peaksoft.peaksoftlmsbb4.db.model.Variant;
import kg.peaksoft.peaksoftlmsbb4.db.repository.ResultRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.VariantRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.AnswerResultService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final VariantRepository variantRepository;
    private final ResultMapper resultMapper;
    private final StudentRepository studentRepository;

    @Override
    public ResultResponse saveResult(String email, ResultRequest resultRequest) {
      return null;
    }

    @Override
    public ResultResponse findById(Long id) {
        Result byId = resultRepository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        "this id =%s not found"));
        log.info("successful find by id:{}", byId);
        return resultMapper.deConvert(byId);
    }

    @Override
    public List<ResultResponse> findAll() {
     //   List<Variant> all1 = variantRepository.findAll();
        List<Result> all = resultRepository.findAll();
            return resultMapper.deConvert(all);
    }

    @Override
    public void delete(Long id) {
        resultRepository.deleteById(id);
        log.info("successful delete this id:{}", id);
    }

    @Override
    public GetResultResponse getResults(String email) {
        return null;
    }

    public List<ResultResponse> saveResult1(String email, List<ResultRequest> resultRequest) {
        return null;
    }
}
