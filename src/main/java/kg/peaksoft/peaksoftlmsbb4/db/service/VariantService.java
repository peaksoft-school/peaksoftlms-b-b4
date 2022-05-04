package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.db.dto.variant.VariantRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.variant.VariantResponse;

import java.util.List;

public interface VariantService {
    VariantResponse saveVariant( VariantRequest variantRequest);

    VariantResponse findById(Long id);

    List<VariantResponse> findAll();

    VariantResponse update(Long id, VariantRequest variantRequest);

    void delete(Long id);
}
