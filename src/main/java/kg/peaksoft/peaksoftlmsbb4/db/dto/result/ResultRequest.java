package kg.peaksoft.peaksoftlmsbb4.db.dto.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultRequest {
    @JsonProperty("question_id")
    private Long questionId;
}
