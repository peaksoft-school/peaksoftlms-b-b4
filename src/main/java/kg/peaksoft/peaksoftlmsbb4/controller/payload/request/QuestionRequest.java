package kg.peaksoft.peaksoftlmsbb4.controller.payload.request;

import kg.peaksoft.peaksoftlmsbb4.db.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionRequest {
    private String question;
    private QuestionType questionType;
    private List<VariantRequest> variants;
}
