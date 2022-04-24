package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.dto.option.OptionRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.option.OptionResponse;

import java.util.List;

public interface OptionService {
    OptionResponse saveOption(Long id, OptionRequest optionRequest);

    OptionResponse findById(Long id);

    List<OptionResponse> findAll();

    OptionResponse update(Long id, OptionRequest optionRequest);

    void delete(Long id);
}
