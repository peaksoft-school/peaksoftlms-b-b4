package kg.peaksoft.peaksoftlmsbb4.dto.question;

import kg.peaksoft.peaksoftlmsbb4.dto.variant.VariantResponse;
import kg.peaksoft.peaksoftlmsbb4.enums.QuestionType;
import kg.peaksoft.peaksoftlmsbb4.model.Variant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionResponse {
    private Long id;
    private String question;
    private QuestionType questionType;
    private List<VariantResponse>variantResponses;

}
