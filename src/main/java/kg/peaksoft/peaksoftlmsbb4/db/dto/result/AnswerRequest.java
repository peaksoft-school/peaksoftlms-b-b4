package kg.peaksoft.peaksoftlmsbb4.db.dto.result;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnswerRequest {
    private Long questionId;
    private List<Long> variantId;
}