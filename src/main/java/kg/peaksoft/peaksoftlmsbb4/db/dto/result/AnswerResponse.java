package kg.peaksoft.peaksoftlmsbb4.db.dto.result;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerResponse {
    private Long id;
    private Long grade;
    private List<Long> studentAnswer;
    private List<Long> correctAnswer;
}
