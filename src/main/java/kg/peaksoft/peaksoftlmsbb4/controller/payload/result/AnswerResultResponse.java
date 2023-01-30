package kg.peaksoft.peaksoftlmsbb4.controller.payload.result;

import kg.peaksoft.peaksoftlmsbb4.db.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AnswerResultResponse {
    private Long id;
    private String question;
    private QuestionType questionType;
    private List<OptionResponse> optionResponses;
}
