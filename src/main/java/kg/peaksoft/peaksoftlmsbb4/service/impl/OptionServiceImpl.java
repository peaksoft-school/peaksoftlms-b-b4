package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.dto.option.OptionRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.option.OptionResponse;
import kg.peaksoft.peaksoftlmsbb4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.mapper.option.OptionMapper;
import kg.peaksoft.peaksoftlmsbb4.model.*;
import kg.peaksoft.peaksoftlmsbb4.repository.OptionRepository;
import kg.peaksoft.peaksoftlmsbb4.repository.VariantRepository;
import kg.peaksoft.peaksoftlmsbb4.service.OptionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;
    private final OptionMapper optionMapper;
    private ModelMapper modelMapper;
    private final VariantRepository variantRepository;


    @Override
    public OptionResponse saveOption(Long id, OptionRequest optionRequest) {
        Variant byId = variantRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("this id not found =%s ", id)
        ));
        Option option = optionRepository.save(modelMapper.map(optionRequest, Option.class));
        log.info("successful test save :{}", option);
        return optionMapper.deConvert(option);
    }

    @Override
    public OptionResponse findById(Long id) {
        Option option = optionRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("not found this id=%s", id)
        ));
        log.info("successful find by this id:{}", id);
        return optionMapper.deConvert(option);
    }

    @Override
    public List<OptionResponse> findAll() {
        log.info("successful find all");
        List<Option> all = optionRepository.findAll();
        return optionMapper.deConvert(all);
    }

    @Override
    public OptionResponse update(Long id, OptionRequest optionRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {
        optionRepository.deleteById(id);
    }
}
