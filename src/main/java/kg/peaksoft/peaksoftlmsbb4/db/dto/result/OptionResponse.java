package kg.peaksoft.peaksoftlmsbb4.db.dto.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OptionResponse {
    private Long id;
    private String option;
    private boolean isCorrect;
}
