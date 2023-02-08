package kg.peaksoft.peaksoftlmsbb4.controller.payload.response;

import kg.peaksoft.peaksoftlmsbb4.db.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionResponse {

    private Long questionId;
    private String question;
    private QuestionType questionType;
    private List<VariantResponse> variantResponses;

}
