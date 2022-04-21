package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.dto.variant.VariantRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.variant.VariantResponse;

import java.util.List;

public interface VariantService {
    VariantResponse saveVariant(Long id, VariantRequest variantRequest);

    VariantResponse findById(Long id);

    List<VariantResponse> findAll();

    VariantResponse update(Long id, VariantRequest variantRequest);

    void delete(Long id);
}
