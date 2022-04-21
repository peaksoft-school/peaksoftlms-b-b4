package kg.peaksoft.peaksoftlmsbb4.dto.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.peaksoftlmsbb4.model.Test;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequest {

    private String question;
}
