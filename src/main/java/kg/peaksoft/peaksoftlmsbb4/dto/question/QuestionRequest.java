package kg.peaksoft.peaksoftlmsbb4.dto.question;

import kg.peaksoft.peaksoftlmsbb4.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequest {

    private String question;
    private QuestionType questionType;
}
