package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.variant.VariantRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.variant.VariantResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.variant.VariantMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Variant;
import kg.peaksoft.peaksoftlmsbb4.db.repository.VariantRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.VariantService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class VariantServiceImpl implements VariantService {
    private final VariantRepository variantRepository;
    private final VariantMapper variantMapper;

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
        String currentVariantName = variant.getOption();
        String newVariantName = variantRequest.getOption();
        if (!currentVariantName.equals(newVariantName)) {
            variant.setOption(newVariantName);
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
