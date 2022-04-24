package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.dto.result.ResultRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.mapper.result.ResultMapper;
import kg.peaksoft.peaksoftlmsbb4.model.Result;
import kg.peaksoft.peaksoftlmsbb4.model.Variant;
import kg.peaksoft.peaksoftlmsbb4.repository.ResultRepository;
import kg.peaksoft.peaksoftlmsbb4.repository.VariantRepository;
import kg.peaksoft.peaksoftlmsbb4.service.ResultService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final ModelMapper modelMapper;
    private final ResultMapper resultMapper;
    //private final OptionRepository;
    private final VariantRepository variantRepository;

    @Override
    public ResultResponse saveResult(Long id, ResultRequest resultRequest) {
        Variant byId = variantRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("this id not found =%s ", id)
        ));
        if (byId.getIsTrue().equals(true)||byId.getIsTrue().equals(false)) {
            Result result = resultRepository.save(modelMapper.map(resultRequest, Result.class));
            result.setCorrect(byId.getIsTrue());
            byId.setResult(result);
            log.info("successful test save :{}", result);
            return resultMapper.deConvert(result);
        } else {
            throw new NotFoundException("variant is not true");
        }
    }

    @Override
    public ResultResponse findById(Long id) {
        Result result = resultRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("not found this id=%s", id)
        ));
        log.info("successful find by this id:{}", id);
        return resultMapper.deConvert(result);
    }

    @Override
    public List<ResultResponse> findAll() {
        List<Result> all = resultRepository.findAll();
        return resultMapper.deConvert(all);

    }

    @Override
    public ResultResponse update(Long id, ResultRequest resultRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {
        resultRepository.deleteById(id);
    }

    @Override
    public List<Long> findAllByResultFalseOrderByResult() {
        return resultRepository.countAllByCorrectTrue();
    }


}
