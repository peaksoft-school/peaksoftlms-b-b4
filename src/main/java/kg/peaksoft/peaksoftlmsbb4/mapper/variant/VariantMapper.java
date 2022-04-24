package kg.peaksoft.peaksoftlmsbb4.mapper.variant;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.dto.variant.VariantRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.variant.VariantResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Variant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VariantMapper implements Converter<Variant, VariantRequest, VariantResponse> {
    @Override
    public Variant convert(VariantRequest variantRequest) {
        Variant variant=new Variant();
        variant.setIsTrue(variantRequest.getIsTrue());
        return variant;
    }

    @Override
    public VariantResponse deConvert(Variant variant) {
        VariantResponse variantResponse=new VariantResponse();
        variantResponse.setId(variant.getId());
        variantResponse.setVariantName(variant.getVariantName());
        return variantResponse;
    }

    public List<VariantResponse> deConvert(List<Variant> variants){
        List<VariantResponse> variantResponses = new ArrayList<>();
        for (Variant v:variants) {
            variantResponses.add(deConvert(v));
        }
        return variantResponses;
    }
}
