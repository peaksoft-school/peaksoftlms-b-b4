package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.variant.VariantRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.variant.VariantResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.variant.VariantMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Question;
import kg.peaksoft.peaksoftlmsbb4.db.model.Variant;
import kg.peaksoft.peaksoftlmsbb4.db.repository.QuestionRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.VariantRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.VariantService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
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
public class VariantServiceImpl implements VariantService {
    private final VariantRepository variantRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;
    private final VariantMapper variantMapper;

    @Override
    public VariantResponse saveVariant(Long id, VariantRequest variantRequest) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("not found this id=%s", id)
        ));
        Variant map = modelMapper.map(variantRequest, Variant.class);
        Variant save = variantRepository.save(map);
        question.setVariants(save);
        log.info("successful variant save:{}", save);
        return variantMapper.deConvert(save);
    }

    @Override
    public VariantResponse findById(Long id) {
        Variant variant = variantRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("this id not found=%s", id)
        ));
        log.info("successful find by id:{}", id);
        return variantMapper.deConvert(variant);
    }

    @Override
    public List<VariantResponse> findAll() {
        List<Variant> variantRepositoryAll = variantRepository.findAll();
        return variantMapper.deConvert(variantRepositoryAll);
    }

    @Override
    public VariantResponse update(Long id, VariantRequest variantRequest) {
        boolean exists = variantRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException(
                    String.format("question id not found=%s", id)
            );
        }
        Variant variant = variantRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("not found this question id=%s", id)
        ));
        String currentVariantName = variant.getVariantName();
        String newVariantName = variantRequest.getVariantName();
        if (!currentVariantName.equals(newVariantName)) {
            variant.setVariantName(newVariantName);
        }
        log.info("successful update this id:{}", id);
        return variantMapper.deConvert(variant);
    }

    @Override
    public void delete(Long id) {
        variantRepository.deleteById(id);
        log.info("successful delete this id:{}", id);
    }
}