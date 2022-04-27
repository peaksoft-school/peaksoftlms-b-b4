package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.result.ResultMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Result;
import kg.peaksoft.peaksoftlmsbb4.db.model.Variant;
import kg.peaksoft.peaksoftlmsbb4.db.repository.ResultRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.VariantRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.ResultService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final VariantRepository variantRepository;
    private final ResultMapper resultMapper;


    @Override
    public ResultResponse saveResult(ResultRequest resultRequest) {
        Variant variant = variantRepository.findById(resultRequest.getVariantId()).orElseThrow(() -> new BadRequestException(
                String.format("Course with id %s does not exists", resultRequest.getVariantId())
        ));
        resultRequest.setStudentAnswer(variant.getOption());
        resultRequest.setIsTrue(variant.getAnswer());

        Result convert = resultMapper.convert(resultRequest);
        Result save = resultRepository.save(convert);
        return resultMapper.deConvert(save);
    }

    @Override
    public ResultResponse findById(Long id) {
        Result byId = resultRepository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        "this id =%s not found"));
        return resultMapper.deConvert(byId);
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
    public List<Long> countAllByIsTrueTrue() {
        return resultRepository.countAllByIsTrueTrue();
    }
}
