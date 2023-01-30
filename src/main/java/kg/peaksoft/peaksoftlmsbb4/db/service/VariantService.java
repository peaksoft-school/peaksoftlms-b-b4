package kg.peaksoft.peaksoftlmsbb4.db.service;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.variant.VariantRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.variant.VariantResponse;

import java.util.List;

public interface VariantService {

    VariantResponse findById(Long id);

    List<VariantResponse> findAll();

    VariantResponse update(Long id, VariantRequest variantRequest);

    void delete(Long id);
}
