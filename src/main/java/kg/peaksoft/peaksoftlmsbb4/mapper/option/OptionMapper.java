package kg.peaksoft.peaksoftlmsbb4.mapper.option;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.dto.option.OptionRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.option.OptionResponse;
import kg.peaksoft.peaksoftlmsbb4.dto.test.TestResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Option;
import kg.peaksoft.peaksoftlmsbb4.model.Test;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OptionMapper implements Converter<Option, OptionRequest, OptionResponse> {
    @Override
    public Option convert(OptionRequest optionRequest) {
        Option option = new Option();
        option.setOptionName(optionRequest.getOptionName());
        option.setAnswer(optionRequest.getAnswer());
        return option;
    }

    @Override
    public OptionResponse deConvert(Option option) {
        OptionResponse optionResponse = new OptionResponse();
        optionResponse.setOptionName(option.getOptionName());
        optionResponse.setId(option.getId());
        optionResponse.setAnswer(option.getAnswer());
        return optionResponse;
    }

    public List<OptionResponse> deConvert(List<Option> options) {
        List<OptionResponse> optionResponses = new ArrayList<>();
        for (Option o : options) {
            optionResponses.add(deConvert(o));
        }
        return optionResponses;
    }
}
