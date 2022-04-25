package kg.peaksoft.peaksoftlmsbb4.db.dto.question;

import kg.peaksoft.peaksoftlmsbb4.db.dto.variant.VariantResponse;
import kg.peaksoft.peaksoftlmsbb4.db.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionResponse {
    private Long id;
    private String question;
    private QuestionType questionType;
    private List<VariantResponse> variantResponses;

}
